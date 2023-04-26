package com.neetcode.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Twitter {

	static int timeStamp;
	int feedMaxNum;
	Map<Integer, Set<Integer>> followees;
	Map<Integer, List<Tweet>> tweets;

	public Twitter() {
		timeStamp = 0;
		feedMaxNum = 10;
		followees = new HashMap<>();
		tweets = new HashMap<>();
	}

	public void postTweet(int userId, int tweetId) {
		if (!tweets.containsKey(userId)) {
			tweets.put(userId, new LinkedList<Tweet>());
			follow(userId, userId); // follow itself
		}
		tweets.get(userId).add(0, new Tweet(tweetId, timeStamp++));
	}

	public List<Integer> getNewsFeed(int userId) {
		PriorityQueue<Tweet> feedHeap = new PriorityQueue<>(new Comparator<Tweet>() {
			@Override
			public int compare(Tweet t1, Tweet t2) {
				return t1.timePosted - t2.timePosted;
			}
		});
		Set<Integer> myFollowees = followees.get(userId);
		if (myFollowees != null) {
			for (int followeeId : myFollowees) {
				List<Tweet> followeeTweets = tweets.get(followeeId);
				if (followeeTweets == null) {
					continue;
				}
				for (Tweet t : followeeTweets) {
					if (feedHeap.size() < feedMaxNum) {
						feedHeap.add(t);
					} else {
						if (t.timePosted <= feedHeap.peek().timePosted) { // This check not required
							break;										  // This check not required
						} else {
							feedHeap.add(t);
							feedHeap.poll(); // remove the oldest tweet
						}
					}
				}
			}
		}
		List<Integer> myFeed = new ArrayList<>();
		while (!feedHeap.isEmpty()) {
			myFeed.add(0, feedHeap.poll().tweetId);
		}
		return myFeed;
	}

	public void follow(int followerId, int followeeId) {
		if (!followees.containsKey(followerId)) {
			followees.put(followerId, new HashSet<Integer>());
		}
		followees.get(followerId).add(followeeId);
	}

	public void unfollow(int followerId, int followeeId) {
		if (!followees.containsKey(followerId) || followerId == followeeId) {
			return;
		} // cannot unfollow itself
		followees.get(followerId).remove(followeeId);
	}

	private static class Tweet {
		int tweetId;
		int timePosted;

		public Tweet(int tId, int time) {
			tweetId = tId;
			timePosted = time;
		}
	}


	public static void main(String[] args) {
		Twitter twitter  = new Twitter();
		twitter.postTweet(1, 5);
		System.out.println(twitter.getNewsFeed(1));
		twitter.follow(1, 2);
		twitter.postTweet(2, 6);
		System.out.println(twitter.getNewsFeed(1));
		twitter.unfollow(1, 2);
		System.out.println(twitter.getNewsFeed(1));
	}
}