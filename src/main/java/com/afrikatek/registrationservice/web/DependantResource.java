package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.Dependant;
import com.afrikatek.registrationservice.repository.DependantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final DependantRepository dependantRepository;

    @GetMapping
    public ResponseEntity<Page<Dependant>> findAllDependents(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(dependantRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Dependant> createDependant(@RequestBody Dependant dependant){
        return ResponseEntity.status(HttpStatus.CREATED).body(dependantRepository.save(dependant));
    }

    @PutMapping
    public ResponseEntity<Dependant> updateDependant(@RequestBody Dependant dependant){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dependantRepository.save(dependant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDependant(@PathVariable Long id){
        dependantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
