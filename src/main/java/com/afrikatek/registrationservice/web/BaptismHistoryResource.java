package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.BaptismHistory;
import com.afrikatek.registrationservice.repository.BaptismHistoryRepository;
import com.afrikatek.registrationservice.service.BaptismHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/baptism-histories")
@AllArgsConstructor
public class BaptismHistoryResource {
    private final BaptismHistoryRepository baptismHistoryRepository;
    private final BaptismHistoryService baptismHistoryService;

    @GetMapping
    public ResponseEntity<Iterable<BaptismHistory>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(baptismHistoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaptismHistory> getBaptismHistory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(baptismHistoryService.findBaptismHistory(id));
    }

    @PostMapping
    public ResponseEntity<BaptismHistory> createBaptismHistory(@RequestBody BaptismHistory baptismHistory){
        return ResponseEntity.status(HttpStatus.CREATED).body(baptismHistoryService.createBaptismHistory(baptismHistory));
    }

    @PutMapping
    public ResponseEntity<BaptismHistory> updateBaptismHistory(@RequestBody BaptismHistory baptismHistory){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(baptismHistoryService.updateBaptismHistory(baptismHistory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaptismHistory(@PathVariable Long id){
        baptismHistoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
