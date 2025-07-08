package com.dailyreminder.reminder.controller;

import com.dailyreminder.reminder.model.Reminder;
import com.dailyreminder.reminder.service.ReminderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public Reminder addReminder(@RequestBody Reminder reminder) {
        return reminderService.save(reminder);
    }

    @GetMapping
    public List<Reminder> getAllReminder() {
        return reminderService.getAll();
    }



    @GetMapping("/trigger")
    public String triggerManually() {
        reminderService.sendReminderEmails();
        return "Triggered manually!";
    }
}
