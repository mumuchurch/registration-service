package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.BaptismHistory;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.BaptismHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BaptismHistoryService {
    private final BaptismHistoryRepository baptismHistoryRepository;

    public Iterable<BaptismHistory> findAll() {
        return baptismHistoryRepository.findAll();
    }

    public BaptismHistory findBaptismHistory(Long id) {
        return baptismHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "BaptismHistory"));
    }

    public BaptismHistory createBaptismHistory(BaptismHistory baptismHistory) {
        return baptismHistoryRepository.save(baptismHistory);
    }

    public BaptismHistory updateBaptismHistory(BaptismHistory baptismHistory) {
        baptismHistoryRepository.findById(baptismHistory.id())
                .orElseThrow(() -> new EntityNotFoundException(baptismHistory.id(), "BaptismHistory"));
        return baptismHistoryRepository.save(baptismHistory);
    }

    public void deleteBaptismHistory(Long id) {
        findBaptismHistory(id);
        baptismHistoryRepository.deleteById(id);
    }
}
