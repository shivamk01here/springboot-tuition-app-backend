package com.example.tution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//its a annotation @configuration means this class as a source of bean definition
// @enableAutoConfiguration : enable springboot auto configuration mechanism
// @componentscan : enable springboot to create bean for ths class

// under the hood springboot creats a applicationcontext ( ioc container ) that manages all beans and their lifecyacle


@SpringBootApplication
public class TutionApplication {

	public static void main(String[] args) {

        // springapplication . run does the following
        //1. create application context
        //2. scan for @component, @service @repository, @controller annotation
        //3. crate beans and manage dependecy
        //4. starts embedded tomcat server
        //5. intialize all auto configured component
        SpringApplication.run(TutionApplication.class, args);
	}

}
