package apocalypse.services;

/**
 * @author robertnjoroge
 */

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import apocalypse.dao.robotDao;
import apocalypse.entity.robotEntity;
import io.undertow.io.Receiver.FullBytesCallback;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;



/**
*
* api service to handle robot api requests
*/


public class robot {
	
	
	
    public static HttpHandler listRobots() {
        return (HttpServerExchange exchange) -> {
        	try {
        		ResponseHandler.successResponse(exchange,robotDao.listAllRobots());
        	}
        	catch (Exception e) {
        		e.printStackTrace();
       		    ResponseHandler.errorResponse(exchange, e);
        	}
            
        };
    }
    
	
    public static HttpHandler addRobot() {
        return (HttpServerExchange exchange) -> {
        	try {
       
                exchange.getRequestReceiver().receiveFullBytes(new FullBytesCallback() {
                    @Override
                    public void handle(HttpServerExchange exchange, byte[] message) {
                        System.out.println(new String(message));
                        
                        
                       	try {
                       	 ObjectMapper mapper = new ObjectMapper();
                       	List<robotEntity> robotJson = mapper.readValue(new String(message), new TypeReference<List<robotEntity>>(){});
                       		//List<robotEntity> robotJson = mapper.readValue(new String(message),robotListType);
				        	ResponseHandler.successResponse(exchange,robotDao.addRobots(robotJson));
						} catch (Exception e) {
					  		e.printStackTrace();
			        		 ResponseHandler.errorResponse(exchange, e);
						}   
                        
                        
                    }  
                });
                
                
            
        	}
        	catch (Exception e) {
        		 e.printStackTrace( );
        		 exchange.getResponseSender().send(e.getMessage());
        	}

            
        };
    }
    
    
    public static HttpHandler robotSummary() {
        return (HttpServerExchange exchange) -> {
         	try {
        		ResponseHandler.successResponse(exchange,robotDao.robotSummary());
        	}
        	catch (Exception e) {
        		e.printStackTrace();
       		    ResponseHandler.errorResponse(exchange, e);
        	}
            
            
        };
    }
    

    

}
