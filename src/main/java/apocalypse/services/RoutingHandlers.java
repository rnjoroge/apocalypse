
package apocalypse.services;
/**
 * @author robertnjoroge
 */


import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class RoutingHandlers {


	/* Survivor Routes */	   
    public static HttpHandler addSurvivor() {
        return survivor.addSurvivor();
    }
    public static HttpHandler listSurvivors() {
        return survivor.listSurvivors();
    }
    public static HttpHandler survivorDetails() {
        return survivor.survivorDetails();
    }
    public static HttpHandler updateSurvivor() {
        return survivor.updateSurvivor();
    }
    public static HttpHandler findSurvivorByName() {
        return survivor.findSurvivorByname();
    }
    
    
	/* Infection Routes */	
    public static HttpHandler reportInfection() {
        return infection.reportInfection();
    }
  

    
	/* Report Routes */	
  
    public static HttpHandler reportSummary()  {
        return report.reportSummary();
    }
 
    public static HttpHandler infected() {
        return report.infected();
    }
    public static HttpHandler noninfected() {
        return report.noninfected();
    }
    
	/* Robot Routes */	
    
    public static HttpHandler listRobots() {
        return robot.listRobots();
    }
    public static HttpHandler addRobot() {
        return robot.addRobot();
    }
    public static HttpHandler robotSummary() {
        return robot.robotSummary();
    }
    
    
	/* Not found Route */	

    
    public static HttpHandler notFoundHandler() {
        return (HttpServerExchange exchange) -> { 
            exchange.setStatusCode(404);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send("{\"error\": \"Invalid , Api not found\"}");
        };
    }
    

    
}
  