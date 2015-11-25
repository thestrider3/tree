package com.twitter;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class GetTweets {
		
		private ConfigurationBuilder cb;
	    private DB db;
	    private DBCollection items;
	    




	    /**
	     * static block used to construct a connection with tweeter with twitter4j
	     * configuration with provided settings. This configuration builder will be
	     * used for next search action to fetch the tweets from twitter.com.
	     */

	    public static void main(String[] args) throws InterruptedException {


	    	GetTweets stream = new GetTweets();
	        stream.loadMenu();
	        //double latitude[]=stream.getLatitude();
	        //stream.disp(latitude);

	    }
	    
public double[] getLatitude(String keyword){
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb(keyword);
	double[] latitude=mdb.getLatitude();
	//System.out.println("yaya");
	return latitude;
}
public void disp(double[] latitude)
{
	for(int i=0;i<latitude.length;i++)
	{
		System.out.println(i+ " "+ latitude[i]);
	}
	
}
public double[] getLongitude(String keyword){
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb(keyword);
	double[] longitude=mdb.getLongitude();
	//System.out.println("yaya");
	return longitude;
}
public String getKeyword(String Key)
{
	String keyword=Key;
	return keyword;
}
	
public double[] getLatNews()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("news");
	double[] latitude=mdb.getLatitude();
	return latitude;	
}

public double[] getLongNews()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("news");
	double[] longitude=mdb.getLongitude();
	return longitude;	
}

public double[] getLatPolitics()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("politics");
	double[] latitude=mdb.getLatitude();
	return latitude;	
}

public double[] getLongPolitics()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("politics");
	double[] longitude=mdb.getLongitude();
	return longitude;	
}


public double[] getLatSports()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("Sports");
	double[] latitude=mdb.getLatitude();
	return latitude;	
}
public double[] getLatTechnology()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("technology");
	double[] latitude=mdb.getLatitude();
	return latitude;	
}
public double[] getLongTechnology()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("technology");
	double[] longitude=mdb.getLongitude();
	return longitude;	
}

public double[] getLongSports()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("sports");
	double[] longitude=mdb.getLongitude();
	return longitude;	
}

	
public double[] getLatMusic()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("music");
	double[] latitude=mdb.getLatitude();
	return latitude;	
}

public double[] getLongMusic()
{
	MongoDBDao mdb = new MongoDBDaoImpl();
	mdb.connectDb("music");
	double[] longitude=mdb.getLongitude();
	return longitude;	
}	    
public String loadMenu() throws InterruptedException {

			//Connection to database
	        System.out.print("Please choose a name for your stream:\t");
	        //Scanner input = new Scanner(System.in);
	        //String keyword = input.nextLine();
	        String keyword="general";
	        //getKeyword(keyword);
	        MongoDBDao mdb = new MongoDBDaoImpl();
	        boolean stopStream;
	        mdb.connectDb(keyword);
	        
	        //int i=0;
	        
	        //Twitter Configuration        
	        

	            cb = new ConfigurationBuilder();
	            cb.setDebugEnabled(true);
	            cb.setOAuthConsumerKey("Q1JP1QWl03Jwfai8ZGwvYPVKZ");
	            cb.setOAuthConsumerSecret("0t4BUKkFzGtX2a2BFqlc9hDk245h0RdWfKmz5ttCAPvkwJyGMe");
	            cb.setOAuthAccessToken("137228501-KPhsxTtE7mlJmh5Df3yhW6Bzg29sajukkaLL0mg1");
	            cb.setOAuthAccessTokenSecret("h1QIlhJOBUTNsDvWiJzu0NB0nXY20U77dqH9wEbx9XEvd");

	            TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	            StatusListener listener = new StatusListener() {
	            	int i=0;
	                public void onStatus(Status status) {
	                	
	                	try {
	                		if(status.getGeoLocation() != null)
	                		{
	                		if(i<10)
	                		{
	                			
	                			
							mdb.storeData(status);
							i++;
							//Thread.sleep(10);
							System.out.println(i);
	                		}
							//stopStream1=true;
	                		else
	                			{
	                			getLatitude(keyword);
	                			//getLongitude(keyword);
	                			twitterStream.cleanUp();
	                		}
	                		}
						
	                		
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
							return ;
						} 
	                	
	               
	                }

	                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	                    System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	                }

	                public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	                    System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	                }

	                public void onScrubGeo(long userId, long upToStatusId) {
	                    System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	                }

	                @Override
	                public void onStallWarning(StallWarning stallWarning) {
	                    //To change body of implemented methods use File | Settings | File Templates.
	                }

	                public void onException(Exception ex) {
	                    ex.printStackTrace();
	                }
	                
	            };

	            FilterQuery fq = new FilterQuery();
	            String keywords[] = {"sports","football","baseball","basketball","tennis"};
	            fq.track(keywords);
	            //twitterStream.addListener(listener);	            
	            FilterQuery locationFilter = new FilterQuery();
	            double[][] locations = {{-180.0d,-90.0d},{180.0d,90.0d}};
	            locationFilter.locations(locations);
	            twitterStream.addListener(listener);
	            twitterStream.filter(locationFilter);
	            String news[]={"news","cnn","bbc","live","reported","crime","murder","shooting","headline","accident","suicide","destroyed","world","phone","introduce","buzz","camera"};
	            String politics[]={"politics","republican","liberal","election","obama","donald trump","clinton","american","party"};
	            String sports[]={"sports","football","baseball","basketball","tennis","swimming","cricket","yankee","ronaldo","messi","met"};
	            String music[]={"music","jazz","rap","pop","rock","song","singer","taylor","beiber","selena","charts","chris","maroon"};
	            String technology[]={"smartphone","laptop","computer","microsoft","windows","technology","android","iOS","samsung","launch"};
	            /*
	            fq.track(politics);
	            twitterStream.filter(fq);
	            */
	            //twitterStream.filter(fq);
	           // twitterStream.sample();
	            //if(!stopStream1)
	            //twitterStream.cleanUp();
	            return keyword;

	        }

}
