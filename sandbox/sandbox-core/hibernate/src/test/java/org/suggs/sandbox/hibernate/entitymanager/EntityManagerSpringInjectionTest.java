/*
 * EntityManagerSpringInjectionTest.java created on 18 Jan 2011 07:15:25 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test suite that shows how we can spring inject an entity manager into our application
 * 
 * @author suggitpe
 * @version 1.0 18 Jan 2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-entitymanager-springinjection.xml" })
public class EntityManagerSpringInjectionTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( EntityManagerSpringInjectionTest.class );

    @Test
    public void deleteMe() {}
}
