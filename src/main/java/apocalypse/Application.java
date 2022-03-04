package apocalypse;
/**
 * @author robertnjoroge
 */


import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;
import apocalypse.services.*;



/*
 * Main Application
 * Defines the rest api routes
 */

public class Application {
	


    public static void main(String[] args) {

    	
    	  RoutingHandler Handler =  new RoutingHandler()
    			  .post("/survivor/add", RoutingHandlers.addSurvivor())
    	          .get("/survivor/list",RoutingHandlers.listSurvivors())
    	          .get("/survivor/details/{survivor_system_ref}",RoutingHandlers.survivorDetails())
    	          //.get("/survivor/byName/{name}",RoutingHandlers.findSurvivorByName())
    	          .put("/survivor/update/{survivor_system_ref}",RoutingHandlers.updateSurvivor())
    	          
    	          
		    	  .post("/infection/report/{survivor_system_ref}", RoutingHandlers.reportInfection())
		    	  
		          .get("/robot/list",RoutingHandlers.listRobots())
		          .post("/robot/add",RoutingHandlers.addRobot())
		          .get("/robot/summary",RoutingHandlers.robotSummary())
		        
		          .get("/report/summary",RoutingHandlers.reportSummary())
		          .get("/report/infected",RoutingHandlers.infected())
		          .get("/report/non-infected",RoutingHandlers.noninfected())	          
		          
    	          .setFallbackHandler(RoutingHandlers.notFoundHandler());
    			  
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0",Handler)
                .build();
        server.start();
    }
}