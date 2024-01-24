package com.afrikatek.registrationservice.repository;

import com.afrikatek.registrationservice.domain.Dependant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependantRepository extends JpaRepository<Dependant, Long> {
}
