package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Dependant;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.DependantRepository;
import com.afrikatek.registrationservice.service.DependantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DependantServiceImpl implements DependantService {
    private final DependantRepository dependantRepository;
    @Override
    public Iterable<Dependant> findAll() {
        return dependantRepository.findAll();
    }

    @Override
    public Dependant findById(Long id) {
        return dependantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Dependant"));
    }

    @Override
    public Dependant create(Dependant dependant) {
        return dependantRepository.save(dependant);
    }

    @Override
    public Dependant update(Dependant dependant, Long id) {
        findById(id);
        return dependantRepository.save(dependant);
    }

    @Override
    public void deleteById(Long id) {
        dependantRepository.deleteById(id);
    }
}
