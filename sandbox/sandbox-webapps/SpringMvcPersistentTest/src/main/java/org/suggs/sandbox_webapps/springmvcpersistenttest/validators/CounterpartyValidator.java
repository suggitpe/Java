package org.suggs.sandbox_webapps.springmvcpersistenttest.validators;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation class to ensure that for a given Counterparty object created from user input, we have the right
 * level/content of information.
 * <p/>
 * User: suggitpe Date: 25/02/11 Time: 19:19
 */

public class CounterpartyValidator implements Validator {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return Counterparty.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate( Object aObject, Errors aErrors ) {
        Counterparty counterparty = ( Counterparty ) aObject;

        if ( !StringUtils.hasLength( counterparty.getCounterpartyName() ) ) {
            aErrors.rejectValue( "counterpartyName", "required", "required" );
        }

        if ( !StringUtils.hasLength( counterparty.getCounterpartyLegalName() ) ) {
            aErrors.rejectValue( "counterpartyLegalName", "required", "required" );
        }

        if ( counterparty.getExternalId() == null || counterparty.getExternalId().equals( Integer.valueOf( 0 ) ) ) {
            aErrors.rejectValue( "externalId", "required", "required" );
        }
    }
}
