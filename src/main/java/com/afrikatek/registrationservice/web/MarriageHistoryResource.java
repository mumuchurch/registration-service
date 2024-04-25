package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.MarriageHistory;
import com.afrikatek.registrationservice.service.MarriageHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1.0/marriage-history")
public class MarriageHistoryResource {
    private final MarriageHistoryService marriageHistoryService;

    @PostMapping("/{congregantId}")
    public ResponseEntity<MarriageHistory> addMarriageHistory(@RequestBody MarriageHistory marriageHistory, @PathVariable Long congregantId) {
        log.debug("Request to save marriage history {} for congregant with Id {}", marriageHistory, congregantId);
        MarriageHistory savedMarriageHistory = marriageHistoryService.addMarriageHistory(marriageHistory, congregantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMarriageHistory);
    }

    @PutMapping("/{congregantId}")
    public ResponseEntity<MarriageHistory> updateMarriageHistory(@RequestBody MarriageHistory marriageHistory, @PathVariable Long congregantId) {
        log.debug("Request to update marriage history {} for congregant with Id {}", marriageHistory, congregantId);
        MarriageHistory updateMarriageHistory = marriageHistoryService.updateMarriageHistory(marriageHistory, congregantId);
        return ResponseEntity.status(HttpStatus.OK).body(updateMarriageHistory);
    }

    @DeleteMapping("/{congregantId}")
    public ResponseEntity<Void> deleteMarriageHistory(@PathVariable Long congregantId) {
        log.debug("Request to delete marriage history for congregant with id {}", congregantId);
        marriageHistoryService.deleteMarriageHistory(congregantId);
        return ResponseEntity.noContent().build();
    }
}
