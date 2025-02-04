package com.example.gecekodubackend.entity.concretes;

import com.example.gecekodubackend.core.entities.*;
import com.example.gecekodubackend.entity.abstracts.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

import static com.example.gecekodubackend.business.constants.WorkshopMessages.*;

@Data
@Table(name = "workshops")
@jakarta.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workshop implements Entity {
    @Id
    @Column(name = "workshop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workshopId;

    @NotEmpty(message = "Çalışma adı boş olamaz!")
    @Size(min = 0, max = 50)
    @Column(name = "workshop_name")
    private String workshopName;

    @Size(min = 0, max = 50)
    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @ManyToMany(mappedBy = "workshops")
    private Set<User> users;
}
