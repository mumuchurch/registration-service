package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.MarriageHistory;
import com.afrikatek.registrationservice.repository.MarriageHistoryRepository;
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

@RestController
@RequestMapping("/api/v1.0/marriage-histories")
@AllArgsConstructor
@Slf4j
public class MarriageHistoryResource {
    private final MarriageHistoryRepository marriageHistoryRepository;

    @PostMapping
    public ResponseEntity<MarriageHistory> createMarriageHistory(@RequestBody MarriageHistory marriageHistory){
        return ResponseEntity.status(HttpStatus.CREATED).body(marriageHistoryRepository.save(marriageHistory));
    }

    @PutMapping
    public ResponseEntity<MarriageHistory> updateMarriageHistory(@RequestBody MarriageHistory marriageHistory){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(marriageHistoryRepository.save(marriageHistory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarriageHistory(@PathVariable Long id){
        marriageHistoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
