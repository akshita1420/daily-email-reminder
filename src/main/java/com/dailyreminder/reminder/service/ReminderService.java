package com.dailyreminder.reminder.service;

import com.dailyreminder.reminder.model.Reminder;
import com.dailyreminder.reminder.repository.ReminderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ReminderService {



    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private EmailService emailService;

    public Reminder save(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public List<Reminder> getAll() {
        return reminderRepository.findAll();
    }
    @Scheduled(fixedRate = 30000)
    public void sendReminderEmails() {
        LocalTime now = LocalTime.now().withSecond(0).withNano(0);
        List<Reminder> reminders = reminderRepository.findAll();
        System.out.println("working");

        for (Reminder reminder : reminders) {
            if (reminder.getRemainingDays() > 0 && reminder.getTime().equals(now)) {
                emailService.sendEmail(
                        reminder.getEmail(),
                        "Your Daily Reminder <3",
                        reminder.getMessage()
                );
                System.out.println("MAil sent");

                reminder.setRemainingDays(reminder.getRemainingDays() - 1);
                reminderRepository.save(reminder);

                System.out.println("Sent email to " + reminder.getEmail() +
                        ", remaining days: " + reminder.getRemainingDays());
            }
        }
    }
}
