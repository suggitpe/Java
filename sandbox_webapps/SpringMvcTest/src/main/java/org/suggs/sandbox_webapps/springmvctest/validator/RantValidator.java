/*
 * RantValidator.java created on 25 Jan 2008 07:51:45 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.validator;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant;

import org.apache.oro.text.perl.Perl5Util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validtor for the Rants passed in from the view
 * 
 * @author suggitpe
 * @version 1.0 25 Jan 2008
 */
public class RantValidator implements Validator
{

    private static final String PLATE_REGEXP = "/[a-z0-9]{2,6}/i";

    /**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports( Class clazz )
    {
        return clazz.equals( Rant.class );
    }

    /**
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    public void validate( Object target, Errors errors )
    {
        Rant rant = (Rant) target;

        ValidationUtils.rejectIfEmptyOrWhitespace( errors,
                                                   "vehicle.state",
                                                   "required.state",
                                                   "State is required" );

        ValidationUtils.rejectIfEmptyOrWhitespace( errors,
                                                   "vehicle.plateNumber",
                                                   "required.plateNumber",
                                                   "Plate number is required" );

        ValidationUtils.rejectIfEmptyOrWhitespace( errors,
                                                   "rantText",
                                                   "required.rantText",
                                                   "You must enter some rant text" );

        validatePlateNumber( rant.getVehicle().getPlateNumber(), errors );
    }

    /**
     * Delegation of the validation of the plate number
     * 
     * @param aPlateNum
     *            the plate number to validate
     * @param aErr
     *            the errors object to feedback issues in
     */
    private void validatePlateNumber( String aPlateNum, Errors aErr )
    {
        Perl5Util p5u = new Perl5Util();
        if ( !p5u.match( PLATE_REGEXP, aPlateNum ) )
        {
            aErr.reject( "invalid.plateNumber", "Invalid license plate number." );
        }
    }

}
