package com.notificationHandler.NotificationHandler.entitys;

import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Document
public class Reminder_user {
	
	
	@Id
	private String id;
	
	private String devicetoken;
   
	private String idUser;
	
	
	private List<Notification> notification;
	
	private List<Reminder> reminder;

	
	

}
