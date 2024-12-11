package com.example.gecekodubackend.entity.concretes;

import com.example.gecekodubackend.core.entities.*;
import com.example.gecekodubackend.entity.abstracts.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@Table(name = "events")
@jakarta.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Entity {
    @Id
    @Column(name="event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;

    @NotEmpty(message = "Çalışma adı boş olamaz!")
    @Size(min = 0, max = 50)
    @Column(name="event_name")
    private String eventName;

    @Size(min = 0, max = 50)
    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @ManyToMany(mappedBy = "events")
    private Set<User> users;
}