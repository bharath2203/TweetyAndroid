package com.example.user.hack18086;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class PopularTweetsAdapter extends ArrayAdapter<PopularTweets> {

    public PopularTweetsAdapter(@NonNull Context context, ArrayList<PopularTweets> tweets) {
        super(context, 0, (List<PopularTweets>) tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.user_tweets_list_item, parent, false);
        }

        PopularTweets currentadapter = getItem(position);

        TextView tweet = (TextView) listItemView.findViewById(R.id.popular_tweets);
        tweet.setText(currentadapter.getmTweet());

        TextView no_of_likes = (TextView) listItemView.findViewById(R.id.popular_tweets_likes);
        no_of_likes.setText(Integer.toString(currentadapter.getmNoOfLikes()));

        return  listItemView;
    }
}
