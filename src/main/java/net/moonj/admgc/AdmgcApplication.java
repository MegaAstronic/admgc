package net.moonj.admgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AdmgcApplication {

	private static ConfigurableApplicationContext ctx ; 
	public static void main(String[] args) {
		ctx =SpringApplication.run(AdmgcApplication.class, args);
		
	}
	
	public static ConfigurableApplicationContext getCtx() {
		return ctx;
	}
}
