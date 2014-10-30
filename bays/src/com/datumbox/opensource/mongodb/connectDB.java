package com.datumbox.opensource.mongodb;

import java.awt.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class connectDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String url = "mongodb://140.118.155.213";
		String DBase = "fingerprints";
		String collection = "csie";

		MongoClient mongo = new MongoClient(new MongoClientURI(url));
		// 連結MongoDB server
		DB db = mongo.getDB(DBase);
		// choose a database
		DBCollection coll = db.getCollection(collection);
		// choose a collection

		BasicDBObject query = new BasicDBObject();
		
		
	query_condition(coll,query);
		/*
		 * List<String> list = mongo.getDatabaseNames(); // database List
		 * Set<String> colls = db.getCollectionNames();
		 * 
		 * DBObject myDoc = coll.findOne(); // Finding the First Document in a
		 * Collection Using
		 
		// Query all with cursor

		BasicDBObject query = new BasicDBObject("i", 71);
		query = new BasicDBObject("i", new BasicDBObject("$gt", 50));
		DBCursor cursor = coll.find();
		
		try {int i=0;
			while (cursor.hasNext()&& i<20) {
				i++;
				System.out.println(cursor.next()); 
			}
		} finally {
			
			cursor.close();
		}
		*/
		
	}
	/**
	 * 搜尋特定字串
	 * @param DBCollection 
	 * @param BasicDBObject
	 * 
	 * @return List result 
	 */
	public static Map<String, Object> query_condition(DBCollection coll, BasicDBObject query_arg) {
		Map<String, Object> result = null;
	
		DBCursor cursor = (query_arg== null)?coll.find():coll.find(query_arg);
		
		//System.out.println(cursor);
		try {
			while (cursor.hasNext() ) { 
			System.out.println(cursor.next().toMap());
			}
		} finally {
			cursor.close();
		}
		
		return result;
	

}
}