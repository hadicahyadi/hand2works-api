package com.curiouslabs.hand2works.config;

import static spark.Spark.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.curiouslabs.hand2works.controller.BrandController;

public class WebConfig {
	
	private AnnotationConfigApplicationContext ctx;

	public WebConfig(AnnotationConfigApplicationContext ctx) {
		this.ctx = ctx;
		// setup port
		port(8080);
		// enable CORS
		enableCORS("*", "GET,PUT,POST,OPTIONS", "Content-Type, Accept,Origin, X-Requested-With, remember-me,Content-Disposition");
		// setup routes api
		setupRoutes();
	}
	
	// Enables CORS on requests. This method is an initialization method and should be called once.
	private void enableCORS(final String origin, final String methods, final String headers) {

	    options("/*", (request, response) -> {

	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }

	        return "OK";
	    });

	    before((request, response) -> {
	        response.header("Access-Control-Allow-Origin", origin);
	        response.header("Access-Control-Request-Method", methods);
	        response.header("Access-Control-Allow-Headers", headers);
	    });
	}
	
	/**
	 * registering routes api by initialize each controller class
	 * pass AnnotationConfigApplicationContext to controller constructor param
	 */
	private void setupRoutes() {
		new BrandController(ctx);
	}

}
