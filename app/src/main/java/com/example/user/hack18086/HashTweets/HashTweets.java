package com.example.user.hack18086.HashTweets;

public class HashTweets {

    String mUserName;
    String mTweet;
    int mNoOfLikes;

    public HashTweets(String mUserName, String mTweet, int mNoOfLikes){
        this.mUserName = mUserName;
        this.mTweet = mTweet;
        this.mNoOfLikes = mNoOfLikes;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmTweet() {
        return mTweet;
    }

    public int getmNoOfLikes() {
        return mNoOfLikes;
    }

}
