package com.example.user.hack18086;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.hack18086.GalleryTweets.GalleryTweetActivity;
import com.example.user.hack18086.HashTweets.HashTweetsActivity;
import com.example.user.hack18086.HashTweets.HashTweetsAdapter;
import com.example.user.hack18086.UserTweets.Tweets;
import com.example.user.hack18086.UserTweets.TweetsAdapter;
import com.example.user.hack18086.UserTweets.UserTweetsActivity;

import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class HomeActivity extends AppCompatActivity {

    private Context context = this;

    private TextView mTextMessage;

    //ArrayList<PopularTweets> tweets = new ArrayList<>();
    //ArrayList<Status> statuses = new ArrayList<>();
    ArrayList<String> tweets = new ArrayList<>();
    ListView list;
    ArrayAdapter<String> populartweetsAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //if(!item.isEnabled())
                        //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_hash_gallery:
                    Intent intent2 = new Intent(getContext(), GalleryTweetActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_hash_tweets:
                    Intent intent3 = new Intent(getContext(), HashTweetsActivity.class);
                    startActivity(intent3);
                    return true;
                case R.id.navigation_user_tweets:
                    //mTextMessage.setText(R.string.title_user_tweets);
                    Intent intent4 = new Intent(getContext(), UserTweetsActivity.class);
                    startActivity(intent4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        populartweetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tweets);
        list = (ListView)findViewById(R.id.popular_tweets_lists);
        list.setAdapter(populartweetsAdapter);

        new updateTwitterStatus().execute();

    }

    public Context getContext(){
        return context;
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
                //statuses.clear();
                Trends trends = tw.getPlaceTrends(23424848);
                //populartweetsAdapter.clear();
                for(int i = 0; i < Math.min(10, trends.getTrends().length);i++){
                    String hashTag = trends.getTrends()[i].getName();
                    tweets.add(hashTag);
                    Log.d("Print", hashTag);
                }
            } catch (TwitterException e) {
                Log.d("Failed to post!", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //int count = 0;
            //tweets.clear();
            /*for(twitter4j.Status status : statuses){
                count++;
                Log.d("Print", status.getText());
                if(count > 10) break;
                //tweets.add(new Tweets("john", status.getText(), status.getFavoriteCount()));
            }*/
            //tweetsAdapter.clear();
            //populartweetsAdapter.clear();
            //populartweetsAdapter.addAll(tweets);
            populartweetsAdapter.notifyDataSetChanged();
            //list.setAdapter(tweetsAdapter);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //tweets.clear();
        //populartweetsAdapter.clear();

    }
    @Override
    protected  void onResume(){
        super.onResume();
    }
}
