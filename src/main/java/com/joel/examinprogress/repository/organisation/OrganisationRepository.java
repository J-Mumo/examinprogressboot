package com.joel.examinprogress.repository.organisation;

import org.springframework.data.repository.CrudRepository;

import com.joel.examinprogress.domain.organisation.DomainOrganisation;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface OrganisationRepository extends CrudRepository<
        DomainOrganisation,
        Long> {

    DomainOrganisation findByDomain( String domain );
}
