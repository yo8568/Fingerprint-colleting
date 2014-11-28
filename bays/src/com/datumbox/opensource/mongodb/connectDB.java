package com.datumbox.opensource.mongodb;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class connectDB {

	private static String url = "mongodb://140.118.155.213";
	private static String DBase = "fingerprints";
	private static String collection = "csie2";

	public connectDB() {
		Map<String, Object> dataset = new HashMap<String, Object>();
	}

	public DBCollection getConnect() throws UnknownHostException {
		MongoClient mongo = new MongoClient(new MongoClientURI(url));
		// �s��MongoDB server
		DB db = mongo.getDB(DBase);
		// choose a database
		DBCollection coll = db.getCollection(collection);
		// choose a collection
		return coll;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		connectDB instance = new connectDB();
		DBCollection fp = instance.getConnect();
		//Pattern john = Pattern.compile("54167453d19088084ae504f7", Pattern.CASE_INSENSITIVE);
		BasicDBObject y = new BasicDBObject("width",1680);
		BasicDBObject x = new BasicDBObject("_id", "545f51d3d19088a021eb91fb");
		BasicDBObject query = x;

		Map<String, String[]> result = new HashMap<String,  String[]>();
		Map<String, Object> filter = new HashMap<String, Object>();
		
		result = query_condition(fp, query);
		
		 for (Object key : result.keySet()) {
	            System.out.println(key + " : " + result.get(key)[0]);
	             }
		/*for (Object key : result.keySet()) {
			
			String[] str = result.get(key).toString().split(",");
			filter.put((String) key, str[1]);
			System.out.println(key + " : " + str[1]);
			
		}*/
		
		/*
       for (Object key : result.keySet()) {
			
			//String[] str = result.get(key).toString().split(",");
			//filter.put((String) key, str[1]);
			System.out.println(key + " : " + result.get(key));
			
		}*/

	}

	/**
	 * �j�M�S�w���data 
	 * �M���নMap 
	 * 
	 * @param DBCollection
	 * @param BasicDBObject
	 * 
	 * @return Map<String, String[]> map
	 */
	public static Map<String, String[]> query_condition(DBCollection coll,
			BasicDBObject query_arg) {

		Map<String, String[]> map = new HashMap<String, String[]>();
		DBCursor cursor = (query_arg == null) ? coll.find() : coll
				.find(query_arg);

		// System.out.println(cursor);
		try {
			int i = 0;
			while (cursor.hasNext()) {
				String key = Integer.toString(i);
				
				Map<String,Object> temp = cursor.next().toMap();
				
				String[] data = new String[14];
				 for (Object key1 : temp.keySet()) {
					 //Map �B�z
					 if(key1.toString().equals("_id")){
				        	// System.out.println((String)temp.get("uid"));
				        	 data[0]= temp.get("_id").toString();
				         }		 				 
					 if(key1.toString().equals("uid")){
			        	// System.out.println((String)temp.get("uid"));
			        	 data[1]=(String) temp.get("uid");
			         }
			         if(key1.toString().equals("browser")){
			        	// System.out.println((String)temp.get("browser"));
			        	 data[2]= temp.get("browser").toString();
			         }
			         if(key1.toString().equals("navigator")){
			        	 //System.out.println(temp.get("navigator"));
			        	 data[3]=temp.get("navigator").toString();
			         }
			         if(key1.toString().equals("screen")){
			        	// System.out.println(temp.get("screen").toString());
			        		data[4]= temp.get("screen").toString();
			         }
			         if(key1.toString().equals("window")){
			        	 //System.out.println(temp.get("window"));
			        	 data[5]= temp.get("window").toString();
			         }
			         if(key1.toString().equals("timezone")){
			        	// System.out.println(temp.get("timezone"));
			        	 data[6]= temp.get("timezone").toString();
			         }
			         if(key1.toString().equals("fonts")){
			        	// System.out.println(temp.get("fonts"));
			        	 data[7]= temp.get("fonts").toString();
			         }
			         if(key1.toString().equals("rtts")){
			        	// System.out.println(temp.get("rtts"));
			        	 data[8]= temp.get("rtts").toString();
			         }
			         if(key1.toString().equals("timestamp")){
			        	 //System.out.println(temp.get("timestamp"));
			        	 data[9]= temp.get("timestamp").toString();
			         }
			         if(key1.toString().equals("0")){//ip
			        	// System.out.println(temp.get("0"));
			        	 if(temp.get("0").toString().contains("ip")&&(!temp.get("0").toString().contains("iPhone"))){
			        		  data[10]=temp.get("0").toString();
			        	 }
			        	

			         }
			         if(key1.toString().equals("1")){//time
			        	// System.out.println(temp.get("1"));
			        	 if(temp.get("0").toString().contains("ip")&&(!temp.get("0").toString().contains("iPhone"))){
			        		  data[10]=temp.get("1").toString();
			        	 }
			        	 data[11]=temp.get("1").toString();
			         }
			         if(key1.toString().equals("2")){//mobile
			        	// System.out.println(temp.get("2"));
			        	 if(temp.get("0").toString().contains("ip")&&(!temp.get("0").toString().contains("iPhone"))){
			        		  data[10]=temp.get("2").toString();
			        	 }
			        	 data[12]=temp.get("2").toString();
			         }
			         if(key1.toString().equals("3")){//server
			        	 //System.out.println(temp.get("3"));
			            data[13]=temp.get("3").toString();
			         }
					// System.out.println(data);
			         //System.out.println(key1);
			        }
				 
				 if(data[0]!=null)map.put(data[0],data);
				 else map.put(key,data);
				 i++;
			}
		} finally {
			cursor.close();
		}

		return map;

	}
	/**
	 * 
	 * @param selection
	 * @return
	 * @throws UnknownHostException
	 */
	public static Map<String, Object> feature_select(String[] selection ) throws UnknownHostException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		connectDB instance = new connectDB();
		DBCollection fp = instance.getConnect();
		BasicDBObject query = new BasicDBObject();

		BasicDBObject con1 = new BasicDBObject("width",1680);
		
		Map<String, String[]> result = new HashMap<String, String[]>();
		result = query_condition(fp, query);

		for (Object key : result.keySet()) {
        
		}

		
		
		return map;
	}
	public static String accurantquery(BasicDBObject query_arg , int feature_no) throws UnknownHostException{
		String real = null;
	    Map<String, Object> map = new HashMap<String, Object>();
		
		connectDB instance = new connectDB();
		DBCollection fp = instance.getConnect();
		BasicDBObject query = new BasicDBObject();

		BasicDBObject con1 = new BasicDBObject("width",1680);
		
		Map<String, String[]> result = new HashMap<String, String[]>();
		result = query_condition(fp, query);
		
		
		
		return real;
		
		
	}

}