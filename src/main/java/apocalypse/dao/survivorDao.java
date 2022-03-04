package apocalypse.dao;

/**
 * @author robertnjoroge
 */


import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import apocalypse.datasource.MongoDB;
import apocalypse.entity.contamination;
import apocalypse.entity.survivorEntity;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import apocalypse.util.CustomApplicationError;

public class survivorDao  {
	

	/**
    * Add a survivor to the database
    * @return the survivor system ref assigned by the system
    */
	 public static String addSurvivor(survivorEntity survivorEntityJson) throws CustomApplicationError {
		 String survivor_system_ref =UUID.randomUUID().toString();
		   if(survivoridExists(survivorEntityJson.getId())){
			   	 throw new CustomApplicationError (" Invalid Survivor id : Already Exists ");
             }
		 try { 
	
			   MongoCollection<Document> SurvivorCollection = getCollection();
		        
		         Document survivor = new Document("_id", new ObjectId());
		                  survivor.append("name", survivorEntityJson.getName())
		                          .append("age", survivorEntityJson.getAge())
		                          .append("infection_status", "non-infected")
		                          .append("id", survivorEntityJson.getId())
		                          .append("gender", survivorEntityJson.getGender())
		                          .append("survivor_system_ref", survivor_system_ref);
		                 
		                  
		                  if(survivorEntityJson.getLast_location().size() > 0) {      	  	      
		                	    survivor.append("location", new Document("type", "point")
		                	    	                     .append("coordinates", survivorEntityJson.getLast_location()));
		                        survivor.append("Last_location", survivorEntityJson.getLast_location());
		                  
		                  }
		                  survivor.append("resources", new ArrayList(Arrays.asList(survivorEntityJson.getResources())));
		                
		                  SurvivorCollection.insertOne(survivor);
		  
		     }
		 catch (Exception e) {
			 e.printStackTrace();
			 throw new CustomApplicationError (" Error in saving the record , kindly check if you sending a valid request ");
		 }
		 
	      return "{\"survivor_system_ref\": \""+survivor_system_ref+"\"}"; 
		 
 }
	
      

	 
	 private static MongoCollection getCollection() {
		 return MongoDB.getMongoDatabase().getCollection("survivor");
	 }
	 
