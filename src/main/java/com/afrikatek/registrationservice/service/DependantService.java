package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.Dependant;
import com.afrikatek.registrationservice.dto.DependantDTO;

public interface DependantService {
    Iterable<Dependant> findAll();
    Dependant findById(Long id);
    Dependant create(DependantDTO dependantDTO);
    Dependant update(DependantDTO dependantDTO, Long id);
    void deleteById(Long id);
}
