package apocalypse.services;

/**
 * @author robertnjoroge
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import apocalypse.dao.survivorDao;
import apocalypse.entity.contamination;

import io.undertow.io.Receiver.FullBytesCallback;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;



/**
*
* api service to handle infection api requests
*/


public class infection  {
	

	/**
	*
	* api service to handle a report infection api
	* @param survivor_system_ref the ref of an already existing survivor assigned by system of registration
	*/
    public static HttpHandler reportInfection() {
        return (HttpServerExchange exchange) -> {
        	try { 
                exchange.getRequestReceiver().receiveFullBytes(new FullBytesCallback() {
                    @Override
                    public void handle(HttpServerExchange exchange, byte[] message) {
                        try {
                            
                    		  String survivor_system_ref = exchange.getQueryParameters().get("survivor_system_ref").getFirst();
                        	
                      		contamination contaminationJson = new ObjectMapper().readValue(new String(message), contamination.class);
      			        	ResponseHandler.successResponse(exchange,survivorDao.reportContamination(survivor_system_ref,contaminationJson));
                        }
                        catch (Exception e) {
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


}
