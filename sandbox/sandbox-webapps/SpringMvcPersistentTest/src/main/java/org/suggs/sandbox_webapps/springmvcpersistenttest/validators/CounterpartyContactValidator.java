package org.suggs.sandbox_webapps.springmvcpersistenttest.validators;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * Validation class to ensure that for a given Counterparty Contact object created from user input, we have the right
 * level/content of information.
 * <p/>
 * User: suggitpe Date: 25/02/11 Time: 19:19
 */

public class CounterpartyContactValidator {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyContactValidator.class );

    public void validate( CounterpartyContact aContact, Errors aErrors ) {
        if ( !StringUtils.hasLength( aContact.getContactFirstName() ) ) {
            aErrors.rejectValue( "contactFirstName", "required", "required" );
        }

        if ( !StringUtils.hasLength( aContact.getContactLastName() ) ) {
            aErrors.rejectValue( "contactLastName", "required", "required" );
        }

        if ( !StringUtils.hasLength( aContact.getContactAddress() ) ) {
            aErrors.rejectValue( "contactAddress", "required", "required" );
        }

        if ( !StringUtils.hasLength( aContact.getContactPostcode() ) ) {
            aErrors.rejectValue( "contactPostcode", "required", "required" );
        }

        if ( !StringUtils.hasLength( aContact.getContactTelephone() ) ) {
            aErrors.rejectValue( "contactTelephone", "required", "required" );
        }
    }
}
