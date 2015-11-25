package com.twitter;

import twitter4j.Status;

public interface MongoDBDao {
	public void connectDb(String keyword);
	public void initMongoDB();
	public void storeData(Status status) throws InterruptedException;
	public double[] getLatitude();
	public double[] getLongitude();

}
