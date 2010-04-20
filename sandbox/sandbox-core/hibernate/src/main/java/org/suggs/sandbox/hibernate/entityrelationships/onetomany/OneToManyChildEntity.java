/*
 * OneToManyChildEntity.java created on 20 Apr 2010 07:09:57 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Child entity for the one to many example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@Entity
@Table(name = "ONETOMANY_CHILD_ENTITY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "ONETOMANY_CHILD_ENTITY_SEQ")
public class OneToManyChildEntity extends EntityBase {

}
