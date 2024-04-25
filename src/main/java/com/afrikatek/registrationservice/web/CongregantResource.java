package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.dto.AddressDTO;
import com.afrikatek.registrationservice.dto.CongregantDTO;
import com.afrikatek.registrationservice.service.AddressService;
import com.afrikatek.registrationservice.service.CongregantService;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@RestController
@RequestMapping("/api/congregants")
public class CongregantResource {
   private final CongregantService congregantService;
   private final AddressService addressService;

   @GetMapping
   public ResponseEntity<Iterable<Congregant>> findAll() {
      return ResponseEntity.status(HttpStatus.OK).body(congregantService.findAll());
   }

   @PostMapping
   public ResponseEntity<Congregant> createCongregant(@RequestBody CongregantDTO congregantDTO) {
      Congregant saved = congregantService.save(congregantDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(saved);
   }

   @PutMapping("/{congregantId}")
   public ResponseEntity<Congregant> updateCongregant(@PathVariable Long congregantId, @RequestBody CongregantDTO congregantDTO) {
      Congregant updated = congregantService.update(congregantDTO, congregantId);
      return ResponseEntity.status(HttpStatus.OK).body(updated);
   }

   @GetMapping("/{congregantId}")
   public ResponseEntity<Congregant> getCongregantById(@PathVariable Long congregantId) {
      return ResponseEntity.status(HttpStatus.OK).body(congregantService.findById(congregantId));
   }

   @DeleteMapping("/{congregantId}")
   public ResponseEntity<Congregant> deleteCongregantById(@PathVariable Long congregantId) {
      congregantService.deleteById(congregantId);
      return ResponseEntity.noContent().build();
   }

   @PostMapping("/{congregantId}/addresses")
   public ResponseEntity<Address> addAddress(@PathVariable Long congregantId, @RequestBody AddressDTO addressDTO) {
      Address address = congregantService.addAddress(congregantId, addressDTO);
      if (address == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
      return ResponseEntity.status(HttpStatus.OK).body(address);
   }

   @PutMapping("/{congregantId}/addresses")
   public ResponseEntity<Address> updateAddress(@PathVariable Long congregantId, @RequestBody AddressDTO addressDTO) {
      Address address = congregantService.updateAddress(congregantId, addressDTO);
      if (address == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
      return ResponseEntity.status(HttpStatus.OK).body(address);
   }

   @DeleteMapping("/{congregantId}/addresses")
   public ResponseEntity<Congregant> deleteAddress(@PathVariable Long congregantId, @RequestBody AddressDTO addressDTO) {
      congregantService.removeAddress(congregantId, addressDTO);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
