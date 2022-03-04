package apocalypse.services;

/**
 * @author robertnjoroge
 */

import apocalypse.dao.reportDao;
import apocalypse.dao.survivorDao;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;



/**
*
* api service to handle reports api requests
*/



public class report {
	
	
    public static HttpHandler reportSummary()  {
        return (HttpServerExchange exchange) -> {
        	
          	try {
              
                ResponseHandler.successResponse(exchange,reportDao.reportSummary());
            	}catch (Exception e) {
            		 e.printStackTrace();
            		 ResponseHandler.errorResponse(exchange, e);
            	}
        };
    }
    
    
    public static HttpHandler infected() {
        return (HttpServerExchange exchange) -> {
        	try {
        		ResponseHandler.successResponse(exchange,survivorDao.listInfectedSurvivors());
        	}
        	catch (Exception e) {
        		e.printStackTrace();
       		    ResponseHandler.errorResponse(exchange, e);
        	}
        };
    }
    
    public static HttpHandler noninfected() {
        return (HttpServerExchange exchange) -> {
        	try {
        		ResponseHandler.successResponse(exchange,survivorDao.listNonInfectedSurvivors());
        	}
        	catch (Exception e) {
        		e.printStackTrace();
       		    ResponseHandler.errorResponse(exchange, e);
        	}
        };
    }

}
