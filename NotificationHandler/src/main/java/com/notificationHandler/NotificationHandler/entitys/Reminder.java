package com.notificationHandler.NotificationHandler.entitys;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @NoArgsConstructor @AllArgsConstructor
public class Reminder {
	private String content;
	private String title;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private LocalDateTime ReminderDate;
}



