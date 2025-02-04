package com.example.gecekodubackend.dataAccess.abstracts;

import com.example.gecekodubackend.entity.concretes.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EventDao extends JpaRepository<Event, Integer> {
    boolean existsByEventName(String eventName);

    boolean existsByDate(Date date);
}
