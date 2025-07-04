package com.dailyreminder.reminder.service;

import com.dailyreminder.reminder.model.Reminder;
import com.dailyreminder.reminder.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    public Reminder save(Reminder reminder){
        return reminderRepository.save(reminder);
    }
    public List<Reminder> getAll(){
        return reminderRepository.findAll();

    }
}
