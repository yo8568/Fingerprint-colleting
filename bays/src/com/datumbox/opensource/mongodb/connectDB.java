package com.datumbox.opensource.mongodb;

import java.awt.List;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class connectDB {

	private static String url = "mongodb://140.118.155.213";
	private static String DBase = "fingerprints";
	private static String collection = "csie";

	public connectDB() {
		Map<String, Object> dataset = new HashMap<String, Object>();
	}

	private DBCollection getConnect() throws UnknownHostException {
		MongoClient mongo = new MongoClient(new MongoClientURI(url));
		// 連結MongoDB server
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
		BasicDBObject query = new BasicDBObject();

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> filter = new HashMap<String, Object>();
		result = query_condition(fp, query);
		
		for (Object key : result.keySet()) {
			
			String[] str = result.get(key).toString().split(",");
			filter.put((String) key, str[1]);
			System.out.println(key + " : " + str[1]);
			
		}/*
       for (Object key : result.keySet()) {
			
			//String[] str = result.get(key).toString().split(",");
			//filter.put((String) key, str[1]);
			System.out.println(key + " : " + result.get(key));
			
		}*/

	}

	/**
	 * 搜尋特定字串
	 * 
	 * @param DBCollection
	 * @param BasicDBObject
	 * 
	 * @return Map<String, Object> map
	 */
	public static Map<String, Object> query_condition(DBCollection coll,
			BasicDBObject query_arg) {

		Map<String, Object> map = new HashMap<String, Object>();
		DBCursor cursor = (query_arg == null) ? coll.find() : coll
				.find(query_arg);

		// System.out.println(cursor);
		try {
			int i = 0;
			while (cursor.hasNext()) {
				String key = Integer.toString(i);
				map.put(key, cursor.next().toMap());
				// System.out.println(cursor.next().toMap());
				i++;
			}
		} finally {
			cursor.close();
		}

		return map;

	}
	public static Map<String, Object> feature_select(String[] selection ) throws UnknownHostException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		connectDB instance = new connectDB();
		DBCollection fp = instance.getConnect();
		BasicDBObject query = new BasicDBObject();

		Map<String, Object> result = new HashMap<String, Object>();
		result = query_condition(fp, query);

		for (Object key : result.keySet()) {
        
		}

		
		
		return map;
	}

}