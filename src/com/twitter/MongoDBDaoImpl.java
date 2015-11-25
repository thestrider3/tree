package com.twitter;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import twitter4j.Status;

public class MongoDBDaoImpl implements MongoDBDao {

	private DB db;
	private DBCollection items;

	@Override
	public void connectDb(String keyword) {
		try {
			initMongoDB();
			items = db.getCollection(keyword);
			BasicDBObject index = new BasicDBObject("tweet_ID", 1).append("unique", true);
			// items.ensureIndex(index, new BasicDBObject("unique", true));
			items.createIndex(index);

		} catch (MongoException ex) {
			System.out.println("MongoException :" + ex.getMessage());
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void initMongoDB() throws MongoException {
		try {
			System.out.println("Connecting to Mongo DB..");
			Mongo mongo;
			mongo = new Mongo("127.0.0.1");//Database URL
			db = mongo.getDB("tweetDB6");
		} catch (Exception ex) {
			System.out.println("MongoDB Connection Error :" + ex.getMessage());
		}

	}

	@Override
	public void storeData(Status status) throws InterruptedException {

		BasicDBObject basicObj = new BasicDBObject();
		// if(status.getGeoLocation().equals(true))

		// if(status.getGeoLocation().getLatitude()!=0.0d)
		// {
		
		
		if (status.getGeoLocation() != null) {
			try {
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				basicObj.put("user_name", status.getUser().getScreenName());
				basicObj.put("retweet_count", status.getRetweetCount());
				// basicObj.put("tweet_followers_count",
				// status.getUser().getFollowersCount());
				basicObj.put("source", status.getSource());
				// basicObj.put("coordinates",status.getGeoLocation());
				basicObj.put("latitude", status.getGeoLocation().getLatitude());
				basicObj.put("longitude", status.getGeoLocation().getLongitude());
				// UserMentionEntity[] mentioned =
				// status.getUserMentionEntities();
				// basicObj.put("tweet_mentioned_count", mentioned.length);
				basicObj.put("tweet_ID", status.getId());
				basicObj.put("tweet_text", status.getText());
				//i++;
			}

			catch (Exception e) {
				System.out.println(e);
			}

			try {
				items.insert(basicObj);
			} catch (Exception e) {
				System.out.println("MongoDB Connection Error : " + e.getMessage());

			}

		
		}
		//else
			//throw new InterruptedException();

	}

	@Override
	public double[] getLatitude() {

		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("latitude", new BasicDBObject("$ne", null));
		gtQuery.put("longitude", new BasicDBObject("$ne", null));

		DBCursor cursor = items.find(gtQuery);
		int size = cursor.count();
		double[] lat = new double[size];
		int i = 0;
		while (cursor.hasNext()) {

			lat[i] = Double.parseDouble(cursor.next().get("latitude").toString());
			System.out.println(lat[i]);
			i++;
		}
		return lat;

	}

	@Override
	public double[] getLongitude() {
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("latitude", new BasicDBObject("$ne", null));
		gtQuery.put("longitude", new BasicDBObject("$ne", null));
		DBCursor cursor = items.find(gtQuery);
		int size = cursor.count();
		double[] longi = new double[size];
		int i = 0;

		while (cursor.hasNext()) {
			longi[i] = Double.parseDouble(cursor.next().get("longitude").toString());
			System.out.println(longi[i] + "   " + i);
			i++;

		}
		return longi;

	}

}
