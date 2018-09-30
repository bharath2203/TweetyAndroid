package com.example.user.hack18086.UserTweets;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.hack18086.HashTweets.HashTweetsActivity;
import com.example.user.hack18086.R;

import java.util.ArrayList;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class UserTweetsActivity extends AppCompatActivity {

    ArrayList<Tweets> tweets = new ArrayList<>();
    ArrayList<Status> statuses = new ArrayList<>();
    EditText userName;
    Twitter twitter;
    String userNameValue;
    ListView list;
    TweetsAdapter tweetsAdapter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tweets);


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //TwitterJHandler tj = new TwitterJHandler();
        //tj.start();
        //statuses = HomeActivity.statuses;

        //new TwitterJHandler();
        //twitter = TwitterJHandler.twitter;



        //tweets.add(new Tweets("Modi", "Tweet", 60000));
        //tweets.add(new Tweets("Modi", "Hi I am Bharath How are you", 1000000));
       // new updateTwitterStatus().execute();

        tweetsAdapter = new TweetsAdapter(this, tweets);
        list = (ListView)findViewById(R.id.user_tweets_lists);
        list.setAdapter(tweetsAdapter);
    }

    public Context getContext(){
        return context;
    }

    public void upDate(View view) {

        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            userName = (EditText)findViewById(R.id.user_tweets_search_box);
            //userName.clearFocus();
            userNameValue = userName.getText().toString();
            new updateTwitterStatus().execute();
        } catch (Exception e) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }


    class updateTwitterStatus extends AsyncTask<String, String, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected Void doInBackground(String... args) {

            try {
                ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true)
                        .setOAuthConsumerKey("p61aIdtpopSO7Kl1YSfNC2Hwh")
                        .setOAuthConsumerSecret("TpCyi3zEqtfBnELBtsTuHdk7Qo2FletoQKqVjzJPqlfhUrqKkA")
                        .setOAuthAccessToken("749163557195886592-O3xtVRZcKoZ4RX5LcpD8QULzg6APxVt")
                        .setOAuthAccessTokenSecret("WmnZ2uhzE0NRZwHjfS4xWqLxXDfHfgYFqmioQmxPHhjn4");
                TwitterFactory tf = new TwitterFactory(cb.build());
                Twitter tw=tf.getInstance();
                statuses.clear();
                statuses = (ArrayList<twitter4j.Status>) tw.getUserTimeline(userNameValue);

            } catch (TwitterException e) {
                Log.d("Failed to post!", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            int count = 0;
            tweets.clear();
            tweetsAdapter.clear();
            for(twitter4j.Status status : statuses){
                count++;
                Log.d("Print", status.getText());
                if(count > 10) break;
                tweets.add(new Tweets("john", status.getText(), status.getFavoriteCount()));
            }
            //tweetsAdapter.clear();
            tweetsAdapter.addAll(tweets);
            tweetsAdapter.notifyDataSetChanged();
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            //list.setAdapter(tweetsAdapter);
        }

    }
}


