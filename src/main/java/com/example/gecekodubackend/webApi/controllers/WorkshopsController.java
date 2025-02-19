package com.example.gecekodubackend.webApi.controllers;

import com.example.gecekodubackend.business.abstracts.WorkshopService;
import com.example.gecekodubackend.entity.dtos.workshop.CreateWorkshopDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webApi/workshops")
public class WorkshopsController {

    private final WorkshopService workshopService;

    @Autowired
    public WorkshopsController(WorkshopService workshopService) {
        super();
        this.workshopService = workshopService;
    }

    @GetMapping("/getAllWorkshops")
    public ResponseEntity<?> getAllWorkshops() {
        var result = workshopService.getAllWorkshops();

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getWorkshopById/{workshopId}")
    public ResponseEntity<?> getWorkshopById(@PathVariable int workshopId) {
        var result = workshopService.getWorkshopById(workshopId);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addWorkshop")
    public ResponseEntity<?> addWorkshop(@Valid @RequestBody CreateWorkshopDto createWorkshopDto) {
        var result = workshopService.addWorkshop(createWorkshopDto);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateWorkshop/{id}")
    public ResponseEntity<?> updateWorkshop(@PathVariable(name = "id") int id, @Valid @RequestBody CreateWorkshopDto createWorkshopDto) {
        var result = workshopService.updateWorkshop(createWorkshopDto, id);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteWorkshop/{id}")
    public ResponseEntity<?> deleteWorkshop(@PathVariable int id) {
        var result = workshopService.deleteWorkshop(id);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getWorkshopByName/{workshopName}")
    public ResponseEntity<?> getWorkshopByName(@PathVariable String workshopName) {
        var result = workshopService.getWorkshopByName(workshopName);

        if (result.isSuccess()) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
