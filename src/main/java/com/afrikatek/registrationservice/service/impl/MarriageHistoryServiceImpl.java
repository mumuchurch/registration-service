package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.domain.MarriageHistory;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.MarriageHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MarriageHistoryServiceImpl implements MarriageHistoryService {
    private final CongregantRepository congregantRepository;
    @Override
    public MarriageHistory addMarriageHistory(MarriageHistory marriageHistory, Long congregantId) {
        Congregant congregant = getCongregant(congregantId);
        congregant.setMarriageHistory(marriageHistory);
        Congregant saved = congregantRepository.save(congregant);
        return saved.getMarriageHistory();
    }

    @Override
    public MarriageHistory updateMarriageHistory(MarriageHistory marriageHistory, Long congregantId) {
        Congregant congregant = getCongregant(congregantId);
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
