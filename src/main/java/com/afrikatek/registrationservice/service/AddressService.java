package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<Address> findAllAddresses(Long congregantId);
    Address findAddressById(String guid, Long congregantId);
    Address addAddress(AddressDTO addressDTO, Long congregantId);
    Address updateAddress(AddressDTO addressDTO, String guid, Long congregantId);
    void deleteAddress(String guid, Long congregantId);
}
