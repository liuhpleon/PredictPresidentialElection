/*write by haopeng liu*/
import java.io.PrintStream;
import java.util.*;
public class CalculateProbability {
	/* calculate the whole support rate*/
	public double supportRate(HashSet<Info>set){
		double pos = 0;
		double neg = 0;
		double neu = 0;
		for(Info info:set){
			if(info.opinion()>0) pos++;
			else if(info.opinion()==0) neu++;
			else neg++;
		}
		//System.out.println("pos:"+pos/(pos+neu+neg));
		//System.out.println("neu:"+neu/(pos+neu+neg));
		//System.out.println("neg:"+neg/(pos+neu+neg));
		return pos/(pos+neu+neg);
	}
	/*calculate the support rate by day*/
    public HashMap<String,Double> supportRateByDay(HashSet<Info>set){
    	HashMap<String,Double>res = new HashMap<>();
    	HashMap<String,HashSet<Info>>map = new HashMap<>();
    	for(Info info:set){
    		String date = info.date.substring(0, 11);
    		if(date.contains("2016-1")){
    		    if(map.containsKey(date)) map.get(date).add(info);
    		    else{
    			    HashSet<Info>s = new HashSet<>();
    			    s.add(info);
    			    map.put(date,s);
    		    }
    		}
    	}
    	for(String date:map.keySet()){
    		double rate = supportRate(map.get(date));
    		res.put(date,rate);
    	}
    	return res;
    }
    public static void main(String args[]){
    	CalculateProbability probability = new CalculateProbability();
    	Classifier classifier = new Classifier();
    	double hillaryRate = probability.supportRate(classifier.hillary());
    	double trumpRate = probability.supportRate(classifier.trump());
    	HashMap<String,Double>hillarydaily = probability.supportRateByDay(classifier.hillary());
    	HashMap<String,Double>trumpdaily = probability.supportRateByDay(classifier.trump());
    	System.out.println(hillaryRate);
    	System.out.println(trumpRate);
    	System.out.println("hillarydaily");
    	for(String date:hillarydaily.keySet()){
    		System.out.println(date);
    	}
    	for(String date:hillarydaily.keySet()){
    		System.out.println(hillarydaily.get(date));
    	}
    	System.out.println("trumpdaily");
    	for(String date:trumpdaily.keySet()){
    		System.out.println(date);
    	}
    	for(String date:hillarydaily.keySet()){
    		System.out.println(trumpdaily.get(date));
    	}
    	
    }
}
