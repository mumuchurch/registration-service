package com.afrikatek.registrationservice.repository;

import com.afrikatek.registrationservice.domain.Congregant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CongregantRepository extends CrudRepository<Congregant, Long> {
    Optional<Congregant> findCongregantByEmail(String email);
}
