package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.domain.Address;

public interface AddressService {
    Address addAddress(Address address, Long congregantId);
    Address updateAddress(Address address, Long id);
    void deleteAddress(Address address, Long congregantId);
}
