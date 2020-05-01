package com.huang.leecode.service_design;

import java.util.*;

public class Twitter355 {

    public static void main(String[] args) {
        
        new PriorityQueue<Tweet>((o1, o2) -> -o1.date + o2.date);

        Twitter355 twitter355 = new Twitter355();
        twitter355.postTweet(1, 5);
        twitter355.postTweet(1, 3);

        twitter355.getNewsFeed(1);

        return;
    }

    //时钟，不考虑线程安全的问题
    private static int date = 0;

    //存储用户信息
    private Map<Integer, User> userIdUserMap = new HashMap<Integer, User>();

    /** Initialize your data structure here. */
    public Twitter355() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = null;
        if (!userIdUserMap.containsKey(userId)) {
            //如果用户不存在,先创建用户的信息
            user = new User(userId);
            userIdUserMap.put(userId, user);
        } else {
            user = userIdUserMap.get(userId);
        }

        user.postTweet(tweetId);

        return;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        User user = this.userIdUserMap.get(userId);
        if (user == null) {
            return new ArrayList<Integer>();
        }

        PriorityQueue<Tweet> allNews = new PriorityQueue<Tweet>();
        allNews.addAll(user.getTweetList());
        for (int followedUserId : user.getFollowSet()) {
            User followedUser = userIdUserMap.get(followedUserId);
            if (followedUser != null) {
                allNews.addAll(followedUser.getTweetList());
            }
        }

        List<Integer> latest10News = new ArrayList<Integer>();

        for (int i=0; i < 10  && !allNews.isEmpty(); i++) {
                Tweet tweet = allNews.poll();
                latest10News.add(tweet.getId());

        }

        return latest10News;

    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            //不让自己关注自己
            return;
        }
        User follower = userIdUserMap.get(followerId);
        if (follower == null) {
            follower = new User(followerId);
            userIdUserMap.put(followerId, follower);
        }

        follower.getFollowSet().add(followeeId);
        return;
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User user = userIdUserMap.get(followerId);
        if (user == null) {
            return;
        }

        user.getFollowSet().remove(followeeId);
        return;
    }

    /**
     * 用户
     */
    private class User {

        //用户id
        private int id;

        //关注的用户
        private HashSet<Integer> followSet = new HashSet<Integer>();

        //自己的推文，最老的推文 index = 0
        private List<Tweet> tweetList = new ArrayList<Tweet>();

        public User(int userId) {
            this.id = userId;
        }

        public void postTweet(int tweetId) {
            Tweet tweet = new Tweet(tweetId);
            if (this.tweetList.size() == 10) {
                this.tweetList.remove(0);
            }
            this.tweetList.add(tweet);
            return;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public HashSet<Integer> getFollowSet() {
            return followSet;
        }

        public void setFollowSet(HashSet<Integer> followSet) {
            this.followSet = followSet;
        }

        public List<Tweet> getTweetList() {
            return tweetList;
        }

        public void setTweetList(List<Tweet> tweetList) {
            this.tweetList = tweetList;
        }
    }

    /**
     * 推文
     */
    private class Tweet implements Comparable{
        //推文id
        private int id;

        //推文发布的时间
        private int date;

        public Tweet (int tweetId) {
            this.id = tweetId;
            this.date = ++Twitter355.date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof  Tweet) {
                return - (this.date - ((Tweet) o).getDate());
            } else {
                return -1;
            }
        }
    }

}
