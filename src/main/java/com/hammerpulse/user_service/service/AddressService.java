package com.hammerpulse.user_service.service;

import com.hammerpulse.user_service.dto.AddressDto;
import com.hammerpulse.user_service.dto.UserAddressResponse;
import com.hammerpulse.user_service.entity.Address;
import com.hammerpulse.user_service.exception.ResourceNotFoundException;
import com.hammerpulse.user_service.mapper.AddressMapper;
import com.hammerpulse.user_service.repository.AddressRepo;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AddressMapper addressMapper;

    public AddressDto addAddresss(AddressDto addressDto){
        try {
            Address address=addressRepo.save(addressMapper.toEntity(addressDto));
            return addressMapper.toDto(address);
        }catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof PropertyValueException pve) {
                String field=pve.getPropertyName();
                    throw new DataIntegrityViolationException(field+" cannot be null");
            }
            throw new com.hammerpulse.user_service.exception.DataIntegrityViolationException(ex.getMessage());
        }


    }

    public UserAddressResponse getAddressForUser(int id){
        List<Address> userAddress=addressRepo.findByUserId(id);
        if(userAddress==null || userAddress.isEmpty()){
            throw new ResourceNotFoundException("Address not found for this user");
        }
        List<AddressDto> addresslist=userAddress.stream()
                .map(address -> addressMapper.toDto(address)).toList();
        return new UserAddressResponse(addresslist,addresslist.size());
    }

    public AddressDto getAddress(int id){
        Address userAddress=addressRepo.findById(id);
        if(userAddress==null){
            throw new ResourceNotFoundException("Address not found for this user");
        }
        return addressMapper.toDto(userAddress);
    }

    public int deleteAddress(int id) {
        addressRepo.deleteById(id);
        return id;
    }

    public AddressDto updateAddress(int id,AddressDto addressDto) {
            Address address = addressRepo.findById(id);
            if(address==null)
                    throw new ResourceNotFoundException("Address does not exists to delete");
            addressMapper.updateAddressFromDto(addressDto, address);
            address = addressRepo.save(address);
            return addressMapper.toDto(address);

    }
}
