package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.dto.CongregantDTO;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.CongregantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CongregationServiceImpl implements CongregantService {
    private final CongregantRepository congregantRepository;
    @Override
    public Iterable<Congregant> findAll() {
        return congregantRepository.findAll();
    }

    @Override
    public Congregant findById(Long congregantId) {
        return congregantRepository.findById(congregantId)
                .orElseThrow(() -> new EntityNotFoundException(congregantId, "Congregant"));
    }

    @Override
    public Congregant save(CongregantDTO congregantDTO) {
        Congregant congregant = Congregant.builder()
                .title(congregantDTO.title())
                .firstNames(congregantDTO.firstNames())
                .surname(congregantDTO.surname())
                .email(congregantDTO.email())
                .dob(congregantDTO.dob())
                .profession(congregantDTO.profession())
                .gender(congregantDTO.gender())
                .build();
        return congregantRepository.save(congregant);
    }

    @Override
    public Congregant update(CongregantDTO congregantDTO, Long id) {
        Congregant congregant = findById(id);
        congregant.setTitle(congregantDTO.title());
        congregant.setFirstNames(congregantDTO.firstNames());
        congregant.setEmail(congregantDTO.email());
        congregant.setDob(congregantDTO.dob());
        congregant.setProfession(congregantDTO.profession());
        congregant.setGender(congregantDTO.gender());
        congregant.setId(id);
        return congregantRepository.save(congregant);
    }

    @Override
    public void deleteById(Long id) {
        congregantRepository.deleteById(id);
    }
}
