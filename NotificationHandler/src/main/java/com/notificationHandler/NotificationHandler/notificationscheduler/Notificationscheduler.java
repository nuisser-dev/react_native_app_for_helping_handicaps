package com.notificationHandler.NotificationHandler.notificationscheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.notificationHandler.NotificationHandler.entitys.Reminder;
import com.notificationHandler.NotificationHandler.entitys.Reminder_user;
import com.notificationHandler.NotificationHandler.repository.NotificationRepository;
import com.notificationHandler.NotificationHandler.service.FirebaseService;


@Component
public class Notificationscheduler {
	@Autowired
	FirebaseService firebaseservice;
	@Autowired
	NotificationRepository repos;
	
	@Scheduled(fixedRate = 1000)
	public void sendReminder() {
	    System.out.println("Scheduler started at " + LocalDateTime.now());
	    List<Reminder_user> usr = repos.findAll();
	    LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

	    if (usr != null && !usr.isEmpty()) {
	        try {
	            for (Reminder_user user : usr) {
	                System.out.println("user: " + user.getIdUser());
	                if (user.getReminder() != null && !user.getReminder().isEmpty()) {
	                    List<Reminder> remindersToRemove = new ArrayList<>();
	                    for (Reminder reminder : user.getReminder()) {
	                        LocalDateTime reminderDate = reminder.getReminderDate().truncatedTo(ChronoUnit.MINUTES);
	                        System.out.println("Checking reminder: " + reminder + " with date: " + reminderDate);
	                        System.out.println("Current time: " + now);
	                        if (reminderDate.compareTo(now)==0) {
	                            firebaseservice.sendNotification(reminder.getTitle(), reminder.getContent(), user.getDevicetoken());
	                            remindersToRemove.add(reminder);
	                            System.out.println("Reminder sent and marked for removal: " + reminder + " with date: " + reminderDate);
	                        }
	                    }
	                    if (!remindersToRemove.isEmpty()) {
	                        user.getReminder().removeAll(remindersToRemove);
	                        repos.save(user);
	                    }
	                } else {
	                    System.out.println("No reminders for user " + user.getIdUser());
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error: " + e.getMessage());
	        }
	    } else {
	        System.out.println("No reminders found in the database");
	    }
	}
	}
	
	
	
	
	
	
	
	
	
	
