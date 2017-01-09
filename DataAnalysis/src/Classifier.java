/*write by haopeng liu*/
import java.util.*;
import java.io.*;
/*
 * judge if the tweets is positive or negative */
public class Classifier {
	private HashSet<String>neg;
	private HashSet<String>pos;
    public Classifier() {
    	// set up the training data
		neg = read("/D:/PythonDevelop/TwitterCralwer/neg.txt");
		pos = read("/D:/PythonDevelop/TwitterCralwer/pos.txt");
	}
    /*read positive and neg words*/
    public HashSet<String>read(String path){
    	HashSet<String>set = new HashSet<>();
    	try{
            File file = new File(path);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
        	    set.add(line.trim());
            }
    	}catch (Exception e) {
    		System.out.println(e);
		}
    	return set;
    }
    /*judge the tweets*/
    public HashSet<Info> judge(HashSet<Info>set){
    	HashSet<Info>result = new HashSet<>();
    	for(Info info:set){
    		String tweet = info.T();
    		int score = info.score;
    		for(String str:neg){
    			if(tweet.contains(str.toLowerCase())) score--;
    		}
    		for(String str:pos){
    			if(tweet.contains(str.toLowerCase())) score++;
    		}
    		info.MS(score);
    		if(info.date.length()>15&&info.date.contains("2016-1")){
    			result.add(info);
    		}
    	}
    	return result;
    }
    //generate the hillary tweets judgement
    public HashSet<Info> hillary(){
		HashSet<Info>hillary = new DataClean().washData(new ReadTweets().ReadHillary());
        return judge(hillary);
    }
    //generate trump tweets judgement
    public HashSet<Info> trump(){
    	HashSet<Info>trump = new DataClean().washData(new ReadTweets().ReadTrump());
        return judge(trump);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stu
		Classifier classifier = new Classifier();
		classifier.hillary();
		classifier.trump();
	}

}
