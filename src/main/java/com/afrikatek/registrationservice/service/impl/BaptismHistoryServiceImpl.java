package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.BaptismHistory;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.BaptismHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BaptismHistoryServiceImpl implements BaptismHistoryService {
    private final CongregantRepository congregantRepository;
    @Override
    public BaptismHistory addBaptismHistory(BaptismHistory baptismHistory, Long congregantId) {
        Congregant congregant = congregantRepository
                .findById(congregantId)
                .orElseThrow(() -> new EntityNotFoundException(congregantId, "Congregant"));
        congregant.setBaptismHistory(baptismHistory);
        Congregant saved = congregantRepository.save(congregant);
        return saved.getBaptismHistory();
    }

    @Override
    public BaptismHistory updateBaptismHistory(BaptismHistory baptismHistory, Long congregantId) {
        Congregant congregant = congregantRepository
                .findById(congregantId)
                .orElseThrow(() -> new EntityNotFoundException(congregantId, "Congregant"));
        congregant.setBaptismHistory(baptismHistory);
        Congregant saved = congregantRepository.save(congregant);
        return saved.getBaptismHistory();
    }

    @Override
    public void deleteBaptismHistory(Long congregantId) {
        Congregant congregant = congregantRepository
                .findById(congregantId)
                .orElseThrow(() -> new EntityNotFoundException(congregantId, "Congregant"));
        BaptismHistory baptismHistory = congregant.getBaptismHistory();
        baptismHistory.setCongregant(null);
        congregant.setBaptismHistory(null);
        congregantRepository.save(congregant);
    }
}
