/*
 * CounterpartyJpaDao.java created on 4 Feb 2011 10:58:59 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.dao.jpa;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.CounterpartyContactDao;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.CounterpartyContact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

/**
 * DAO for counterparty contact object.
 *
 * @author suggitpe
 * @version 1.0 4 Feb 2011
 */
@Repository
public class CounterpartyContactJpaDao extends AbstractJpaDao<Long, CounterpartyContact> implements CounterpartyContactDao {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyContactJpaDao.class );

    /**
     * Constructs a new instance.
     */
    public CounterpartyContactJpaDao() {
        super( CounterpartyContact.class );
    }

}
