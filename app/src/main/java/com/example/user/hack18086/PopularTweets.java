package com.example.user.hack18086;

public class PopularTweets {
    String mUserName;
    String mTweet;
    int mNoOfLikes;

    public PopularTweets(String mUserName, String mTweet, int mNoOfLikes){
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
