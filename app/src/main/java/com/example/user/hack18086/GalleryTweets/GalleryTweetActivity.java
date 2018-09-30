package com.example.user.hack18086.GalleryTweets;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.user.hack18086.R;



import java.util.ArrayList;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GalleryTweetActivity extends AppCompatActivity {

    ArrayList<GalleryTweet> tweets = new ArrayList<>();
    ArrayList<Status> statuses = new ArrayList<>();
    EditText hashTagName;
    Twitter twitter;
    String hashTagNameValue;
    ListView list;
    GalleryTweetAdapter tweetsAdapter;
    Context context;
    //ArrayList<Bitmap> imagesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_tweet);


        tweetsAdapter = new GalleryTweetAdapter(this, tweets);
        list = (ListView)findViewById(R.id.gallery_tweets_lists);
        list.setAdapter(tweetsAdapter);


        //new updateTwitterStatus().execute();

    }

    public void upDate(View view) {

        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            hashTagName = (EditText)findViewById(R.id.gallery_tweets_search_box);
            hashTagNameValue = hashTagName.getText().toString();
            new updateTwitterStatus().execute();
        } catch (Exception e) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    class updateTwitterStatus extends AsyncTask<String, String, Void> {

        ProgressDialog dialog;

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
                //statuses = (ArrayList< twitter4j.Status>)tw.getUserTimeline(hashTagNameValue);
                //MediaEntity x[] = statuses.getMediaEntities()
                Query query = new Query(hashTagNameValue);
                query.setCount(30);
                query.setResultType(Query.POPULAR);
                QueryResult result = tw.search(query);
                int count = 0;
                //tweetsAdapter.clear();
                //System.gc();
                tweets.clear();
                System.out.println("comming here");
                boolean flag = false;
                for(twitter4j.Status status : result.getTweets()){
                    MediaEntity x[] = status.getMediaEntities();
                    for(MediaEntity m : x){
                        //tweets.add(new GalleryTweet(m.getMediaURLHttps(), null));
                        String url = m.getMediaURLHttps();
                        System.out.println(url);
                        Bitmap image = null;
                        image = BitmapFactory.decodeStream(new java.net.URL(url).openStream());
                        tweets.add(new GalleryTweet(m.getMediaURLHttps(), image));
                        flag = true;
                    }
                    //if(tweets.size() >= 20) break;
                   // if(count >= 1) break;
                }
            } catch (TwitterException e) {
                Log.d("Failed to post!", e.getMessage());
            } catch (Exception e) {
                Toast.makeText(context, "Something Error Occured", Toast.LENGTH_SHORT).show();
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
            //tweetsAdapter.addAll(tweets);
            Log.d("Here", Integer.toString(tweets.size()));
            Log.d("Hi", Integer.toString(tweetsAdapter.getCount()));
            tweetsAdapter.notifyDataSetChanged();
            //list.setAdapter(tweetsAdapter);
        }

    }

}
