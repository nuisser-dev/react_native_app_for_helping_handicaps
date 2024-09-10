package com.notificationHandler.NotificationHandler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.notificationHandler.NotificationHandler.entitys.Reminder_user;



public interface NotificationRepository  extends MongoRepository<Reminder_user, String> {
	Reminder_user deleteByIdUser(String id);
	Reminder_user findByIdUser(String id);
}
