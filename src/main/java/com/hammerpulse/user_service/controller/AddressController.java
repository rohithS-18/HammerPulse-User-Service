package com.hammerpulse.user_service.controller;

import com.hammerpulse.user_service.dto.AddressDto;
import com.hammerpulse.user_service.dto.UserAddressResponse;
import com.hammerpulse.user_service.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity<AddressDto> addAddress(@Valid @RequestBody AddressDto address){
        AddressDto userAddress=addressService.addAddresss(address);
        URI location= URI.create("/address/"+userAddress.getId());
        return ResponseEntity.created(location).body(userAddress);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") int id){
        return ResponseEntity.ok(addressService.getAddress(id));
    }

    @GetMapping("/user/address/{id}")
    public ResponseEntity<UserAddressResponse> getAddressByUserId(@PathVariable("id") int id){
        return ResponseEntity.ok(addressService.getAddressForUser(id));
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") int id){
         id=addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/address/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("id") int id,@RequestBody AddressDto addressDto){
        addressDto= addressService.updateAddress(id,addressDto);
        return ResponseEntity.ok(addressDto);
    }
}
