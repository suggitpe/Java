/*
 * TimestampedEntity.java created on 25 Mar 2010 06:51:47 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.timestamps;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Simple entity that will show how to use timestamps in a Hibernate
 * entity. This entity has a surrogate key built from a sequence and a
 * set of basic data values.
 * 
 * @author suggitpe
 * @version 1.0 25 Mar 2010
 */
@Entity
@Table(name = "TIMESTAMPED_ENTITY")
@SequenceGenerator(name = "TIMESTAMP_ENTITY_SEQ_STR", sequenceName = "TIMESTAMP_ENTITY_SEQ")
public class TimestampedEntity {

    private Long id;
    private String someString;
    private Date someDate;
    private Integer someInteger;

    /**
     * Returns the value of id.
     * 
     * @return Returns the id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIMESTAMP_ENTITY_SEQ_STR")
    public Long getId() {
        return id;
    }

    /**
     * Sets the id field to the specified value.
     * 
     * @param aId
     *            The id to set.
     */
    public void setId( Long aId ) {
        id = aId;
    }

    /**
     * Returns the value of someString.
     * 
     * @return Returns the someString.
     */
    @Column(name = "STRING_DATA", nullable = false, length = 16)
    public String getSomeString() {
        return someString;
    }

    /**
     * Sets the someString field to the specified value.
     * 
     * @param aSomeString
     *            The someString to set.
     */
    public void setSomeString( String aSomeString ) {
        someString = aSomeString;
    }

    /**
     * Returns the value of someDate.
     * 
     * @return Returns the someDate.
     */
    @Column(name = "DATE_DATA", nullable = false)
    public Date getSomeDate() {
        return someDate;
    }

    /**
     * Sets the someDate field to the specified value.
     * 
     * @param aSomeDate
     *            The someDate to set.
     */
    public void setSomeDate( Date aSomeDate ) {
        someDate = aSomeDate;
    }

    /**
     * Returns the value of someInteger.
     * 
     * @return Returns the someInteger.
     */
    @Column(name = "INTEGER_DATA", nullable = true)
    public Integer getSomeInteger() {
        return someInteger;
    }

    /**
     * Sets the someInteger field to the specified value.
     * 
     * @param aSomeInteger
     *            The someInteger to set.
     */
    public void setSomeInteger( Integer aSomeInteger ) {
        someInteger = aSomeInteger;
    }

}
