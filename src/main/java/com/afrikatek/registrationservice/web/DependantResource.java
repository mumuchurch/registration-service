package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.Dependant;
import com.afrikatek.registrationservice.dto.DependantDTO;
import com.afrikatek.registrationservice.service.DependantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1.0/dependants")
public class DependantResource {
    private final DependantService dependantService;

    @GetMapping
    public ResponseEntity<Iterable<Dependant>> findAllDependents(){
        return ResponseEntity.status(HttpStatus.OK).body(dependantService.findAll());
    }

    @PostMapping
    public ResponseEntity<Dependant> createDependant(@RequestBody DependantDTO dependantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(dependantService.create(dependantDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dependant> updateDependant(@RequestBody DependantDTO dependantDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dependantService.update(dependantDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDependant(@PathVariable Long id){
        dependantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
