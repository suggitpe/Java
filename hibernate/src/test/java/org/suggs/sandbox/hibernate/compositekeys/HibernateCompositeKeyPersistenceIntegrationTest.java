package org.suggs.sandbox.hibernate.compositekeys;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;
import org.suggs.sandbox.hibernate.support.HibernateIntegrationTestCallback;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = {"classpath:xml/ut-compositekeys.xml"})
public class HibernateCompositeKeyPersistenceIntegrationTest extends AbstractSimpleHibernateIntegrationTest<EntityKey, EntityObject> {

    private static final String TEST_HQL = "from EntityObject";

    @Override
    protected void cleanUpData(Session aSession) {
        aSession.createQuery("delete from EntityObject").executeUpdate();
    }

    @Override
    protected EntityKey createKeyTemplate() {
        EntityKey key = new EntityKey();
        key.setKeyOne("key 1-1");
        key.setKeyTwo("key 2-1");
        key.setKeyThree("key 3-1");
        return key;
    }

    @Override
    protected EntityObject createEntityTemplate(EntityKey aKey, Session aSession) {
        EntityObject object = new EntityObject();
        object.setKey(aKey);
        object.setDataOne("data1 1-1");
        object.setDataTwo("data1 2-1");
        return object;
    }

    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    @Override
    protected void updateEntityForUpdateTest(EntityObject aEntity) {
        aEntity.setDataTwo("Updated this field");
    }

    @Test
    public void createEntityWithNullDataInTable() {
        runGenericTest(new HibernateIntegrationTestCallback() {

            EntityKey key = createKeyTemplate();
            EntityObject object = null;

            @Override
            public void beforeTest(Session aSession) {
                object = createEntityTemplate(key, aSession);
            }

            @Override
            public void executeTest(Session aSession) {
                object.setDataTwo(null);
                aSession.save(object);
            }

            @Override
            public void verifyTest(Session aSession) {
                verifyEntityCount(aSession, 1L);
            }
        });
    }

    @Test(expected = HibernateException.class)
    public void createEntityWithNullKeyInTableThrowsException() {
        runGenericTest(false, new HibernateIntegrationTestCallback() {

            EntityKey key = createKeyTemplate();
            EntityObject object = null;

            @Override
            public void beforeTest(Session aSession) {
                object = createEntityTemplate(key, aSession);
            }

            @Override
            public void executeTest(Session aSession) {
                key.setKeyThree(null);
                aSession.save(object);
            }

            @Override
            public void verifyTest(Session aSession) {
                verifyEntityCount(aSession, 1L);
            }
        });
    }

    @Test
    public void retrieveEntityWithNullDataFromTableWithGet() {
        runGenericTest(new HibernateIntegrationTestCallback() {

            EntityKey key = createKeyTemplate();
            EntityObject object = null;
            EntityObject result;

            @Override
            public void beforeTest(Session aSession) {
                object = createEntityTemplate(key, aSession);
                object.setDataTwo(null);
                aSession.save(object);
            }

            @Override
            public void executeTest(Session aSession) {
                result = (EntityObject) aSession.get(EntityObject.class, key);
            }

            @Override
            public void verifyTest(Session aSession) {
                verifyResult(object, result);
            }
        });
    }

    /**
     * This test highlights and issue with the way that Hibernate deals with null values in keys. If this test
     * ever fails it means that now Hibernate deals with null values in composite keys.
     */
    @Test(expected = HibernateException.class)
    public void retrieveEntityWithNullKeyFromTableWithGetReturnsNull_HibernateIssueAndThrowsException() {
        runGenericTest(false, new HibernateIntegrationTestCallback() {

            EntityKey key = createKeyTemplate();
            EntityObject object = null;
            EntityObject result;

            @Override
            public void beforeTest(Session aSession) {
                object = createEntityTemplate(key, aSession);
                key.setKeyThree(null);
                aSession.save(object);
            }

            @Override
            public void executeTest(Session aSession) {
                result = (EntityObject) aSession.get(EntityObject.class, key);
            }

            @Override
            public void verifyTest(Session aSession) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat(result, nullValue());
            }
        });
    }

    /**
     * This test highlights and issue with the way that Hibernate deals with null values in keys. If this test
     * ever fails it means that now Hibernate deals with null values in composite keys.
     */
    @Test(expected = HibernateException.class)
    public void retrieveEntityWithNullKeyFromTableWithCriteriaReturnsNull_HibernateIssueAndThrowsException() {
        runGenericTest(false, new HibernateIntegrationTestCallback() {

            EntityKey key = createKeyTemplate();
            EntityObject object = null;
            EntityObject result;

            @Override
            public void beforeTest(Session aSession) {
                object = createEntityTemplate(key, aSession);
                key.setKeyThree(null);
                aSession.save(object);
            }

            @Override
            public void executeTest(Session aSession) {
                Criteria criteria = aSession.createCriteria(EntityObject.class);
                criteria.add(Restrictions.eq("key.keyOne", key.getKeyOne()));
                criteria.add(Restrictions.eq("key.keyTwo", key.getKeyTwo()));
                criteria.add(Restrictions.isNull("key.keyThree"));
                result = (EntityObject) criteria.uniqueResult();
            }

            @Override
            public void verifyTest(Session aSession) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat(result, nullValue());
            }
        });
    }

    /**
     * This test highlights and issue with the way that Hibernate deals with null values in keys. If this test
     * ever fails it means that now Hibernate deals with null values in composite keys.
     */
    @Test(expected = HibernateException.class)
    public void retrieveEntityWithNullKeyFromTableWithQbeReturnsNull_HibernateIssueAndThrowsException() {
        runGenericTest(false, new HibernateIntegrationTestCallback() {

            EntityKey key = createKeyTemplate();
            EntityObject object = null;
            EntityObject result;

            @Override
            public void beforeTest(Session aSession) {
                object = createEntityTemplate(key, aSession);
                key.setKeyThree(null);
                aSession.save(object);
            }

            @Override
            public void executeTest(Session aSession) {
                EntityObject example = new EntityObject();
                example.setKey(key);
                Criteria criteria = aSession.createCriteria(EntityObject.class);
                criteria.add(Example.create(example));
                result = (EntityObject) criteria.uniqueResult();
            }

            @Override
            public void verifyTest(Session aSession) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat(result, nullValue());
            }
        });
    }

    /**
     * This test highlights and issue with the way that Hibernate deals with null values in keys. If this test
     * ever fails it means that now Hibernate deals with null values in composite keys.
     */
    @Test(expected = HibernateException.class)
    public void retrieveEntityWithNullKeyFromTableWithHqlReturnsNull_HibernateIssueAndThrowsException() {
        runGenericTest(false, new HibernateIntegrationTestCallback() {

            EntityKey key = createKeyTemplate();
            EntityObject object = null;
            EntityObject result;

            @Override
            public void beforeTest(Session aSession) {
                object = createEntityTemplate(key, aSession);
                key.setKeyThree(null);
                aSession.save(object);
            }

            @Override
            public void executeTest(Session aSession) {
                StringBuffer buff = new StringBuffer("from EntityObject where ");
                buff.append("key.keyOne = '").append(key.getKeyOne()).append("' ");
                buff.append("and key.keyTwo = '").append(key.getKeyTwo()).append("' ");
                buff.append("and key.keyThree is null");
                Query query = aSession.createQuery(buff.toString());
                result = (EntityObject) query.uniqueResult();
            }

            @Override
            public void verifyTest(Session aSession) {
                // DON'T BLINDLY CHANGE THIS - READ JAVADOC
                assertThat(result, nullValue());
            }
        });
    }

}
