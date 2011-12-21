package org.suggs.webapps.buildpipeline.domain.component;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test class for the ComponentVerionsBean.
 * <p/>
 * User: suggitpe Date: 26/07/11 Time: 14:23
 */

public final class ComponentVersionsBeanTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentVersionsBeanTest.class );

    private ComponentVersionsBean componentVersionsBean;
    private ComponentBean componentBean = new ComponentBean( "CAL", Boolean.FALSE );
    private ComponentBean testBean1 = new ComponentBean( "TEST", Boolean.TRUE );
    private ComponentBean testBean2 = new ComponentBean( "OTHER-TEST", Boolean.TRUE );

    @Before
    public void onSetup() {
        componentVersionsBean = new ComponentVersionsBean();
        componentVersionsBean.addVersion( componentBean, "1.1" );
        componentVersionsBean.addVersion( componentBean, "1.2" );
        componentVersionsBean.addVersion( componentBean, "1.3" );
        componentVersionsBean.addVersion( componentBean, "1.4" );
        componentVersionsBean.addVersion( testBean1, "1.4" );
        componentVersionsBean.addVersion( testBean1, "1.4" );
        componentVersionsBean.addVersion( testBean1, "1.4" );
        componentVersionsBean.addVersion( testBean2, "1.4" );
    }

    @Test
    public void shouldReturnListOfComponents() {
        assertThat( componentVersionsBean.getComponents().size(), equalTo( 1 ) );
        assertThat( componentVersionsBean.getComponents().contains( componentBean ), is( true ) );
    }

    @Test
    public void shouldReturnListOfTestSuites() {
        assertThat( componentVersionsBean.getTestSuites().size(), equalTo( 2 ) );
        assertThat( componentVersionsBean.getTestSuites().contains( testBean1 ), is( true ) );
        assertThat( componentVersionsBean.getTestSuites().contains( testBean2 ), is( true ) );
    }
}

