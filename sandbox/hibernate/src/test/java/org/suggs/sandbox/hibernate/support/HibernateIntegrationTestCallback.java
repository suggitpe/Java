/*
 * HibernateIntegrationTestCallback.java created on 25 Mar 2010 16:41:13 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import org.hibernate.Session;

/**
 * Interface for a callback to be used for testing Hibernate objects with segregated transactional boundaries.
 * the really neat thing about this is that it allows you to pass the impl of the tests around and manage the
 * actual execution a little later.
 * 
 * @author suggitpe
 * @version 1.0 25 Mar 2010
 */
public interface HibernateIntegrationTestCallback {

    /**
     * Run before the test execution.
     */
    void beforeTest( Session aSession );

    /**
     * Execute the actual test.
     */
    void executeTest( Session aSession );

    /**
     * Verify the test results.
     * 
     * @param aSession
     */
    void verifyTest( Session aSession );
}
