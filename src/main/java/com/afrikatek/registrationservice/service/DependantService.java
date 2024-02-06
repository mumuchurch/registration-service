package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.Dependant;

public interface DependantService {
    Iterable<Dependant> findAll();
    Dependant findById(Long id);
    Dependant create(Dependant dependant);
    Dependant update(Dependant dependant, Long id);
    void deleteById(Long id);
}
