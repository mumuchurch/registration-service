package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.domain.Dependant;
import com.afrikatek.registrationservice.dto.DependantDTO;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.DependantRepository;
import com.afrikatek.registrationservice.service.CongregantService;
import com.afrikatek.registrationservice.service.DependantService;
import lombok.AllArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DependantServiceImpl implements DependantService {
    private final DependantRepository dependantRepository;
    private final CongregantService congregantService;
    @Override
    public Iterable<Dependant> findAll() {
        return dependantRepository.findAll();
    }

    @Override
    public Dependant findById(Long id) {
        return dependantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Dependant"));
    }

    @Override
    public Dependant create(DependantDTO dependantDTO) {
        Congregant congregant = congregantService.findById(dependantDTO.congregantId());
        Dependant dependant = Dependant.builder()
                .fullNames(dependantDTO.fullNames())
                .gender(dependantDTO.gender())
                .dateOfBirth(dependantDTO.dateOfBirth())
                .congregant(AggregateReference.to(congregant.getId()))
                .build();
        return dependantRepository.save(dependant);
    }

    @Override
    public Dependant update(DependantDTO dependantDTO, Long id) {
        Dependant dependant = findById(id);
        dependant.setGender(dependantDTO.gender());
        dependant.setFullNames(dependantDTO.fullNames());
        dependant.setCongregant(AggregateReference.to(dependantDTO.congregantId()));
        return dependantRepository.save(dependant);
    }

    @Override
    public void deleteById(Long id) {
        dependantRepository.deleteById(id);
    }
}
