/*
 * CounterpartyContact.java created on 2 Feb 2011 20:31:33 by suggitpe for project sandbox-spring-mvc-persistent-test
 * 
 */
package org.suggs.sandbox_webapps.springmvcpersistenttest.domain;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Domain entity for a Counterparty Contact.
 *
 * @author suggitpe
 * @version 1.0 2 Feb 2011
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "COUNTERPARTY_CONTACT")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "COUNTERPARTY_CONTACT_SQ")
public final class CounterpartyContact extends AbstractEntityBase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CounterpartyContact.class );

    private static final long serialVersionUID = -7311793062334565352L;

    @Column(name = "FIRST_NAME", nullable = false, length = 255)
    private String contactFirstName;

    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String contactLastName;

    @Column(name = "ADDRESS", nullable = true, length = 255)
    private String contactAddress;

    @Column(name = "POSTCODE", nullable = true, length = 255)
    private String contactPostcode;

    @Column(name = "TELEPHONE", nullable = false, length = 255)
    private String contactTelephone;

    /**
     * Constructs a new instance.
     */
    public CounterpartyContact() {
    }

    /**
     * Constructs a new instance.
     */
    public CounterpartyContact( String aFirstName, String aLastName, String aTelephoneNumber ) {
        contactFirstName = aFirstName;
        contactLastName = aLastName;
        contactTelephone = aTelephoneNumber;
    }

    /**
     * Constructs a new instance.
     */
    public CounterpartyContact( String aFirstName, String aLastName, String aAddress, String aPostcode, String aTelephoneNumber ) {
        contactFirstName = aFirstName;
        contactLastName = aLastName;
        contactAddress = aAddress;
        contactPostcode = aPostcode;
        contactTelephone = aTelephoneNumber;
    }

    /**
     * Returns the value of contactFirstName.
     *
     * @return Returns the contactFirstName.
     */
    public String getContactFirstName() {
        return contactFirstName;
    }

    /**
     * Sets the contactFirstName field to the specified value.
     *
     * @param aContactFirstName The contactFirstName to set.
     */
    public void setContactFirstName( String aContactFirstName ) {
        contactFirstName = aContactFirstName;
    }

    /**
     * Returns the value of contactLastName.
     *
     * @return Returns the contactLastName.
     */
    public String getContactLastName() {
        return contactLastName;
    }

    /**
     * Sets the contactLastName field to the specified value.
     *
     * @param aContactLastName The contactLastName to set.
     */
    public void setContactLastName( String aContactLastName ) {
        contactLastName = aContactLastName;
    }

    /**
     * Returns the value of contactAddress.
     *
     * @return Returns the contactAddress.
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * Sets the contactAddress field to the specified value.
     *
     * @param aContactAddress The contactAddress to set.
     */
    public void setContactAddress( String aContactAddress ) {
        contactAddress = aContactAddress;
    }

    /**
     * Returns the value of contactPostcode.
     *
     * @return Returns the contactPostcode.
     */
    public String getContactPostcode() {
        return contactPostcode;
    }

    /**
     * Sets the contactPostcode field to the specified value.
     *
     * @param aContactPostcode The contactPostcode to set.
     */
    public void setContactPostcode( String aContactPostcode ) {
        contactPostcode = aContactPostcode;
    }

    /**
     * Returns the value of contactTelephone.
     *
     * @return Returns the contactTelephone.
     */
    public String getContactTelephone() {
        return contactTelephone;
    }

    /**
     * Sets the contactTelephone field to the specified value.
     *
     * @param aContactTelephone The contactTelephone to set.
     */
    public void setContactTelephone( String aContactTelephone ) {
        contactTelephone = aContactTelephone;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( contactAddress == null ) ? 0 : contactAddress.hashCode() );
        result = prime * result + ( ( contactFirstName == null ) ? 0 : contactFirstName.hashCode() );
        result = prime * result + ( ( contactLastName == null ) ? 0 : contactLastName.hashCode() );
        result = prime * result + ( ( contactPostcode == null ) ? 0 : contactPostcode.hashCode() );
        result = prime * result + ( ( contactTelephone == null ) ? 0 : contactTelephone.hashCode() );
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
        CounterpartyContact other = ( CounterpartyContact ) obj;
        if ( contactAddress == null ) {
            if ( other.contactAddress != null )
                return false;
        }
        else if ( !contactAddress.equals( other.contactAddress ) )
            return false;
        if ( contactFirstName == null ) {
            if ( other.contactFirstName != null )
                return false;
        }
        else if ( !contactFirstName.equals( other.contactFirstName ) )
            return false;
        if ( contactLastName == null ) {
            if ( other.contactLastName != null )
                return false;
        }
        else if ( !contactLastName.equals( other.contactLastName ) )
            return false;
        if ( contactPostcode == null ) {
            if ( other.contactPostcode != null )
                return false;
        }
        else if ( !contactPostcode.equals( other.contactPostcode ) )
            return false;
        if ( contactTelephone == null ) {
            if ( other.contactTelephone != null )
                return false;
        }
        else if ( !contactTelephone.equals( other.contactTelephone ) )
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
        builder.append( " CounterpartyContact [contactFirstName=" );
        builder.append( contactFirstName );
        builder.append( ", contactLastName=" );
        builder.append( contactLastName );
        builder.append( ", contactAddress=" );
        builder.append( contactAddress );
        builder.append( ", contactPostcode=" );
        builder.append( contactPostcode );
        builder.append( ", contactTelephone=" );
        builder.append( contactTelephone );
        builder.append( "]" );
        return builder.toString();
    }

}
