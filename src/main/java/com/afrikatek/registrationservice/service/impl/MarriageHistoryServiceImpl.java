package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.domain.MarriageHistory;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.MarriageHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
public class MarriageHistoryServiceImpl implements MarriageHistoryService {
    private final CongregantRepository congregantRepository;
    @Override
    public MarriageHistory addMarriageHistory(MarriageHistory marriageHistory, Long congregantId) {
        marriageHistory.setCreatedDate(Instant.now());
        Congregant congregant = getCongregant(congregantId);
        congregant.setMarriageHistory(marriageHistory);
        Congregant saved = congregantRepository.save(congregant);
        return saved.getMarriageHistory();
    }

    @Override
    public MarriageHistory updateMarriageHistory(MarriageHistory marriageHistory, Long congregantId) {
        Congregant congregant = getCongregant(congregantId);
        MarriageHistory foundMarriageHistory = congregant.getMarriageHistory();
        marriageHistory.setCreatedDate(foundMarriageHistory.getCreatedDate());
        congregant.setMarriageHistory(marriageHistory);
        Congregant saved = congregantRepository.save(congregant);
        return saved.getMarriageHistory();
    }

    @Override
    public void deleteMarriageHistory(Long congregantId) {
        Congregant congregant = getCongregant(congregantId);
        congregant.setMarriageHistory(null);
        congregantRepository.save(congregant);
    }

    private Congregant getCongregant(Long congregantId) {
        return congregantRepository.findById(congregantId).orElseThrow(() -> new EntityNotFoundException(congregantId, "Congregant"));
    }
}
