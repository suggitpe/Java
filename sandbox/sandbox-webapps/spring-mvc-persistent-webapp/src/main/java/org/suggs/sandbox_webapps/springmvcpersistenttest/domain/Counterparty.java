/*
 * Counterparty.java created on 2 Feb 2011 20:30:27 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.domain;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Domain entity for a Counterparty.
 *
 * @author suggitpe
 * @version 1.0 2 Feb 2011
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "COUNTERPARTY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "COUNTERPARTY_SQ")
public final class Counterparty extends AbstractEntityBase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Counterparty.class );

    private static final long serialVersionUID = 392537700576570720L;

    @Column(name = "NAME", nullable = false, length = 255)
    private String counterpartyName;

    @Column(name = "LEGAL_NAME", nullable = false, length = 255)
    private String counterpartyLegalName;

    @Column(name = "EXTERNAL_ID")
    private Integer externalId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTERPARTY_ID")
    private Set<CounterpartyContact> counterpartyContacts = new HashSet<CounterpartyContact>();

    /**
     * Constructs a new instance.
     */
    public Counterparty() {
    }

    /**
     * Constructs a new instance.
     */
    public Counterparty( String aName, String aLegalName, Integer aExternalId ) {
        counterpartyName = aName;
        counterpartyLegalName = aLegalName;
        externalId = aExternalId;
    }

    /**
     * Returns the value of counterpartyName.
     *
     * @return Returns the counterpartyName.
     */
    public String getCounterpartyName() {
        return counterpartyName;
    }

    /**
     * Sets the counterpartyName field to the specified value.
     *
     * @param aCounterpartyName The counterpartyName to set.
     */
    public void setCounterpartyName( String aCounterpartyName ) {
        counterpartyName = aCounterpartyName;
    }

    /**
     * Returns the value of counterpartyLegalName.
     *
     * @return Returns the counterpartyLegalName.
     */
    public String getCounterpartyLegalName() {
        return counterpartyLegalName;
    }

    /**
     * Sets the counterpartyLegalName field to the specified value.
     *
     * @param aCounterpartyLegalName The counterpartyLegalName to set.
     */
    public void setCounterpartyLegalName( String aCounterpartyLegalName ) {
        counterpartyLegalName = aCounterpartyLegalName;
    }

    /**
     * Returns the value of externalId.
     *
     * @return Returns the externalId.
     */
    public Integer getExternalId() {
        return externalId;
    }

    /**
     * Sets the externalId field to the specified value.
     *
     * @param aExternalId The externalId to set.
     */
    public void setExternalId( Integer aExternalId ) {
        externalId = aExternalId;
    }

    /**
     * Returns the value of counterpartyContacts.
     *
     * @return Returns the counterpartyContacts.
     */
    public Set<CounterpartyContact> getCounterpartyContacts() {
        return counterpartyContacts;
    }

    /**
     * Sets the counterpartyContacts field to the specified value.
     *
     * @param aCounterpartyContacts The counterpartyContacts to set.
     */
    public void setCounterpartyContacts( Set<CounterpartyContact> aCounterpartyContacts ) {
        counterpartyContacts = aCounterpartyContacts;
    }

    /**
     * Adds a counterparty contact
     *
     * @param aCounterpartyContact The counterpartyContact to add
     */
    public void addCounterpartyContact( CounterpartyContact aCounterpartyContact ) {
        counterpartyContacts.add( aCounterpartyContact );
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( counterpartyLegalName == null ) ? 0 : counterpartyLegalName.hashCode() );
        result = prime * result + ( ( counterpartyName == null ) ? 0 : counterpartyName.hashCode() );
        result = prime * result + ( ( externalId == null ) ? 0 : externalId.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( !super.equals( obj ) )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Counterparty other = ( Counterparty ) obj;
        if ( counterpartyLegalName == null ) {
            if ( other.counterpartyLegalName != null )
                return false;
        }
        else if ( !counterpartyLegalName.equals( other.counterpartyLegalName ) )
            return false;
        if ( counterpartyName == null ) {
            if ( other.counterpartyName != null )
                return false;
        }
        else if ( !counterpartyName.equals( other.counterpartyName ) )
            return false;
        if ( externalId == null ) {
            if ( other.externalId != null )
                return false;
        }
        else if ( !externalId.equals( other.externalId ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( super.toString() );
        builder.append( "Counterparty [counterpartyName=" );
        builder.append( counterpartyName );
        builder.append( ", counterpartyLegalName=" );
        builder.append( counterpartyLegalName );
        builder.append( ", externalId=" );
        builder.append( externalId );
        builder.append( "]" );
        return builder.toString();
    }

}
