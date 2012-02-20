/*
 * AveragePriceFunctionMockitoTest.java created on 20 Nov 2009 19:33:09 by suggitpe for project SandBox - JUnit
 * 
 */
package org.suggs.sandbox.test.junit.impl;

import org.suggs.sandbox.test.junit.IDoubleDaoService;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite to show how to use mockito
 * 
 * @author suggitpe
 * @version 1.0 20 Nov 2009
 */
public class AveragePriceFunctionMockitoTest {

    private IDoubleDaoService mockService;
    private AveragePriceFunction function;

    /**
     * set up the test
     */
    @Before
    public void doBefore() {
        mockService = mock( IDoubleDaoService.class );
    }

    /**
     * Tests that when we execute with one date it will correctly average the resulting doubles
     * 
     * @throws Exception
     */
    @Test
    public void testAveragePriceWithOneDate() throws Exception {
        Date date1 = new Date();
        function = new AveragePriceFunction( mockService, date1 );

        when( mockService.doubleForDate( date1 ) ).thenReturn( Double.valueOf( 10.0d ) );
        Double result = function.execute();

        assertThat( "Function did not give the correct result", result, equalTo( Double.valueOf( 10.0d ) ) );
    }

    /**
     * Tests that when we execute the function with two dates that we get a good average.
     * 
     * @throws Exception
     */
    @Test
    public void testAveragePriceWithTwoDates() throws Exception {
        Date date1 = new Date( 12l );
        Date date2 = new Date( 13l );
        function = new AveragePriceFunction( mockService, date1, date2 );

        when( mockService.doubleForDate( date1 ) ).thenReturn( Double.valueOf( 10.0d ) );
        when( mockService.doubleForDate( date2 ) ).thenReturn( Double.valueOf( 20.0d ) );

        Double result = function.execute();

        assertThat( "Function did not give the correct result", result, equalTo( Double.valueOf( 15.0d ) ) );
    }

    /**
     * Tests that when the function is exceuted with five dates it all works correctly.
     * 
     * @throws Exception
     */
    @Test
    public void testAveragePriceWithFiveDates() throws Exception {
        Date date1 = new Date( 12l );
        Date date2 = new Date( 13l );
        Date date3 = new Date( 14l );
        Date date4 = new Date( 15l );
        Date date5 = new Date( 16l );
        function = new AveragePriceFunction( mockService, date1, date2, date3, date4, date5 );

        when( mockService.doubleForDate( date1 ) ).thenReturn( Double.valueOf( 10.0d ) );
        when( mockService.doubleForDate( date2 ) ).thenReturn( Double.valueOf( 20.0d ) );
        when( mockService.doubleForDate( date3 ) ).thenReturn( Double.valueOf( 30.0d ) );
        when( mockService.doubleForDate( date4 ) ).thenReturn( Double.valueOf( 40.0d ) );
        when( mockService.doubleForDate( date5 ) ).thenReturn( Double.valueOf( 50.0d ) );
        Double result = function.execute();

        assertThat( "Function did not give the correct result", result, equalTo( Double.valueOf( 30.0d ) ) );
    }

    /**
     * Tests that when executed with a null date, it runs correctly
     * 
     * @throws Exception
     */
    @Test
    public void testAveragePriceWithNullDate() throws Exception {
        function = new AveragePriceFunction( mockService, (Date) null );
        Double result = function.execute();

        assertThat( "Function did not execute correctly", result, equalTo( Double.valueOf( 0.0d ) ) );

    }
}
