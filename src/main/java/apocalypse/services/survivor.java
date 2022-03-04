package apocalypse.services;
/**
 * @author robertnjoroge
 */


import io.undertow.io.Receiver.FullBytesCallback;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import com.fasterxml.jackson.databind.ObjectMapper;

import apocalypse.dao.survivorDao;
import apocalypse.entity.survivorEntity;
import apocalypse.util.CustomApplicationError;





/**
*
* api service to handle robot api requests
*/

public class survivor {
	

	
    public static HttpHandler addSurvivor() {
        return (HttpServerExchange exchange) -> {
        	try {  
                exchange.getRequestReceiver().receiveFullBytes(new FullBytesCallback() {
                    @Override
                    public void handle(HttpServerExchange exchange, byte[] message) {
                    	try {
							survivorEntity survivorEntity = new ObjectMapper().readValue(new String(message), survivorEntity.class);
				        	ResponseHandler.successResponse(exchange,survivorDao.addSurvivor(survivorEntity));
						} catch (Exception e) {
					  		e.printStackTrace();
			        		 ResponseHandler.errorResponse(exchange, e);
						} 
            
                    }  
                });
                
                
               
        	}
        	catch (Exception e) {
          		e.printStackTrace();
       		     ResponseHandler.errorResponse(exchange, e);
        	}

            
        };
    }
    
    
	
    public static HttpHandler listSurvivors() {
        return (HttpServerExchange exchange) -> {
        	try {
        		ResponseHandler.successResponse(exchange,survivorDao.listAllSurvivors());
        	}
        	catch (Exception e) {
        		e.printStackTrace();
       		    ResponseHandler.errorResponse(exchange, e);
        	}
            
         
        };
    }
    
    public static HttpHandler findSurvivorByname() {
        return (HttpServerExchange exchange) -> {
        	try {
            String name = exchange.getQueryParameters().get("name").getFirst();
            ResponseHandler.successResponse(exchange,survivorDao.findSurvivorByName(name));
        	}catch (Exception e) {
        		e.printStackTrace();
        		 ResponseHandler.errorResponse(exchange, e);
        	}
         };
    }
    
    
    public static HttpHandler survivorDetails() {
        return (HttpServerExchange exchange) -> {
          	try {
                String survivor_system_ref = exchange.getQueryParameters().get("survivor_system_ref").getFirst();
                   if(survivor_system_ref==null) {
                	   throw new CustomApplicationError (" Invalid Request  , the survivor_system_ref is required  ");
                   }
                
                ResponseHandler.successResponse(exchange,survivorDao.findSurvivorSystemRef(survivor_system_ref));
            	}catch (Exception e) {
            		e.printStackTrace();
            		 ResponseHandler.errorResponse(exchange, e);
            	}
        };
    }
    
    
    
    public static HttpHandler updateSurvivor() {
        return (HttpServerExchange exchange) -> {
        	try {  
                exchange.getRequestReceiver().receiveFullBytes(new FullBytesCallback() {
                    @Override
                    public void handle(HttpServerExchange exchange, byte[] message) {
                    	try {
                    		  String survivor_system_ref = exchange.getQueryParameters().get("survivor_system_ref").getFirst();
                    	
							survivorEntity updatesurvivorEntity = new ObjectMapper().readValue(new String(message), survivorEntity.class);
				        	ResponseHandler.successResponse(exchange,survivorDao.UpdateSurvivor(survivor_system_ref,updatesurvivorEntity));
						} catch (Exception e) {
					  		e.printStackTrace();
			        		 ResponseHandler.errorResponse(exchange, e);
						} 
            
                    }  
                });
                
                
               
        	}
        	catch (Exception e) {
          		e.printStackTrace();
       		     ResponseHandler.errorResponse(exchange, e);
        	}

            
        };

}
}
