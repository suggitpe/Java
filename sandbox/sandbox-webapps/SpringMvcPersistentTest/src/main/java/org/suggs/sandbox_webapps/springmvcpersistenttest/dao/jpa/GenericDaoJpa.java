package org.suggs.sandbox_webapps.springmvcpersistenttest.dao.jpa;

import org.suggs.sandbox_webapps.springmvcpersistenttest.dao.GenericDao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

/**
 * This class serves as the Base class for all other DAOs - namely to hold common CRUD methods that they might
 * all use. You should only need to extend this class when your require custom CRUD logic.
 */
@Transactional
public class GenericDaoJpa<T, PK extends Serializable> implements GenericDao<T, PK> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( GenericDaoJpa.class );

    protected EntityManager entityManager;
    protected Class<T> persistentClass;

    @PersistenceContext
    public void setEntityManager( EntityManager entityManager ) {
        this.entityManager = entityManager;
    }

    public GenericDaoJpa( final Class<T> persistentClass ) {
        this.persistentClass = persistentClass;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<T> getAll() {
        return entityManager.createQuery( "Select t from " + persistentClass.getSimpleName() + " t" )
            .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public T get( PK id ) {
        return entityManager.find( this.persistentClass, id );
    }

    @Override
    public boolean exists( PK id ) {
        return get( id ) != null;
    }

    @Override
    public T saveOrUpdate( T object ) {
        T entity = entityManager.merge( object );
        entityManager.flush();
        return entity;
    }

    @Override
    public void save( T object ) {
        entityManager.persist( object );
        entityManager.flush();
    }

    @Override
    public void remove( T object ) {
        entityManager.remove( object );
    }

}
