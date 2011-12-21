package org.suggs.webapps.buildpipeline.validators;

import org.suggs.webapps.buildpipeline.domain.releaseversion.ReleaseVersion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validates that a ReleaseVersionImpl is true and complete.
 * <p/>
 * User: suggitpe Date: 06/07/11 Time: 10:44
 */

public final class ReleaseVersionValidator implements Validator {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionValidator.class );

    @Override
    public boolean supports( Class<?> aClass ) {
        return ReleaseVersion.class.isAssignableFrom( aClass );
    }

    @Override
    public void validate( Object aObject, Errors aErrors ) {
        ReleaseVersion version = ( ReleaseVersion ) aObject;

        if ( !StringUtils.hasLength( version.getDescription() ) ) {
            aErrors.rejectValue( "description", "required", "required" );
        }

    }
}
