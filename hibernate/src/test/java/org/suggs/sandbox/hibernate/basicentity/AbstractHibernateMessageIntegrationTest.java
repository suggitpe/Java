package org.suggs.sandbox.hibernate.basicentity;

import org.hibernate.Session;
import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

public abstract class AbstractHibernateMessageIntegrationTest extends AbstractSimpleHibernateIntegrationTest<Long, Message> {

    @Override
    protected void cleanUpData(Session aSession) {
        aSession.createQuery("delete from Message").executeUpdate();
    }

    @Override
    protected String createEntitySearchHql() {
        return "from Message";
    }

    @Override
    protected Message createEntityTemplate(Long aKey, Session aSession) {
        return new Message("This is a test message");
    }

    @Override
    protected Long createKeyTemplate() {
        // leaving as null means that we are using sequences. Really this could be done better as it is
        // confusing.
        return null;
    }

    @Override
    protected void updateEntityForUpdateTest(Message aEntity) {
        aEntity.setText("I have been updated");
    }
}
