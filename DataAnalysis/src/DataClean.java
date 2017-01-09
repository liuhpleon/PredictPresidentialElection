/*Write by Haopeng Liu
 * the purpose of this part is to clean the connection words in tweet*/
import java.util.*;
import java.io.*;
/*clean the useless word and reduce the size of tweets*/
public class DataClean {
	private HashSet<String>set = new HashSet<>();
    public void connectionWord(){
    	HashSet<String>bewords = new HashSet<>(Arrays.asList("is","am","are","was","were","being","been","to", "be","in","on","do","does"));
    	HashSet<String>subject = new HashSet<>(Arrays.asList("he","she","his","her","him","you","your","they","them","their","it","if"));
    	HashSet<String>junction = new HashSet<>(Arrays.asList("a","an","and","but","also","neither","either","final"));
    	set.addAll(junction);
    	set.addAll(subject);
    	set.addAll(bewords);
    }
    public HashSet<Info> washData(HashSet<Info>s){
    	connectionWord();
    	HashSet<Info>result = new HashSet<>();
    	for(Info i:s){
    		String tweet = i.T();
    		tweet = tweet.toLowerCase();
    		for(String str:set){
    			tweet = tweet.replaceAll(str,"");
    		}
    		i.MT(tweet);
    		result.add(i);
    	}
    	return result;
    }
    public static void main(String args[]){
    	HashSet<Info>hillary = new ReadTweets().ReadHillary();
    	DataClean dataClean = new DataClean();
    	dataClean.washData(hillary);
    }
}
