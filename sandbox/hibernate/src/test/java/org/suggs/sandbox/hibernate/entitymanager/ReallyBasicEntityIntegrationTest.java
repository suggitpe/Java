/*
 * ReallyBasicEntityIntegrationTest.java created on 24 Jan 2011 19:10:44 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entitymanager;

import org.springframework.test.context.ContextConfiguration;
import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;
import org.suggs.sandbox.hibernate.support.AbstractEntityManagerIntegrationTest;

import javax.persistence.EntityManager;
import java.util.Calendar;

@ContextConfiguration(locations = {"classpath:xml/ut-entitymanager-springinjection.xml"})
public class ReallyBasicEntityIntegrationTest extends AbstractEntityManagerIntegrationTest<Long, ReallyBasicEntity> {

    private static final String DELETE_SQL = "delete ReallyBasicEntity where someInteger = :intValue";

    @Override
    protected void cleanUpData(EntityManager aEntityManager) {
        aEntityManager.createQuery(DELETE_SQL)
                .setParameter("intValue", 8899)
                .executeUpdate();
    }

    @Override
    protected Long createKeyTemplate() {
        return null;
    }

    @Override
    protected String createEntitySearchHql() {
        return "from ReallyBasicEntity where someInteger = 8899";
    }

    @Override
    protected ReallyBasicEntity createEntityTemplate(Long aKey) {
        return new ReallyBasicEntity("foo", 8899, Calendar.getInstance().getTime());
    }

    @Override
    protected void updateEntityForUpdateTest(ReallyBasicEntity aEntity) {
        aEntity.setSomeString("bar");
    }
}
