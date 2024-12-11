package com.example.gecekodubackend.dataAccess.abstracts;

import com.example.gecekodubackend.entity.concretes.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<Event,Integer> {
    /*
  @Query("Select new com.example.gecekodubackend.entity.dtos.event.getEventsDto(" +
            "p.eventId, p.eventName, p.description, p.date) " +
            "From User u Inner Join u.events p")
    List<getEventsDto> getAllEventsWithDto();
  */
}
