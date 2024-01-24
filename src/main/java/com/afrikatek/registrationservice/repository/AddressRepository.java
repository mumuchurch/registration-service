package com.afrikatek.registrationservice.repository;

import com.afrikatek.registrationservice.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
