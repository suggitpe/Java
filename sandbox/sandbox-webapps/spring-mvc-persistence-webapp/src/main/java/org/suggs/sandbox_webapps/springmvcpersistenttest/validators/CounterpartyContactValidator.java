package org.suggs.sandbox_webapps.springmvcpersistenttest.validators;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation class to ensure that for a given Counterparty Contact object created from user input, we have the right
 * level/content of information.
 * <p/>
 * User: suggitpe Date: 25/02/11 Time: 19:19
 */

public class CounterpartyContactValidator implements Validator {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyContactValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return CounterpartyContact.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate( Object aObject, Errors aErrors ) {

        CounterpartyContact contact = ( CounterpartyContact ) aObject;

        if ( !StringUtils.hasLength( contact.getContactFirstName() ) ) {
            aErrors.rejectValue( "contactFirstName", "required", "required" );
        }

        if ( !StringUtils.hasLength( contact.getContactLastName() ) ) {
            aErrors.rejectValue( "contactLastName", "required", "required" );
        }

        if ( !StringUtils.hasLength( contact.getContactAddress() ) ) {
            aErrors.rejectValue( "contactAddress", "required", "required" );
        }

        if ( !StringUtils.hasLength( contact.getContactPostcode() ) ) {
            aErrors.rejectValue( "contactPostcode", "required", "required" );
        }

        if ( !StringUtils.hasLength( contact.getContactTelephone() ) ) {
            aErrors.rejectValue( "contactTelephone", "required", "required" );
        }
    }
}
