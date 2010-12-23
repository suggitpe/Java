/*
 * ConfigurationManagerTest.java created on 29 Sep 2010 07:26:51 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.conffguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import com.ubs.reporting.configuration.ConfigurationManager;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Test to ensure that the configuration manager reads the properties file correctly and also that it can
 * access data correctly.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2010
 */
public class ConfigurationManagerTest {

    private static final Logger LOG = LoggerFactory.getLogger( ConfigurationManagerTest.class );

    @Test
    public void doesNotThrowExceptionOnInitialRead() {
        ConfigurationManager.instance();
    }

    @Test
    public void makesUsernameAvailable() {
        String username = ConfigurationManager.instance().getProperty( ConfigurationManager.USERNAME );
        LOG.debug( "Have retrieved username [" + username + "] from configuration file" );
        assertThat( username, not( nullValue() ) );
    }
}
