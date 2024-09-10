package com.notificationHandler.NotificationHandler.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FirebaseService {
	
	
	 public void sendNotification(String title, String body, String token) {
	        Notification notification = Notification.builder()
	                .setTitle(title)
	                .setBody(body)
	                .build();

	        Message message = Message.builder()
	                .setToken(token)
	                .setNotification(notification)
	                .build();

	        try {
	            String response = FirebaseMessaging.getInstance().send(message);
	            System.out.println("Successfully sent message: " + response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
