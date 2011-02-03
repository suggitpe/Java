/*
 * Counterparty.java created on 2 Feb 2011 20:30:27 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.domain;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Domain entity for a Counterparty.
 * 
 * @author suggitpe
 * @version 1.0 2 Feb 2011
 */
@Entity
@Table(name = "COUNTERPARTY")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "COUNTERPARTY_SQ")
public class Counterparty extends AbstractEntityBase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Counterparty.class );

    private static final long serialVersionUID = 392537700576570720L;

    @Column(name = "NAME", nullable = false, length = 255)
    private String counterpartyName;

    @Column(name = "LEGAL_NAME", nullable = false, length = 255)
    private String counterpartyLegalName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTERPARTY_ID")
    private Set<CounterpartyContact> counterpartyContacts;

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
     * @param aCounterpartyName
     *            The counterpartyName to set.
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
     * @param aCounterpartyLegalName
     *            The counterpartyLegalName to set.
     */
    public void setCounterpartyLegalName( String aCounterpartyLegalName ) {
        counterpartyLegalName = aCounterpartyLegalName;
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
     * @param aCounterpartyContacts
     *            The counterpartyContacts to set.
     */
    public void setCounterpartyContacts( Set<CounterpartyContact> aCounterpartyContacts ) {
        counterpartyContacts = aCounterpartyContacts;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( counterpartyContacts == null ) ? 0 : counterpartyContacts.hashCode() );
        result = prime * result + ( ( counterpartyLegalName == null ) ? 0 : counterpartyLegalName.hashCode() );
        result = prime * result + ( ( counterpartyName == null ) ? 0 : counterpartyName.hashCode() );
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
        Counterparty other = (Counterparty) obj;
        if ( counterpartyContacts == null ) {
            if ( other.counterpartyContacts != null )
                return false;
        }
        else if ( !counterpartyContacts.equals( other.counterpartyContacts ) )
            return false;
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
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( super.toString() );
        builder.append( " Counterparty [counterpartyName=" );
        builder.append( counterpartyName );
        builder.append( ", counterpartyLegalName=" );
        builder.append( counterpartyLegalName );
        builder.append( ", counterpartyContacts=" );
        builder.append( counterpartyContacts );
        builder.append( "]" );
        return builder.toString();
    }

}
