package com.example.user.hack18086.GalleryTweets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.user.hack18086.R;
import com.example.user.hack18086.UserTweets.Tweets;
import com.example.user.hack18086.app.AppController;

import java.util.ArrayList;
import java.util.List;

public class GalleryTweetAdapter extends ArrayAdapter<GalleryTweet> {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public GalleryTweetAdapter(@NonNull Context context, ArrayList<GalleryTweet> tweets) {
        super(context, 0, (List<GalleryTweet>) tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.gallery_tweet_list_item, parent, false);
        }

        GalleryTweet currentadapter = getItem(position);

        ImageView image = listItemView.findViewById(R.id.thumbnail);
        image.setImageBitmap(currentadapter.getmImage());

        return listItemView;
    }
}
