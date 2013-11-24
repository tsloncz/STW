/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ConnectionTest;


import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;


/**
 *
 * @author timsloncz
 */
public class ConnectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("")
          .setOAuthConsumerSecret("")
          .setOAuthAccessToken("")
          .setOAuthAccessTokenSecret("");
        cb.setJSONStoreEnabled(true);

    //Works for print @username - tweet    
    TwitterFactory tf = new TwitterFactory(cb.build());
    Twitter twitter = tf.getInstance();
        try {
            //Query query = new Query("#rescueph");  //search for tweets with a specific hashtag
            Query query = new Query("from:slonztree");  //search for tweets from a specific user
            QueryResult result;
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
                //! Line below prints @username - tweet
                //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                
                //! Below prints tweets in json format
                //System.out.println(tweet.getUser() + ":" + tweet.getText());
                String json = DataObjectFactory.getRawJSON(tweet);
                //System.out.println(json);
                System.out.println("User: " + tweet.getUser().getName());
                System.out.println("Id: " + tweet.getId());
                System.out.println("Location: " + tweet.getGeoLocation());
                System.out.println("Entities: " + tweet.getHashtagEntities());
                System.out.println("Text: " + tweet.getText());
            }

            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
    
}
