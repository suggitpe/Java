/*
 * CounterpartyJpaDao.java created on 4 Feb 2011 10:58:59 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.dao.jpa;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.Counterparty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO for counterparty object.
 * 
 * @author suggitpe
 * @version 1.0 4 Feb 2011
 */
public class CounterpartyJpaDao extends AbstractJpaDao<Long, Counterparty> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyJpaDao.class );

    /**
     * Constructs a new instance.
     */
    public CounterpartyJpaDao() {
        super( Counterparty.class );
    }

}
