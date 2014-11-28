/* 
 * Copyright (C) 2014 Vasilis Vryniotis <bbriniotis at datumbox.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.datumbox.opensource.examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.datumbox.opensource.classifiers.NaiveBayes;
import com.datumbox.opensource.dataobjects.NaiveBayesKnowledgeBase;
import com.datumbox.opensource.mongodb.connectDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * 
 * @author Vasilis Vryniotis <bbriniotis at datumbox.com>
 * @see <a
 *      href="http://blog.datumbox.com/developing-a-naive-bayes-text-classifier-in-java/">http://blog.datumbox.com/developing-a-naive-bayes-text-classifier-in-java/</a>
 */
public class NaiveBayesExample {

	/**
	 * Reads the all lines from a file and places it a String array. In each
	 * record in the String array we store a training example text.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	/*
	 * public static String[] readLines(URL url) throws IOException {
	 * 
	 * Reader fileReader = new InputStreamReader(url.openStream(),
	 * Charset.forName("UTF-8")); List<String> lines; try (BufferedReader
	 * bufferedReader = new BufferedReader(fileReader)) { lines = new
	 * ArrayList<>(); String line; while ((line = bufferedReader.readLine()) !=
	 * null) { lines.add(line); } } return lines.toArray(new
	 * String[lines.size()]); }
	 */
	/**
	 * Main method
	 * 
	 * @param args
	 *            the command line arguments
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		// 嚙瞇DATASET嚙緬MongoDB query 嚙碼嚙踝蕭
		connectDB instance = new connectDB();
		DBCollection fp = instance.getConnect();
		// Pattern john = Pattern.compile("54167453d19088084ae504f7",
		// Pattern.CASE_INSENSITIVE);
		BasicDBObject y = new BasicDBObject("width", 1680);
		BasicDBObject x = new BasicDBObject("uid", "4X1AAmlTIgSaqEsC10g2");
		BasicDBObject query = null;

		Map<String, String[]> result = new HashMap<String, String[]>();
		Map<String, Object> filter = new HashMap<String, Object>();

		result = instance.query_condition(fp, query);

		/*
		 * for (Object key : result.keySet()) { // System.out.println(key +
		 * " : " + result.get(key)); }
		 */
		/**/

		// map of dataset files
		Map<String, URL> trainingFiles = new HashMap<>();
		/*
		 * trainingFiles.put("English",
		 * NaiveBayesExample.class.getResource("datasets/training.language.en.txt"
		 * )); trainingFiles.put("French",
		 * NaiveBayesExample.class.getResource("datasets/training.language.fr.txt"
		 * )); trainingFiles.put("German",
		 * NaiveBayesExample.class.getResource("datasets/training.language.de.txt"
		 * ));
		 */
		// trainingFiles.put("English",
		// NaiveBayesExample.class.getResource("datasets/training.language.en.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("text.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("text2.txt"));
		String[] s = new String[10000];
		String[] s2 = new String[10000];
		// loading examples in memory
		Map<String, String[]> trainingExamples = new HashMap<>();
		Map<String, String[]> trainingdata = new HashMap<>();

		for (String entry : result.keySet()) {
			trainingExamples.put(entry, result.get(entry));
			// System.out.println(entry.getKey());
		}
		/* trainingExamples */
		String cookie[] = new String[10000];
		int cookie_null = 0;
		int key_no = 0;
		int feature = 11;//11->ip 1->cookie
		int feature2 = 1;
		for (Object key : trainingExamples.keySet()) {
			key_no++;
			boolean flag = false;
			if (trainingExamples.get(key)[1] == null) {
				cookie_null++;
			} else {
				for (int str_compare = 0; str_compare < cookie.length; str_compare++) {
					if (cookie[str_compare] != null) {
						if (cookie[str_compare].equals(trainingExamples
								.get(key)[feature])) {
							flag = true;
						}
					}
				}
				if (!flag) {
					int com_new_one = 0;
					for (int com_new = 0; com_new < cookie.length; com_new++) {
						if (cookie[com_new] != null) {
							com_new_one++;
						}
					}
					cookie[com_new_one] = trainingExamples.get(key)[feature];
					
				} 
				s[key_no]= trainingExamples.get(key)[feature];
			    s2[key_no]= (String)key;
				// System.out.println(trainingExamples.get(key)[feature]);
				String[] featurearr = { trainingExamples.get(key)[feature] };
				trainingdata.put((String) key, featurearr);

			}

		}
		int cookie_count = 0;
		for (int i = 0; i < cookie.length; i++) {
			if (cookie[i] != null) {
				cookie_count++;
			}
		}
		System.out
				.println("According to IP, it classified dataset to generate  "
						+ cookie_count + " sets");
		System.out.println("Null ip set iclude " + cookie_null + " instances");
		System.out.println(key_no);
		for (int i = 0; i < s.length; i++) {
			try {
				bw.write(s[i]);
				bw2.write(s2[i]+",");
			} catch (NullPointerException e) {

			}
		}

