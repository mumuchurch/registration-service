package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.MarriageHistory;

public interface MarriageHistoryService {
    MarriageHistory addMarriageHistory(MarriageHistory marriageHistory, Long congregantId);
    MarriageHistory updateMarriageHistory(MarriageHistory marriageHistory, Long congregantId);
    void deleteMarriageHistory(Long congregantId);
}
