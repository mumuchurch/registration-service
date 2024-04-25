package com.afrikatek.registrationservice.service.impl;

import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.dto.AddressDTO;
import com.afrikatek.registrationservice.dto.CongregantDTO;
import com.afrikatek.registrationservice.exception.EntityNotFoundException;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.CongregantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CongregantServiceImpl implements CongregantService {
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
        congregant.setSurname(congregantDTO.surname());
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

    @Override
    public Address addAddress(Long congregantId, AddressDTO addressDTO) {
        log.debug("Adding address {} to congregant with id {}", addressDTO, congregantId);
        Congregant dbCongregant = findById(congregantId);
        Address address = new Address();
        populateAddress(address, addressDTO, false);
        dbCongregant.addAddress(address);
        Congregant savedCongregant = congregantRepository.save(dbCongregant);
        Set<Address> addresses = savedCongregant.getAddresses();
        Optional<Address> optionalAddress = addresses.stream().filter(filteredAddress -> filteredAddress.equals(address)).findFirst();
        return optionalAddress.orElse(null);
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
        }
    }

    @Override
    public Address updateAddress(Long congregantId, AddressDTO addressDTO) {
        Congregant congregant = findById(congregantId);
        Address address = new Address();
        populateAddress(address, addressDTO, false);
        Optional<Address> optionalAddress = congregant.getAddresses()
                .stream()
                .filter(filteredAddress -> filteredAddress.equals(address))
                .findFirst();
        Address foundAddress = optionalAddress.orElseThrow(() -> new EntityNotFoundException(0L, "Address"));
        populateAddress(foundAddress, addressDTO, true);
        congregant.addAddress(address);
        address.setCongregant(congregant);
        Congregant saved = congregantRepository.save(congregant);
        Optional<Address> addressOptional = saved.getAddresses().stream().filter(filteredAddress -> filteredAddress.equals(address)).findFirst();
        return addressOptional.orElse(null);
    }

    @Override
    public void removeAddress(Long congregantId, AddressDTO addressDTO) {
        Congregant congregant = findById(congregantId);
        Address address = Address.builder().build();
        populateAddress(address, addressDTO, false);
        congregant.removeAddress(address);
        congregantRepository.save(congregant);
    }
}
