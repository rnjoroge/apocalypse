package apocalypse.datasource;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import io.github.cdimascio.dotenv.Dotenv;


        /**
		* apocalypse data repository ..choice of database is mongo database 
	    */ 

public class MongoDB {
	
    private static  MongoClient mongo;
  
  
  
    private static void init() {
        if ((mongo == null)) {
     	    
            Dotenv dotenv = null;
            dotenv = Dotenv.configure().load();
            
            // if running with docker compose
            String client_url = "mongodb://mongo-db:27017/apocalypse" ;
            if(dotenv.get("MONGO-URL") !=null) {
            	// running with mongo url defined
            	client_url=dotenv.get("MONGO-URL") ;
            }
            MongoClientURI uri = new MongoClientURI(client_url);
            mongo = new MongoClient(uri);
        }
    }

    
    public static MongoClient getClient () {
    	return mongo;
    }
    
    public static MongoDatabase getMongoDatabase() {
    	  if ((mongo == null)) {
    		  init();
    	  }
    	return MongoDB.getClient().getDatabase("apocalypse");
    }
    

    
    

}
