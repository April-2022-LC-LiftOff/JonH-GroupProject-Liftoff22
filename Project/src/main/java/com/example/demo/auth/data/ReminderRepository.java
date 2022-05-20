package com.example.demo.auth.data;

import com.example.demo.auth.models.Reminder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends CrudRepository<Reminder, Integer> {
    List<Reminder> findAll();


}
