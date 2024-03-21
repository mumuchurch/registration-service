package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.dto.AddressDTO;
import com.afrikatek.registrationservice.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1.0/addresses")
public class AddressResource {
    private final AddressService addressService;

    @GetMapping("/{congregantId}")
    public ResponseEntity<List<Address>> findAllAddresses(@PathVariable Long congregantId) {
        log.debug("Request to find all addresses for congregant with id {}", congregantId);
        List<Address> allAddresses = addressService.findAllAddresses(congregantId);
        return ResponseEntity.status(HttpStatus.OK).body(allAddresses);
    }

    @GetMapping("/{guid}/congregants/{congregantId}")
    public ResponseEntity<Address> findCongregantAddress(@PathVariable String guid, @PathVariable Long congregantId) {
        log.debug("Request to find an address with guid {} for congregant with id {}", guid, congregantId);
        Address addressById = addressService.findAddressById(guid, congregantId);
        return ResponseEntity.status(HttpStatus.OK).body(addressById);
    }

    @PostMapping("/{congregantId}")
    public ResponseEntity<Address> addAddress(@PathVariable Long congregantId, @RequestBody AddressDTO addressDTO) {
        log.debug("Request to add address {} to congregant with id {}", addressDTO, congregantId);
        Address address = addressService.addAddress(addressDTO, congregantId);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PutMapping("/{guid}/congregants/{congregantId}")
    public ResponseEntity<Address> updateAddress(@PathVariable String guid, @PathVariable Long congregantId, @RequestBody AddressDTO addressDTO) {
        log.debug("Request to update address {} for congregant with id {}", addressDTO, congregantId);
        Address address = addressService.updateAddress(addressDTO, guid, congregantId);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @DeleteMapping("/{guid}/congregants/{congregantId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String guid, @PathVariable Long congregantId) {
        log.debug("Request to delete address with guid {} for congregant with id {}", guid, congregantId);
        addressService.deleteAddress(guid, congregantId);
        return ResponseEntity.noContent().build();
    }
}
