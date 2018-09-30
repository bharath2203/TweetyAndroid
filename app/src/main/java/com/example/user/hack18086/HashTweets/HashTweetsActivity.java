package com.example.user.hack18086.HashTweets;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.hack18086.R;
import com.example.user.hack18086.UserTweets.Tweets;

import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class HashTweetsActivity extends AppCompatActivity {

    ArrayList<HashTweets> tweets = new ArrayList<>();
    ArrayList<Status> statuses = new ArrayList<>();
    EditText hashTag;
    String hashTagValue;
    ListView list;
    HashTweetsAdapter hashTweetsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_tweets);

        hashTweetsAdapter = new HashTweetsAdapter(this, tweets);
        list = (ListView)findViewById(R.id.hash_tweets_lists);
        list.setAdapter(hashTweetsAdapter);
    }
    public void upDate(View view) {

        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            hashTag = (EditText)findViewById(R.id.hash_tweets_search_box);
            hashTagValue = hashTag.getText().toString();
            new updateTwitterStatus().execute();
        } catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
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
                Query query = new Query(hashTagValue);
                query.setCount(20);
                query.setResultType(Query.POPULAR);
                statuses = (ArrayList)tw.search(query).getTweets();

            } catch (TwitterException e) {
                Log.d("Failed to post!", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            int count = 0;
            tweets.clear();
            hashTweetsAdapter.clear();
            for(twitter4j.Status status : statuses){
                count++;
                Log.d("Print", status.getText());
                if(count > 20) break;
                tweets.add(new HashTweets(status.getUser().getScreenName(), status.getText(), status.getFavoriteCount()));
            }
            //hashTweetsAdapter.clear();
            hashTweetsAdapter.addAll(tweets);
            hashTweetsAdapter.notifyDataSetChanged();
            //list.setAdapter(hashTweetsAdapter);
        }

    }

}
