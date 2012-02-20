/*
 * HamcrestTest.java created on 9 Feb 2010 12:15:55 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.junit;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsIn;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

/**
 * Test class that will show varying uses of Hamcrest asserts etc
 *
 * @author suggitpe
 * @version 1.0 9 Feb 2010
 */
public class HamcrestTest {

    @Test
    public void testAssertThatIsForString() {
        String color = "red";
        assertThat( color, is( "red" ) );
    }

    /**
     * Watch out for boxing!
     */
    @Test
    public void testAssertThatIsForDouble() {
        double expected = 100.0;
        double result = 10.0 * 10.0;
        assertThat( result, is( expected ) );
    }

    @Test
    public void testAssertThatIsIn() {
        String[] colors = { "red", "green", "blue" };
        String result = "blue";
        assertThat( result, IsIn.isIn( colors ) );
    }

    @Test
    public void testAssertThatIsNotIn() {
        String[] colors = { "red", "green", "blue" };
        String result = "yellow";
        assertThat( result, not( IsIn.isIn( colors ) ) );
    }

    @Test
    public void testAssertThatIsOneOfRedGreenBlue() {
        String color = "blue";
        assertThat( color, IsIn.isOneOf( "blue", "green", "red" ) );
    }

    @Test
    public void testAssertThatIsNotNull() {
        String color = "blue";
        assertThat( color, notNullValue() );
    }

    @Test
    public void testAssertThatHasItem() {
        List<String> colors = new ArrayList<String>();
        colors.add( "red" );
        colors.add( "green" );
        colors.add( "blue" );
        assertThat( colors, hasItem( "red" ) );
    }

    @Test
    public void testEqualTo() {
        String a1 = "a";
        String a2 = "a";

        assertThat( a1, equalTo( a2 ) );

    }

}
