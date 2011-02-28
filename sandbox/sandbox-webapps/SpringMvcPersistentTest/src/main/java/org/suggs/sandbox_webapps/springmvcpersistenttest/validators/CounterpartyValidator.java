package org.suggs.sandbox_webapps.springmvcpersistenttest.validators;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 25/02/11
 * Time: 19:19
 */

public class CounterpartyValidator {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyValidator.class );

    public void validate( Counterparty aCounterparty, Errors aErrors ) {
        if ( !StringUtils.hasLength( aCounterparty.getCounterpartyName() ) ) {
            aErrors.rejectValue( "counterpartyName", "required", "required" );
        }
        if ( !StringUtils.hasLength( aCounterparty.getCounterpartyLegalName() ) ) {
            aErrors.rejectValue( "counterpartyLegalName", "required", "required" );
        }
        if ( aCounterparty.getExternalId() == null || aCounterparty.getExternalId().equals( Integer.valueOf( 0 ) ) ) {
            aErrors.rejectValue( "externalId", "required", "required" );
        }
    }
}
