package com.dailyreminder.reminder.repository;


import com.dailyreminder.reminder.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long>{

}
