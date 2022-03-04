package apocalypse.services;

/**
 * @author robertnjoroge
 */

import org.json.JSONObject;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class ResponseHandler {
	
	public static void successResponse(HttpServerExchange exchange,String data) {
        exchange.setStatusCode(200);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
      /*
               JSONObject SuccessResponse = new JSONObject();
                   SuccessResponse.put("status", "success");
                   SuccessResponse.put("data", data);
        
               exchange.getResponseSender().send(SuccessResponse.toString());
       */
        
        exchange.getResponseSender().send("{\"status\":\"success\",\"data\":"+data+"}");
	}
	
	public static void errorResponse(HttpServerExchange exchange,Exception Error) {
		
	
        exchange.setStatusCode(502);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        JSONObject errResponse = new JSONObject();
                   errResponse.put("status","error");
                   JSONObject errdetails = new JSONObject();
                   errdetails.put("error_code",Error.getMessage());
                   errdetails.put("error_message",Error.getMessage());
                   errResponse.put("error",errdetails);
        
        exchange.getResponseSender().send(errResponse.toString());
	}
}



