package org.suggs.sandbox.hibernate.timestamps;

import org.hibernate.Session;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;
import org.suggs.sandbox.hibernate.support.HibernateIntegrationTestCallback;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = {"classpath:xml/ut-timestamps.xml"})
public class HibernateTimestampEntityIntegrationTest extends AbstractSimpleHibernateIntegrationTest<Long, TimestampedEntity> {

    @SuppressWarnings("unused")
    private static Logger LOG = LoggerFactory.getLogger(HibernateTimestampEntityIntegrationTest.class);

    private static final String WHERE_CLAUSE = "someString in ('deleteMe', 'altered')";
    private static final String TEST_HQL = "from TimestampedEntity where " + WHERE_CLAUSE;

    @Test
    @Override
    public void basicCreateOperationCreatesCorrectObject() {
    }

    @Override
    protected void cleanUpData(Session aSession) {

        String childDelete = " delete from TimestampedChildEntity c where exists (select 1 from TimestampedEntity e where c.parent.id = e.id and e."
                + WHERE_CLAUSE + " )";
        String parentDelete = "delete " + TEST_HQL;

        aSession.createQuery(childDelete).executeUpdate();
        aSession.createQuery(parentDelete).executeUpdate();
    }

    @Override
    protected Long createKeyTemplate() {
        // this is actually not needed for this entity.
        return null;
    }

    @Override
    protected TimestampedEntity createEntityTemplate(Long aKey, Session aSession) {
        return TimestampTestHelper.createTimestampEntity();
    }

    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    @Override
    protected void updateEntityForUpdateTest(TimestampedEntity aEntity) {
        aEntity.setSomeString("altered");
    }

    @Test
    public void creationOfNewObjectPopulatesCreateAndUpdateDatesAndThatTheyAreSameValue() {
        runGenericTest(new HibernateIntegrationTestCallback() {

            TimestampedEntity entity = null;

            @Override
            public void beforeTest(Session aSession) {
                entity = createEntityTemplate(createKeyTemplate(), aSession);
                verifyEntityCount(aSession, 0L);
            }

            @Override
            public void executeTest(Session aSession) {
                aSession.save(entity);
            }

            @Override
            public void verifyTest(Session aSession) {
                TimestampedEntity result = (TimestampedEntity) aSession.createQuery(TEST_HQL)
                        .uniqueResult();
                assertThat(entity.getTimestampAuditInfo().getCreateDate(),
                        equalTo(result.getTimestampAuditInfo().getCreateDate()));
                assertThat(entity.getTimestampAuditInfo().getUpdateDate(),
                        equalTo(result.getTimestampAuditInfo().getUpdateDate()));
                assertThat(result.getTimestampAuditInfo().getCreateDate(),
                        equalTo(result.getTimestampAuditInfo().getUpdateDate()));

                verifyEntityCount(aSession, 1L);
            }
        });
    }

    @Test
    public void updateOfExistingObjectPopulatesUpdateDateAndThatCreateDateDiffers() {
        runGenericTest(new HibernateIntegrationTestCallback() {

            TimestampedEntity entity = null;

            @Override
            public void beforeTest(Session aSession) {
                entity = createEntityTemplate(createKeyTemplate(), aSession);
                aSession.save(entity);
                verifyEntityCount(aSession, 1L);
            }

            @Override
            public void executeTest(Session aSession) {
                TimestampedEntity toUpdate = (TimestampedEntity) aSession.createQuery(TEST_HQL)
                        .uniqueResult();
                toUpdate.setSomeInteger(23);
                aSession.save(toUpdate);
            }

            @Override
            public void verifyTest(Session aSession) {
                TimestampedEntity result = (TimestampedEntity) aSession.createQuery(TEST_HQL)
                        .uniqueResult();
                assertThat(result, not(nullValue()));
                assertThat(result.getTimestampAuditInfo().getCreateDate(),
                        not(equalTo(result.getTimestampAuditInfo().getUpdateDate())));
            }
        });
    }

}
