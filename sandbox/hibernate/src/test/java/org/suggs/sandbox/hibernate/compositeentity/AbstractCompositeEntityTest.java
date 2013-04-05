package org.suggs.sandbox.hibernate.compositeentity;

import org.hibernate.Session;
import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

public abstract class AbstractCompositeEntityTest extends AbstractSimpleHibernateIntegrationTest<Long, CoreEntity> {

    @Override
    protected void cleanUpData(Session aSession) {
        aSession.createQuery("delete from CoreEntity").executeUpdate();
    }

    @Override
    protected Long createKeyTemplate() {
        // leaving as null means that we are using sequences. Realy this could b e done better as it is
        // confusing.
        return null;
    }

    @Override
    protected CoreEntity createEntityTemplate(Long aKey, Session aSession) {
        return new CoreEntity("This is some text", new CompositeEntity("comp text", 3));
    }

    @Override
    protected void updateEntityForUpdateTest(CoreEntity aEntity) {
        aEntity.setSomeText("This is some new text");
    }

    @Override
    protected String createEntitySearchHql() {
        return "from CoreEntity";
    }
}
