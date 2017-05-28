package com.curiouslabs.hand2works;

import static spark.Spark.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.curiouslabs.hand2works.config.WebConfig;

@Configuration
@ComponentScan({ "com.curiouslabs.hand2works.*" })
public class Main {
	
	public static void main(String args[]) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
		new WebConfig(ctx);
		ctx.registerShutdownHook();
	}

}
