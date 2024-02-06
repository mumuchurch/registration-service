package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final CongregantRepository congregantRepository;
    @Override
    public Address addAddress(Address address, Long congregantId) {
        Congregant congregant = congregantRepository
                .findById(congregantId)
                .orElseThrow(() -> new EntityNotFoundException(congregantId, "Congregant"));
        congregant.addAddress(address);
        congregantRepository.save(congregant);
        return address;
    }

    @Override
    public Address updateAddress(Address address, Long id) {
        Congregant congregant = congregantRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "Congregant"));
        congregant.addAddress(address);
        congregantRepository.save(congregant);
        return address;
    }

    @Override
    public void deleteAddress(Address address, Long congregantId) {
        Congregant congregant = congregantRepository
                .findById(congregantId)
                .orElseThrow(() -> new EntityNotFoundException(congregantId, "Congregant"));
        congregant.removeAddress(address);
        congregantRepository.save(congregant);
    }
}