		/**
	    * 
	    * @return List all the survivors in the database
	    */
	 public static String  listAllSurvivors() {
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
	 
		/**
	    * 
	    * @return List all infected  survivors in the database
	    */
	 public static String  listInfectedSurvivors() {
		 StringWriter sb = new StringWriter();
		 sb.append("[");

		 boolean first = true;
         FindIterable<Document> docs = getCollection().find(new Document("infection_status", "infected"));
         for (Document d : docs) {
             if (!first) sb.append(",");
             else first = false;
             sb.append(d.toJson());
         }
         sb.append("]");
         
         return sb.toString();
  
	 }
	 
		/**
	    * 
	    * @return List all non-infected  survivors in the database
	    */
	 public static String  listNonInfectedSurvivors() {
		 StringWriter sb = new StringWriter();
		 sb.append("[");

		 boolean first = true;
         FindIterable<Document> docs = getCollection().find(new Document("infection_status", "non-infected"));
         for (Document d : docs) {
             if (!first) sb.append(",");
             else first = false;
             sb.append(d.toJson());
         }
         sb.append("]");
         
         return sb.toString();
  
	 }
		/**
	    * @param name of the survivor
	    * @return survivor details
	    */
	 public static String  findSurvivorByName(String name) throws CustomApplicationError {
		
		 Document docs = (Document) getCollection().find(new Document("name", name)).first();
		 if(docs !=null) {
			 return docs.toJson().toString(); 
		 }else {
			throw new CustomApplicationError (" Document not Found ");
		 }
		
	
	 }
	 
		/**
		* Check if survivor exists
	    * @param survivor id
	    * @return boolean 
	    */ 
	 public static boolean  survivoridExists(String id) throws CustomApplicationError {
			
		 Document docs = (Document) getCollection().find(new Document("id", id)).first();
		 if(docs !=null) {
			 return true;
		 }
		 return false;
		
	
	 }
		/**
		* Check if survivor assigned system ref exists
	    * @param survivor_system_ref
	    * @return boolean 
	    */ 
	 
	 public static boolean  survivorsystemRefExists(String survivor_system_ref) throws CustomApplicationError {
			
		 Document docs = (Document) getCollection().find(new Document("survivor_system_ref", survivor_system_ref)).first();
		 if(docs !=null) {
			 return true;
		 }
		 return false;
		
	
	 }
	 
	   /**
		* Check and return survivor details 
	    * @param survivor_system_ref
	    * @return survivor details 
	    */ 
	 
	 public static String  findSurvivorSystemRef(String survivor_system_ref) throws CustomApplicationError {
			
		 Document docs = (Document) getCollection().find(new Document("survivor_system_ref", survivor_system_ref)).first();
		 if(docs !=null) {
			 return docs.toJson().toString(); 
		 }else {
			throw new CustomApplicationError (" Document not Found ");
		 }
		
	
	 }
	 
	   /**
		* Updates a survivor details  
	    * @param survivor_system_ref and update details
	    * @return success if update went ok
	    */ 
	 
	 public static String  UpdateSurvivor(String survivor_system_ref,survivorEntity updatesurvivorJson) throws CustomApplicationError {

		   if(!survivorsystemRefExists(survivor_system_ref)){
			   	 throw new CustomApplicationError (" Invalid survivor_system_res: The reference does not exist ");
           }
			
         try {
        	 MongoCollection<Document> SurvivorCollection = getCollection();
        	 Bson findQuerry =  new Document("survivor_system_ref", survivor_system_ref);
        	 Document updateFields =  new Document();
        	 Document setQuerry =  new Document();
        	 Document addTosetQuerry =  new Document();
        	    if(updatesurvivorJson.getId() != null) {
        	    	updateFields.append("id", updatesurvivorJson.getId());
        	    }
        	    if(updatesurvivorJson.getAge() !=0) {
        	    	updateFields.append("age", updatesurvivorJson.getAge());
        	    }
        	    if(updatesurvivorJson.getName() != null) {
        	    	updateFields.append("name", updatesurvivorJson.getName());
        	    }
         	    if(updatesurvivorJson.getGender() != null) {
   
        	    	updateFields.append("gender", updatesurvivorJson.getGender());
        	    }
         	    
	         	if(updatesurvivorJson.getLast_location().size() > 0) {    
	         		  updateFields.append("Last_location", updatesurvivorJson.getLast_location());
	         		  updateFields.append("location", new Document("type", "point")
	  	                         .append("coordinates", updatesurvivorJson.getLast_location()));
	         	 }
	         	
	         	setQuerry.append("$set", updateFields);
	         	 
	         	
	         	 if(updatesurvivorJson.getResources().length > 0) {    
	         	 	addTosetQuerry.append("resources", new Document("$each" ,Arrays.asList(updatesurvivorJson.getResources())));
	         	 	 setQuerry.append("$addToSet", addTosetQuerry);
	         	 }
	         	 

	    
	         SurvivorCollection.findOneAndUpdate(findQuerry, setQuerry) ; 
        	 
         }
         catch (Exception e) {
        	 e.printStackTrace();
        	 throw new CustomApplicationError (" Unknown Error : an unknown error has occured ,kindly report it ");
         }
		
	     return "{\"update_status\": \"success\"}";
	 }
	 
	   /**
		* saves a contamination report 
	    * @param survivor_system_ref and contamination details
	    * @return success if database update was successful 
	    */ 

	 public static String  reportContamination(String survivor_system_ref,contamination contaminationJson) throws CustomApplicationError {
		 
		 Document doc = (Document) getCollection().find(new Document("survivor_system_ref", survivor_system_ref)).first();
		 if(doc !=null) {
			 Document updateFields =  new Document(); 
        	 Document setQuerry =  new Document();
        	 Document addTosetQuerry =  new Document();
        	 Document incQuerry =  new Document();
        	
			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  
			  String contamination_status="non-infected";
			   if(doc.get("contamination_reported") != null) {
				 
				   if(DocCount((ArrayList) (doc.get("contamination_reported"))) > 1) {
					   contamination_status="infected";
				   }
				  
						 
				   
			   }
			   updateFields.append("infection_status", contamination_status);  	 
			 
		   	 MongoCollection<Document> SurvivorCollection = getCollection();
        	 Bson findQuerry =  new Document("survivor_system_ref", survivor_system_ref);
        

                  	 addTosetQuerry.append("contamination_reported", new Document("reporter" ,contaminationJson.getReporter())
           				                                          .append("robot_serial_number",contaminationJson.getRobot_serial_number())
           				                                          .append("report_date", dtf.format(now))
           				                                          .append("report_details",contaminationJson.getReport_details()));
           				                                       
                    	setQuerry.append("$set", updateFields);                                         
	         	 	    setQuerry.append("$addToSet", addTosetQuerry);
	         	 
	         	 	 SurvivorCollection.findOneAndUpdate(findQuerry, setQuerry) ; 
			 
		 }else {
			throw new CustomApplicationError (" survivor Document not Found ");
		 }
		 
		 return "{\"contamination_status\": \"success\"}";
	 
	 }
	 
	 private static int DocCount (ArrayList docs) {
		 int count = 0;
		 
		 for(int i = 0; i < docs.size(); i++) {
			 count++;
		 }
	 
		  return count;
	 }
	 
		 
}
