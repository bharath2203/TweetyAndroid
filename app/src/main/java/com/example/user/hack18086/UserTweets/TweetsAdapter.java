package com.example.user.hack18086.UserTweets;

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

public class TweetsAdapter extends ArrayAdapter<Tweets> {

    public TweetsAdapter(@NonNull Context context, ArrayList<Tweets> tweets) {
        super(context, 0, (List<Tweets>) tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.user_tweets_list_item, parent, false);
        }

        Tweets currentadapter = getItem(position);

        TextView tweet = (TextView) listItemView.findViewById(R.id.user_tweets);
        tweet.setText(currentadapter.getmTweet());

        TextView no_of_likes = (TextView) listItemView.findViewById(R.id.user_tweets_likes);
        no_of_likes.setText(Integer.toString(currentadapter.getmNoOfLikes()));

        return  listItemView;
    }
}
