package com.notificationHandler.NotificationHandler;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
@EnableScheduling

public class NotificationHandlerApplication {
	
	
      
	
	
	
	
	 @Bean
	    public FirebaseApp initializeFirebase() throws IOException {
	        if (FirebaseApp.getApps().isEmpty()) { 
	            FileInputStream serviceAccount = new FileInputStream("src/main/resources/google-services.json");

	            FirebaseOptions options = FirebaseOptions.builder()
	                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	                    .build();

	            return FirebaseApp.initializeApp(options);
	        } else {
	            return FirebaseApp.getInstance(); 
	        }
	    }
	
	

	public static void main(String[] args) {
		SpringApplication.run(NotificationHandlerApplication.class, args);
	}

}
