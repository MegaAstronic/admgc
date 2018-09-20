package net.moonj.admgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"net.moonj.admgc.genecode.user.mapper","net.moonj.admgc.mapper"})
public class AdmgcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmgcApplication.class, args);
		
	}
}