		bw.close();
		bw2.close();
		/*
		 * Map<String, String[]> trainingExamples = new HashMap<>();
		 * 
		 * for(Object key : result.keySet()) {
		 * 
		 * trainingExamples.put();
		 * 
		 * } for (Object key : trainingExamples.keySet()) {
		 * 
		 * System.out.println(key + " : " ); for(String
		 * token:trainingExamples.get(key)){ System.out.println(token);
		 * 
		 * } }
		 */

		// train classifier
		NaiveBayes nb = new NaiveBayes();
		nb.setChisquareCriticalValue(6.63); // 0.01 pvalue
		nb.train(trainingdata);

		// get trained classifier knowledgeBase
		NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();

		nb = null;
		trainingExamples = null;

		FileReader fr = new FileReader("text.txt");

		BufferedReader br = new BufferedReader(fr);
        String[] brarray = new String[1000];
		while (br.ready()) {

			brarray = br.readLine().split("(?<=\\})(?=\\{)");

		}

		fr.close();
        for(int i=0 ; i<brarray.length;i++){
        	System.out.println(brarray[i]);
        	
        }
        
        FileReader fr2 = new FileReader("text2.txt");

		BufferedReader br2 = new BufferedReader(fr2);
        String[] brarray2 = new String[1000];
		while (br2.ready()) {

			brarray2 = br2.readLine().split(",");

		}

		fr2.close();
		nb = new NaiveBayes(knowledgeBase);
		String exampleEn = "guuLsdEHsaL5KgSqzN3x";
		double accuracy_count =0.0;
		double total =0.0;
		FileWriter fw = new FileWriter("unmatch.txt");
        for(int i=0 ; i<brarray2.length;i++){
        	System.out.println(brarray2[i]);
        	String outputEn = nb.predict(brarray[i]);
        	total++;
        	
        	if(outputEn.equals(brarray2[i])){
        		accuracy_count++;
        		   
        		   
        		          
        		   
        		           
        		        	}else{
        		        		 fw.write("The sentense"+brarray[i] +" was classified as "+outputEn+"and real id was"+brarray2[i]+"   ");
        		        		
        		        	}
    		System.out.format("The sentense \"%s\" was classified as \"%s\"and real id was\"%s\" .%n",
    				brarray[i] , outputEn,brarray2[i]);
        }
        
        double accuracy=accuracy_count/total;
        System.out.println("總數:"+total);
        System.out.println("沒命中個數:"+(total-accuracy_count));
        System.out.println("命中個數:"+accuracy_count);
        System.out.println("命中率:"+accuracy);
		// Use classifier
		fw.flush();
        		   
        		           fw.close();

		
		

		/*
		 * String exampleFr = "Je suis Fran癟ais"; String outputFr =
		 * nb.predict(exampleFr);
		 * System.out.format("The sentense \"%s\" was classified as \"%s\".%n",
		 * exampleFr, outputFr);
		 * 
		 * String exampleDe = "Ich bin Deutsch"; String outputDe =
		 * nb.predict(exampleDe);
		 * System.out.format("The sentense \"%s\" was classified as \"%s\".%n",
		 * exampleDe, outputDe);
		 */
	}

}
