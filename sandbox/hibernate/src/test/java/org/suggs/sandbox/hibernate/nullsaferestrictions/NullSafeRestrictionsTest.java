/*
 * NullSafeRestrictionsTest.java created on 22 Mar 2010 08:29:19 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.nullsaferestrictions;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.criterion.Restrictions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Test fpor the nullsafe restrictions class. The core aim of this class is to check that the methods
 * implemented by this class are execatly the same as that of the core Hibernate restrictions class. This will
 * ensure that as any upgrade occur, all the interface is also implemented in this class. <br/>
 * <br/>
 * <b>This implementation could really do with some refactoring around the method execution piece.</b>
 * 
 * @author suggitpe
 * @version 1.0 22 Mar 2010
 */
public class NullSafeRestrictionsTest {

    private static final Logger LOG = LoggerFactory.getLogger( NullSafeRestrictionsTest.class );
    private static final Method[] RESTRICTIONS_METHODS = Restrictions.class.getDeclaredMethods();
    private static final Method[] NULLSAFERESTRICTIONS_METHODS = NullSafeRestrictions.class.getDeclaredMethods();

    @Before
    public void onSetUp() {
        LOG.debug( "============================" );
    }

    @SuppressWarnings("boxing")
    @Test
    public void bothImplementationsHaveTheSameNumberOfMethods() {
        LOG.debug( "Test that both implementations have the same number of methods. Restrictions=["
                   + RESTRICTIONS_METHODS.length + "] NullsafeRestrictions=["
                   + NULLSAFERESTRICTIONS_METHODS.length + "]" );
        assertThat( RESTRICTIONS_METHODS.length, equalTo( NULLSAFERESTRICTIONS_METHODS.length ) );
    }

    @Test
    public void nullsafeRetsrictionsImplementsAllRestrictionsMethods() {
        LOG.debug( "Test that the NullSafeRestrictions impl has all the same methods as the Restrictions class." );
        for ( Method methodInRestrictions : RESTRICTIONS_METHODS ) {
            if ( getMethodFromNullSafeRestrictionsIfExists( methodInRestrictions ) == null ) {
                Assert.fail( "Could not find method [" + methodInRestrictions
                             + "] in NullSafeRestrictions implementation" );
            }

        }
    }

    @Test
    public void nullSafeRestrictionsReturnTheSameObjectsAsRestrictionsForNotNullCalls() {
        LOG.debug( "Test that if I call each of the methods in the NullSafeRestrictions class then I get the exact same result from the Restrictions class." );
        for ( Method methodInRestrictions : RESTRICTIONS_METHODS ) {
            Method nullSafeMethod = getMethodFromNullSafeRestrictionsIfExists( methodInRestrictions );
            assertThat( nullSafeMethod, not( nullValue() ) );

            try {
                if ( !doBothMethodReturnSameResult( methodInRestrictions, nullSafeMethod ) ) {
                    Assert.fail( "Method in retsrictions ["
                                 + methodInRestrictions.getName()
                                 + "] executes with a different result to the same method in NullSafeRestrictions" );
                }
            }
            catch ( Exception ex ) {
                throw new IllegalStateException( "Failed to execute method ["
                                                 + methodInRestrictions.getName() + "]", ex );
            }
        }
    }

    private boolean doBothMethodReturnSameResult( Method aMethodInRestrictions, Method aNullSafeMethod )
                    throws Exception {

        LOG.debug( "Testing execution of [" + aMethodInRestrictions.getName() + "]" );
        RestrictionsMethodExecutionComparitor tester = new RestrictionsMethodExecutionComparitor( aMethodInRestrictions,
                                                                                                  aNullSafeMethod );
        return tester.compareMethods();
    }

    private Method getMethodFromNullSafeRestrictionsIfExists( Method aMethodInRestrictions ) {
        for ( Method nullSafeMethod : NULLSAFERESTRICTIONS_METHODS ) {
            if ( isMethodSameUnderDeepCheck( aMethodInRestrictions, nullSafeMethod ) ) {
                return nullSafeMethod;
            }
        }
        LOG.debug( "Could not find [" + aMethodInRestrictions.getName() + "]" );
        return null;
    }

    private boolean isMethodSameUnderDeepCheck( Method aLhs, Method aRhs ) {
        if ( !aLhs.getName().equals( aRhs.getName() ) ) {
            return false;
        }

        if ( !aLhs.getReturnType().equals( aRhs.getReturnType() ) ) {
            // check that it is assignable from
            if ( !aRhs.getReturnType().isAssignableFrom( aLhs.getReturnType() ) ) {
                return false;
            }
        }

        if ( aLhs.getModifiers() != aRhs.getModifiers() ) {
            return false;
        }

        if ( aLhs.getParameterTypes().length != aRhs.getParameterTypes().length ) {
            return false;
        }

        for ( int i = 0; i < aLhs.getParameterTypes().length; ++i ) {
            if ( !aLhs.getParameterTypes()[i].equals( aRhs.getParameterTypes()[i] ) ) {
                return false;
            }
        }

        if ( aLhs.getExceptionTypes().length != aRhs.getExceptionTypes().length ) {
            return false;
        }

        for ( int i = 0; i < aLhs.getExceptionTypes().length; ++i ) {
            if ( !aLhs.getExceptionTypes()[i].equals( aRhs.getExceptionTypes()[i] ) ) {
                return false;
            }
        }

        return true;
    }
}
