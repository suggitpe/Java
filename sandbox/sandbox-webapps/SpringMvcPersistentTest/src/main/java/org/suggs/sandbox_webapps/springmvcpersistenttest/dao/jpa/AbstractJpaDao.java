package org.suggs.sandbox_webapps.springmvcpersistenttest.dao.jpa;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.GenericDao;
import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class serves as the Base class for all other DAOs - namely to hold common CRUD methods that they might all use.
 * You should only need to extend this class when your require custom CRUD logic.
 */
@Transactional
public abstract class AbstractJpaDao<PK extends Serializable, T extends AbstractEntityBase> implements GenericDao<PK, T> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractJpaDao.class );


    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> persistentClass;

    /**
     * Constructs a new instance.
     */
    public AbstractJpaDao( final Class<T> persistentClass ) {
        this.persistentClass = persistentClass;
    }

    @Transactional(readOnly = true)
    @Override
    public T get( PK id ) {
        return entityManager.find( persistentClass, id );
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery( "select t from " + persistentClass.getSimpleName() + " t" ).getResultList();
    }


    @Override
    public boolean exists( PK id ) {
        return get( id ) != null;
    }

    @Override
    public void save( T object ) {
        entityManager.persist( object );
    }

    @Override
    public T merge( T object ) {
        return entityManager.merge( object );
    }

    @Override
    public void remove( PK id ) {
        T object = get( id );
        entityManager.remove( object );
    }

}
