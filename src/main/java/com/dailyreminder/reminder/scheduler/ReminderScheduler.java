package com.dailyreminder.reminder.scheduler;

import com.dailyreminder.reminder.model.Reminder;
import com.dailyreminder.reminder.repository.ReminderRepository;
import com.dailyreminder.reminder.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ReminderScheduler {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * * * *") // every minute
    public void sendReminder() {
        LocalTime now = LocalTime.now().withSecond(0).withNano(0);
        List<Reminder> reminders = reminderRepository.findAll();

        for (Reminder reminder : reminders) {
            if (reminder.getRemainingDays() > 0 && reminder.getTime().equals(now)) {
                emailService.sendEmail(
                        reminder.getEmail(),
                        "Your Daily Reminder <3 ",
                        reminder.getMessage()
                );

                // Decrease day count
                reminder.setRemainingDays(reminder.getRemainingDays() - 1);
                reminderRepository.save(reminder);
                System.out.println("Sent email to " + reminder.getEmail() +
                        ", remaining days: " + reminder.getRemainingDays());
            }
        }
    }
}
