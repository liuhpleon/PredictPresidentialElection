/*write by haopeng liu*/
import java.util.*;
import java.io.*;
public class ReadTweets {
	/*read the hillary tweet in the folder and */
    public HashSet<Info> ReadHillary(){
    	HashSet<Info>result = new HashSet();
    	try{
    		File f = new File("/D:/PythonDevelop/TwitterCralwer/HillaryTweets/");
            for(File file:f.listFiles()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    String name = "";
                    String date = "";
                    String tweet = "";
                	if(line.length()>50){
                	    line = line.substring(25, line.length());
                	    String newline = "";
                        for(int i=0;i<line.length();i++){
                    	    if(line.charAt(i)=='|'){
                    	    	line = line.substring(i,line.length());
                    	    	break;
                    	    }              	    
                    	    else newline = newline+line.charAt(i);
                        }
                        name = newline;
                        newline = "";
                        for(int i=0;i<line.length();i++){
                        	if(line.charAt(i)>='0'&&line.charAt(i)<='9'&&newline.equals("")&&i+25<line.length()){
                        		newline = line.substring(i,i+19);
                        		line = line.substring(i+26,line.length());
                        	}
                        }
                        date = newline;
                        tweet = line;
                        Info info = new Info(name, date, tweet,0);
                        result.add(info);
                	}
                }
                
			}
		}catch (Exception e){
            System.out.println(e);
		}
          return result;
    }  
    
    public HashSet<Info> ReadTrump(){
    	HashSet<Info>result = new HashSet();
    	try{
			File f = new File("/D:/PythonDevelop/TwitterCralwer/TrumpTweets/");
            for(File file:f.listFiles()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    String name = "";
                    String date = "";
                    String tweet = "";
                	if(line.length()>50){
                	    line = line.substring(25, line.length());
                	    String newline = "";
                        for(int i=0;i<line.length();i++){
                    	    if(line.charAt(i)=='|'){
                    	    	line = line.substring(i,line.length());
                    	    	break;
                    	    }              	    
                    	    else newline = newline+line.charAt(i);
                        }
                        name = newline;
                        newline = "";
                        for(int i=0;i<line.length();i++){
                        	if(line.charAt(i)>='0'&&line.charAt(i)<='9'&&newline.equals("")&&i+25<line.length()){
                        		newline = line.substring(i,i+19);
                        		line = line.substring(i+26,line.length());
                        	}
                        }
                        date = newline;
                        tweet = line;
                        Info info = new Info(name, date, tweet,0);
                        result.add(info);
                	}
                }
                
			}
		}catch (Exception e){
            System.out.println(e);
		}
          return result;
    }
    public static void main(String args[]){
    	ReadTweets readTweets = new ReadTweets();
    	Set<Info>set1 = readTweets.ReadHillary();
    	Set<Info>set2 = readTweets.ReadTrump();
    	System.out.println(set1.size()+" "+set2.size());
    }
}
/*
 * this class is user for saving the tweet information and used as scoring*/
class Info{
    String name;
    String date;
    String tweet;
    int score;
	public Info(String name,String date,String tweet,int score){
		this.name = name;
		this.date = date;
		this.tweet = tweet;
		this.score = score;
	}
	public String N(){
		return name;
	}
	public String D(){
		return date;
	}
	public String T(){
		return tweet;
	}
	public int S(){
		return score;
	}
	public void MT(String s){
		tweet =s;
		return;
	}
	public void MS(int s){
		score = s;
		return;
	}
	public int opinion(){
		if(score>0) return 1;
		else if(score==0) return 0;
		else return -1;
	}
}
