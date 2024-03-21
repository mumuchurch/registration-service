package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.dto.AddressDTO;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.AddressService;
import com.afrikatek.registrationservice.service.CongregantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final CongregantService congregantService;
    private final CongregantRepository congregantRepository;

    @Override
    public List<Address> findAllAddresses(Long congregantId) {
        return congregantService.findById(congregantId).getAddresses().stream().toList();
    }

    @Override
    public Address findAddressById(String guid, Long congregantId) {
        return findAllAddresses(congregantId).stream()
                .filter(address -> address.getGuid().equals(guid))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(guid, "Address"));
    }

    @Override
    public Address addAddress(AddressDTO addressDTO, Long congregantId) {
        Congregant congregant = congregantService.findById(congregantId);
        Address address = new Address();
        populateAddress(address, addressDTO, false);
        congregant.addAddress(address);
        Congregant saved = congregantRepository.save(congregant);
        return saved.getAddresses()
                .stream()
                .filter(add -> add.getGuid().equals(address.getGuid()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(address.getGuid(), "Address"));
    }

    @Override
    public Address updateAddress(AddressDTO addressDTO, String guid, Long congregantId) {
        Congregant congregant = congregantService.findById(congregantId);
        Address address = congregant
                .getAddresses()
                .stream()
                .filter(add -> add.getGuid().equals(guid)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(guid, "Address"));
        populateAddress(address, addressDTO, true);
        congregant.addAddress(address);
        Congregant saved = congregantRepository.save(congregant);
        return saved.getAddresses()
                .stream()
                .filter(add -> add.getGuid().equals(address.getGuid()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(address.getGuid(), "Address"));
    }

    @Override
    public void deleteAddress(String guid, Long congregantId) {
        Congregant congregant = congregantService.findById(congregantId);
        Address foundAddress = congregant.getAddresses()
                .stream()
                .filter(address -> address.getGuid().equals(guid))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(guid, "Address"));
        congregant.removeAddress(foundAddress);
        congregantRepository.save(congregant);
    }

    private static void populateAddress(Address address, AddressDTO addressDTO, Boolean update) {
        address.setCity(addressDTO.city());
        address.setProvince(addressDTO.province());
        address.setStreetAddress(addressDTO.streetAddress());
        address.setStreetAddressOne(addressDTO.streetAddressOne());
        address.setCountry(addressDTO.country());
        address.setZipCode(addressDTO.zipCode());
        if (Boolean.TRUE.equals(update)) {
            address.setLastModifiedDate(Instant.now());
        } else {
            address.setCreatedDate(Instant.now());
            address.setGuid(UUID.randomUUID().toString());
        }
    }

}
