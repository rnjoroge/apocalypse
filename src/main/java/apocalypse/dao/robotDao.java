package apocalypse.dao;
/**
 * @author robertnjoroge
 */


import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import apocalypse.datasource.MongoDB;
import apocalypse.entity.robotEntity;
import apocalypse.util.CustomApplicationError;

public class robotDao {
	
	
	 public static String addRobots(List<robotEntity> robotsJson) throws CustomApplicationError {
		 
		 MongoCollection<Document> RobotCollection = getCollection();
		 List<Document> jsonList = new ArrayList<Document>();
		   for (robotEntity rob : robotsJson) {
			   Document robotDoc = new Document("_id", new ObjectId());
					   robotDoc.append("model", rob.getModel());
					   robotDoc.append("serialNumber", rob.getSerialNumber());
					   robotDoc.append("manufacturedDate", rob.getManufacturedDate());
					   robotDoc.append("category", rob.getCategory());
		
		        jsonList.add(robotDoc);

		    }
		 
		 RobotCollection.insertMany(jsonList);
		return "{\"robot_status\": \"success\"}";
		 
	 }
	    /**
	     *
	     * @return the list of all robots in the database
	     */
	 
	 public static String  listAllRobots() {
		 StringWriter sb = new StringWriter();
		 sb.append("[");
		 boolean first = true;
         FindIterable<Document> docs = getCollection().find();
         for (Document d : docs) {
             if (!first) sb.append(",");
             else first = false;
             sb.append(d.toJson());
         }
         sb.append("]");
         
         return sb.toString();
  
	 }
	 
	 private static MongoCollection getCollection() {
		 return MongoDB.getMongoDatabase().getCollection("robot");
	 }
	 
	 
	 /**
     *
     * @return the number of robots in the system by robot category
     */
	 
	 public static String  robotSummary() throws CustomApplicationError {
			

		 
		 StringWriter sb = new StringWriter();
		 sb.append("[");
		 boolean first = true;
		 AggregateIterable  docs =  getCollection().aggregate(Arrays.asList(
				 Aggregates.group("$category", Accumulators.sum("count", 1))
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

}
