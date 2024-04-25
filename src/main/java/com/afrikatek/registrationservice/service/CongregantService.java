package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.dto.AddressDTO;
import com.afrikatek.registrationservice.dto.CongregantDTO;

public interface CongregantService {
    Iterable<Congregant> findAll();
    Congregant findById(Long congregantId);
    Congregant save(CongregantDTO congregant);
    Congregant update(CongregantDTO congregant, Long id);
    void deleteById(Long id);

    Address addAddress(Long congregantId, AddressDTO addressDTO);

    Address updateAddress(Long congregantId, AddressDTO addressDTO);

    void removeAddress(Long congregantId, AddressDTO addressDTO);
}
