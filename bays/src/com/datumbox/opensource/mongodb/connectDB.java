package com.datumbox.opensource.mongodb;

import java.awt.List;
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
		// ³sµ²MongoDB server
		DB db = mongo.getDB(DBase);
		// choose a database
		DBCollection coll = db.getCollection(collection);
		// choose a collection

		/*
		 * List<String> list = mongo.getDatabaseNames(); // database List
		 * Set<String> colls = db.getCollectionNames();
		 * 
		 * DBObject myDoc = coll.findOne(); // Finding the First Document in a
		 * Collection Using
		 */
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
		
		
	}

/*	public static List query_condition(String query_arg) {
		List result = null;
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
		return result;
	}*/

}
