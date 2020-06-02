package com.joel.examinprogress.repository.address;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.address.AddressType;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface AddressTypeRepository extends CrudRepository<AddressType,
        Long> {

    @Override
    Optional<AddressType> findById( Long addressTypeId );


    List<AddressType> findByName( String name );
}
