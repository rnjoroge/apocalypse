package apocalypse.dao;
/**
 * @author robertnjoroge
 */


import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;
import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import apocalypse.datasource.MongoDB;
import apocalypse.util.CustomApplicationError;



/*
 * Report Data Access
 */


public class reportDao {
	
	/*
	 * Report Summary 
	 * Returns a summary of all infected and non infected survivors
	 */
	
	 public static String  reportSummary() throws CustomApplicationError {
			

		 
		 StringWriter sb = new StringWriter();
		 sb.append("[");
		 boolean first = true;
		 AggregateIterable  docs =  getCollection().aggregate(Arrays.asList(
				 Aggregates.group("$infection_status", Accumulators.sum("count", 1))
				 ));
		 Iterator iterator = docs.iterator();
		  while (iterator.hasNext()) {
			 
			 if (!first) sb.append(",");
             else first = false;
             sb.append(((Document) iterator.next()).toJson());
			   
			}

         sb.append("]");
         
         return sb.toString();
		
	
	 }
	 
	 
	 private static MongoCollection getCollection() {
		 return MongoDB.getMongoDatabase().getCollection("survivor");
	 }
	 

}
