package com.example.user.hack18086.GalleryTweets;

import android.graphics.Bitmap;

public class GalleryTweet {
    String mediaURL;
    Bitmap mImage;

    public GalleryTweet(String mediaURL, Bitmap mImage){
        this.mediaURL = mediaURL;
        this.mImage = mImage;
    }

    public String getMediaURL() {
        return mediaURL;
    }
    public Bitmap getmImage() { return mImage; }
}
