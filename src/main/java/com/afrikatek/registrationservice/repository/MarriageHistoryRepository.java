package com.afrikatek.registrationservice.repository;

import com.afrikatek.registrationservice.domain.MarriageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarriageHistoryRepository extends JpaRepository<MarriageHistory, Long> {
}
