package com.example.gecekodubackend.dataAccess.abstracts;

import com.example.gecekodubackend.entity.concretes.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopDao extends JpaRepository<Workshop,Integer> {
    boolean existsByWorkshopName(String workshopName);
}
