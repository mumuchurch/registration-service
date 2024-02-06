package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.BaptismHistory;

public interface BaptismHistoryService {
    BaptismHistory addBaptismHistory(BaptismHistory baptismHistory, Long congregantId);
    BaptismHistory updateBaptismHistory(BaptismHistory baptismHistory, Long id);
    void deleteBaptismHistory(Long id);
}
