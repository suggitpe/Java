package org.suggs.sandbox_webapps.springmvcpersistenttest.validators.support;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Abstract validator test that manages most of the parameterisation issues.
 * <p/>
 * User: suggitpe Date: 10/03/11 Time: 19:03
 */
@RunWith(Parameterized.class)
public abstract class AbstractValidatorTest<V extends Validator, T> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractValidatorTest.class );

    private V validator;
    private Errors errors;
    private T itemForValidation;
    private Boolean shouldBeSuccessfulValidation;

    @Before
    public void onSetup() {
        validator = createValidator();
    }

    protected abstract V createValidator();

    protected AbstractValidatorTest( T aItemForValidation, Boolean isSuccessfulValidation ) {
        itemForValidation = aItemForValidation;
        shouldBeSuccessfulValidation = isSuccessfulValidation;
        errors = new BeanPropertyBindingResult( itemForValidation, itemForValidation.getClass().getSimpleName() );
    }

    @Test
    @SuppressWarnings("boxing")
    public void validatesItemForValidationCorrectly() {
        LOG.debug( "Testing validity of " + itemForValidation.getClass().getSimpleName() + " object [" + itemForValidation.toString() + "]" );
        validator.validate( itemForValidation, errors );
        boolean validObject = !errors.hasErrors();
        assertThat( validObject, is( equalTo( shouldBeSuccessfulValidation ) ) );
    }

    @Test
    public void ensuresCorrectAssignability() {
        assertThat( validator.supports( itemForValidation.getClass() ), is( Boolean.TRUE ) );
    }


}
