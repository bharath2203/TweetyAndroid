package com.example.user.hack18086.HashTweets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.hack18086.R;

import java.util.ArrayList;
import java.util.List;

public class HashTweetsAdapter extends ArrayAdapter<HashTweets> {

    public HashTweetsAdapter(@NonNull Context context, ArrayList<HashTweets> tweets) {
        super(context, 0, (List<HashTweets>) tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.hash_tweets_list_item, parent, false);
        }

        HashTweets currentadapter = getItem(position);

        TextView user = (TextView) listItemView.findViewById(R.id.list_hash_user);
        user.setText('@' + currentadapter.getmUserName());

        TextView tweet = (TextView) listItemView.findViewById(R.id.list_hash_tweets);
        tweet.setText(currentadapter.getmTweet());

        TextView no_of_likes = (TextView) listItemView.findViewById(R.id.hash_tweets_likes);
        no_of_likes.setText(Integer.toString(currentadapter.getmNoOfLikes()));

        return  listItemView;
    }
}
