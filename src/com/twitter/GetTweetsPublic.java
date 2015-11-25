package com.twitter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class GetTweetsPublic {

	private ConfigurationBuilder cb;
	static TreeMap<Integer, Sentiment> tmap = new TreeMap<Integer, Sentiment>();
	int[] sent=new int[5];

	public static void main(String[] args) throws InterruptedException {

		GetTweetsPublic stream = new GetTweetsPublic();
		
	
		stream.loadMenu();
		stream.getSentiment();
		
		
		
		}

	
	

	public void loadMenu() throws InterruptedException {

		
		// Twitter Configuration
		

		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("Q1JP1QWl03Jwfai8ZGwvYPVKZ");
		cb.setOAuthConsumerSecret("0t4BUKkFzGtX2a2BFqlc9hDk245h0RdWfKmz5ttCAPvkwJyGMe");
		cb.setOAuthAccessToken("137228501-KPhsxTtE7mlJmh5Df3yhW6Bzg29sajukkaLL0mg1");
		cb.setOAuthAccessTokenSecret("h1QIlhJOBUTNsDvWiJzu0NB0nXY20U77dqH9wEbx9XEvd");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener listener = new StatusListener() {
			int i = 0;
			
			public void onStatus(Status status) {

				
						if (i < 10) {

							
							
							
							//System.out.println(i);
							//System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
							NLP.init();
							System.out.println(status.getText()+ " : " + NLP.findSentiment(status.getText()));
							//Sentiment s=new Sentiment();
							int k=NLP.findSentiment(status.getText());
							sent[k]=sent[k]+1;
							//s.setText(status.getText());
							//s.setSenti(NLP.findSentiment(status.getText()));
							//tmap.put(i,s);
							i++;
						}
						
						else {
							//getSentiment();
							twitterStream.cleanUp();
							
						}
					

					return;
			

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
				// To change body of implemented methods use File | Settings |
				// File Templates.
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}
			
			

		};

		FilterQuery fq = new FilterQuery();
		//String keywords[] = {"i feel","i am feeling","i’m feeling","i dont feel", "I’m", "Im","I am","makes me"};
		String []keywords={"stocks","stock market","revenue","profit"};
		fq.track(keywords);
		// twitterStream.addListener(listener);
		// FilterQuery locationFilter = new FilterQuery();
		// double[][] locations = {{-180.0d,-90.0d},{180.0d,90.0d}};
		// locationFilter.locations(locations);
		twitterStream.addListener(listener);
		// twitterStream.filter(locationFilter);

		twitterStream.filter(fq);
		
		
	}

	public int[] getSentiment() {
		// TODO Auto-generated method stub
		for(int i=0;i<sent.length;i++){
			System.out.println("sentiment    "+sent[i]);
		}
		return sent;
		
	}

}
