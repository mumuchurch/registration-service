package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.BaptismHistory;
import com.afrikatek.registrationservice.service.BaptismHistoryService;
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
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1.0/baptism-history")
public class BaptismHistoryResource {
    private final BaptismHistoryService baptismHistoryService;

    @PostMapping("/{congregantId}")
    public ResponseEntity<BaptismHistory> createBaptismHistory(@RequestBody BaptismHistory baptismHistory, @PathVariable Long congregantId) {
        log.debug("Request to add baptism history {} for congregant with id {}", baptismHistory, congregantId);
        BaptismHistory addedBaptismHistory = baptismHistoryService.addBaptismHistory(baptismHistory, congregantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBaptismHistory);
    }

    @PutMapping("/{congregantId}")
    public ResponseEntity<BaptismHistory> updateBaptismHistory(@RequestBody BaptismHistory baptismHistory, @PathVariable Long congregantId) {
        log.debug("Request to update baptism history {} for congregant with id {}", baptismHistory, congregantId);
        BaptismHistory updatedBaptismHistory = baptismHistoryService.updateBaptismHistory(baptismHistory, congregantId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBaptismHistory);
    }

    @DeleteMapping("/{congregantId}")
    public ResponseEntity<Void> deleteBaptismHistory(@PathVariable Long congregantId) {
        log.debug("Request to delete baptism history for congregant with id {}", congregantId);
        baptismHistoryService.deleteBaptismHistory(congregantId);
        return ResponseEntity.noContent().build();
    }
}
