package com.dailyreminder.reminder.controller;

import com.dailyreminder.reminder.model.Reminder;
import com.dailyreminder.reminder.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;

@Controller
public class FormController {

    @Autowired
    private ReminderService reminderService;

    @GetMapping("/")
    public String showForm() {
        return "reminder-form";
    }

    @PostMapping("/reminders/form")
    public String submitForm(@RequestParam String email,
                             @RequestParam String message,
                             @RequestParam String time,
                             @RequestParam int days,
                             Model model) {

        Reminder reminder = new Reminder();
        reminder.setEmail(email);
        reminder.setMessage(message);
        reminder.setTime(LocalTime.parse(time));
        reminder.setRemainingDays(days);
        reminder.setSent(false);

        reminderService.save(reminder);
        model.addAttribute("success", true);
        return "reminder-form";
    }
}
