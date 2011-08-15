/*
 * CompositeEntityHibernateAnnotationDaoTest.java created on 22 Sep 2010 20:01:42 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositeentity;

import org.springframework.test.context.ContextConfiguration;

/**
 * Annotated DAO test for the composite entity.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2010
 */
@ContextConfiguration(locations = { "classpath:xml/ut-compositetest-annotation.xml" })
public class CompositeEntityHibernateAnnotationDaoTest extends AbstractCompositeEntityTest {}
