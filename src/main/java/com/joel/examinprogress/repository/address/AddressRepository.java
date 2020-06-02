package com.joel.examinprogress.repository.address;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.address.Address;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Override
    Optional<Address> findById( Long addressId );
}
