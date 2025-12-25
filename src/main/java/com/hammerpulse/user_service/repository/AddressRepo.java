package com.hammerpulse.user_service.repository;

import com.hammerpulse.user_service.entity.Address;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Address.class,idClass = Integer.class)
public interface AddressRepo {
    Address save(Address address);
    List<Address> findByUserId(int id);

    Address findById(int id);

    void deleteById(int id);
}
