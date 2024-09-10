package com.notificationHandler.NotificationHandler.Contollers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notificationHandler.NotificationHandler.entitys.Notification;
import com.notificationHandler.NotificationHandler.entitys.Reminder;
import com.notificationHandler.NotificationHandler.entitys.Reminder_user;
import com.notificationHandler.NotificationHandler.repository.NotificationRepository;
import com.notificationHandler.NotificationHandler.service.FirebaseService;

@RestController

public class NotificationController {
	
	
	@Autowired
	FirebaseService firebaseservice;
	
	@Autowired
	NotificationRepository repos;
	
	@PostMapping("/send/{idUser}")
	public boolean sendNotification(@PathVariable String idUser ,@RequestParam String content) {
		
		Reminder_user rm=new Reminder_user();
		try {
			
			rm=repos.findByIdUser(idUser);
			if(rm != null)
			{
				firebaseservice.sendNotification("Admin Notificaton",content,rm.getDevicetoken());
				
				if(rm.getNotification()==null)
				{rm.setNotification(new ArrayList<>());}
				
				rm.getNotification().add(new Notification("Admin Notificaton",content));
				repos.save(rm);
				return true;
			}
			
			
		}
		catch(Exception e)
		{ e.printStackTrace();}
		
		return false;
		
		 
		
	}
	@PostMapping("/save_user")
	public String SaveReminder_user(@RequestBody  Reminder_user reminder) {
		Reminder_user usr=repos.findByIdUser(reminder.getIdUser());
		
		if(usr == null)
		{
		reminder.setNotification(new ArrayList<>());
		reminder.setReminder(new ArrayList<>());
		repos.save(reminder);
		 
		return "save succes";}
		
		return "user existe"; 
	}

	@PostMapping("/save_user/{id}")
	public String SaveReminder_(@PathVariable("id") String id, @RequestBody Reminder reminder) {
	    Reminder_user usr = repos.findByIdUser(id);
	    
	    if (usr.getReminder() == null) {
	        usr.setReminder(new ArrayList<Reminder>());
	    }
	    System.out.println(reminder.getReminderDate());
	    usr.getReminder().add(reminder);
	    repos.save(usr);

	    return "save success";
	}
	
	@DeleteMapping("/delete_user_reminder/{id}")
	 public String delete_user_reminder(@PathVariable("id")String id ,@RequestBody Reminder reminder)
	 {
		System.out.println("aaa"+reminder);
		
		try
		{
		Reminder_user usr=repos.findByIdUser(id);
		if(usr!=null)
		{
			
			usr.getReminder().remove(reminder);
			repos.save(usr);
			return "reminder deleted with succes";
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			
		}
		
		return "error" ;
		
		
	 }
	
	@GetMapping("/get_user_reminder/{id}")
	
	public List<Reminder> reminder_user_by_id(@PathVariable("id") String id){
		Reminder_user usr= new Reminder_user();
		List<Reminder> lst = new ArrayList<>();
		
		
		
		try
		{
			
			usr=repos.findByIdUser(id); 
		if(usr != null) {
			if( !usr.getReminder().isEmpty())
			{
				return usr.getReminder();
				
				
			}
			
			
			
		}
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		
		
		return lst;
	}
	
	@GetMapping("/getNotificationByid/{idUser}")
	
	public List<Notification> getAllNotificationById(@PathVariable String idUser){
		
		Reminder_user usr=new Reminder_user();
		List<Notification> notif =new ArrayList<>();
		try {
			
			usr=repos.findByIdUser(idUser);
			if(usr.getNotification()!=null)
			{
				return usr.getNotification();
			}
			else 
				return notif;
			
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return notif;
		
		
		
	}
	
	@DeleteMapping("/deleteNotification/{idUser}")
	 public void deleteNotificationByidUser(@PathVariable("idUser") String idUser, @RequestBody  Notification nf) {
		
		try {
			Reminder_user usr=repos.findByIdUser(idUser);
			
			usr.getNotification().remove(nf);
			repos.save(usr);
			
			
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		
		
		
		
	}
	
}
