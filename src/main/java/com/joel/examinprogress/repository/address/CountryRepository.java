package com.joel.examinprogress.repository.address;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.address.Country;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByName( String name );
}
