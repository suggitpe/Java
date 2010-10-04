/*
 * TradingAccount.java created on 28 Sep 2010 17:36:23 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Persistable domain class for a Trading Account.
 * 
 * @author suggitpe
 * @version 1.0 28 Sep 2010
 */
@Entity
@Table(name = "STG_TRADING_ACCOUNT")
public class TradingAccount extends AbstractTradingAccount {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TradingAccount.class );

    @Temporal(TemporalType.DATE)
    @Column(name = "STATUS_UPDT_DATE")
    private Date statusUpdateDate;

    @Column(name = "STATUS")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "OPEN_DATE")
    private Date openDate;

    @Column(name = "CLIENT_CLASS")
    private String clientClass;

    @Column(name = "RISK_LOCATION")
    private String riskLocation;

    @Column(name = "RISK_CITY_CODE")
    private Integer riskCityCode;

    @Column(name = "SIMPLE_NAME")
    private String simpleName;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "COMMUNICATION_TYPE")
    private String communicationType;

    @Column(name = "ADDRESS_LINE")
    private String addressLine;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "FUNCTION_REFERENCE")
    private String functionReference;

    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE3")
    private String addressLine3;

    @Column(name = "ADDRESS_LINE4")
    private String addressLine4;

    /**
     * Constructs a new instance. Created for Hibernate only.
     */
    protected TradingAccount() {}

    /**
     * Constructs a new instance.
     */
    public TradingAccount( TradingAccountKey aTradingAccountKey, String aTradingAccountNK ) {
        super( aTradingAccountKey, aTradingAccountNK );
    }

    /**
     * Constructs a new instance.
     */
    public TradingAccount( TradingAccountKey aTradingAccountKey, String aTradingAccountNK,
                           Date aStatusUpdateDate, String aStatus, Date aOpenDate, String aClientClass,
                           String aRiskLocation, Integer aRiskCityCode, String aSimpleName,
                           String aShortName, String aCommunicationType, String aAddressLine, String aCity,
                           String aCountry, String aFunctionReference, String aAddressLine2,
                           String aAddressLine3, String aAddressLine4 ) {
        this( aTradingAccountKey, aTradingAccountNK );

        statusUpdateDate = aStatusUpdateDate;
        status = aStatus;
        openDate = aOpenDate;
        clientClass = aClientClass;
        riskLocation = aRiskLocation;
        riskCityCode = aRiskCityCode;
        simpleName = aSimpleName;
        shortName = aShortName;
        communicationType = aCommunicationType;
        addressLine = aAddressLine;
        city = aCity;
        country = aCountry;
        functionReference = aFunctionReference;
        addressLine2 = aAddressLine2;
        addressLine3 = aAddressLine3;
        addressLine4 = aAddressLine4;

    }

    /**
     * Returns the value of statusUpdateDate.
     * 
     * @return Returns the statusUpdateDate.
     */
    public Date getStatusUpdateDate() {
        return statusUpdateDate;
    }

    /**
     * Sets the statusUpdateDate field to the specified value.
     * 
     * @param aStatusUpdateDate
     *            The statusUpdateDate to set.
     */
    public void setStatusUpdateDate( Date aStatusUpdateDate ) {
        statusUpdateDate = aStatusUpdateDate;
    }

    /**
     * Returns the value of status.
     * 
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status field to the specified value.
     * 
     * @param aStatus
     *            The status to set.
     */
    public void setStatus( String aStatus ) {
        status = aStatus;
    }

    /**
     * Returns the value of openDate.
     * 
     * @return Returns the openDate.
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * Sets the openDate field to the specified value.
     * 
     * @param aOpenDate
     *            The openDate to set.
     */
    public void setOpenDate( Date aOpenDate ) {
        openDate = aOpenDate;
    }

    /**
     * Returns the value of clientClass.
     * 
     * @return Returns the clientClass.
     */
    public String getClientClass() {
        return clientClass;
    }

    /**
     * Sets the clientClass field to the specified value.
     * 
     * @param aClientClass
     *            The clientClass to set.
     */
    public void setClientClass( String aClientClass ) {
        clientClass = aClientClass;
    }

    /**
     * Returns the value of riskLocation.
     * 
     * @return Returns the riskLocation.
     */
    public String getRiskLocation() {
        return riskLocation;
    }

    /**
     * Sets the riskLocation field to the specified value.
     * 
     * @param aRiskLocation
     *            The riskLocation to set.
     */
    public void setRiskLocation( String aRiskLocation ) {
        riskLocation = aRiskLocation;
    }

    /**
     * Returns the value of riskCityCode.
     * 
     * @return Returns the riskCityCode.
     */
    public Integer getRiskCityCode() {
        return riskCityCode;
    }

    /**
     * Sets the riskCityCode field to the specified value.
     * 
     * @param aRiskCityCode
     *            The riskCityCode to set.
     */
    public void setRiskCityCode( Integer aRiskCityCode ) {
        riskCityCode = aRiskCityCode;
    }

    /**
     * Returns the value of simpleName.
     * 
     * @return Returns the simpleName.
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * Sets the simpleName field to the specified value.
     * 
     * @param aSimpleName
     *            The simpleName to set.
     */
    public void setSimpleName( String aSimpleName ) {
        simpleName = aSimpleName;
    }

    /**
     * Returns the value of shortName.
     * 
     * @return Returns the shortName.
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the shortName field to the specified value.
     * 
     * @param aShortName
     *            The shortName to set.
     */
    public void setShortName( String aShortName ) {
        shortName = aShortName;
    }

    /**
     * Returns the value of communicationType.
     * 
     * @return Returns the communicationType.
     */
    public String getCommunicationType() {
        return communicationType;
    }

    /**
     * Sets the communicationType field to the specified value.
     * 
     * @param aCommunicationType
     *            The communicationType to set.
     */
    public void setCommunicationType( String aCommunicationType ) {
        communicationType = aCommunicationType;
    }

    /**
     * Returns the value of addressLine.
     * 
     * @return Returns the addressLine.
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * Sets the addressLine field to the specified value.
     * 
     * @param aAddressLine
     *            The addressLine to set.
     */
    public void setAddressLine( String aAddressLine ) {
        addressLine = aAddressLine;
    }

    /**
     * Returns the value of city.
     * 
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city field to the specified value.
     * 
     * @param aCity
     *            The city to set.
     */
    public void setCity( String aCity ) {
        city = aCity;
    }

    /**
     * Returns the value of country.
     * 
     * @return Returns the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country field to the specified value.
     * 
     * @param aCountry
     *            The country to set.
     */
    public void setCountry( String aCountry ) {
        country = aCountry;
    }

    /**
     * Returns the value of functionReference.
     * 
     * @return Returns the functionReference.
     */
    public String getFunctionReference() {
        return functionReference;
    }

    /**
     * Sets the functionReference field to the specified value.
     * 
     * @param aFunctionReference
     *            The functionReference to set.
     */
    public void setFunctionReference( String aFunctionReference ) {
        functionReference = aFunctionReference;
    }

    /**
     * Returns the value of addressLine2.
     * 
     * @return Returns the addressLine2.
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the addressLine2 field to the specified value.
     * 
     * @param aAddressLine2
     *            The addressLine2 to set.
     */
    public void setAddressLine2( String aAddressLine2 ) {
        addressLine2 = aAddressLine2;
    }

    /**
     * Returns the value of addressLine3.
     * 
     * @return Returns the addressLine3.
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * Sets the addressLine3 field to the specified value.
     * 
     * @param aAddressLine3
     *            The addressLine3 to set.
     */
    public void setAddressLine3( String aAddressLine3 ) {
        addressLine3 = aAddressLine3;
    }

    /**
     * Returns the value of addressLine4.
     * 
     * @return Returns the addressLine4.
     */
    public String getAddressLine4() {
        return addressLine4;
    }

    /**
     * Sets the addressLine4 field to the specified value.
     * 
     * @param aAddressLine4
     *            The addressLine4 to set.
     */
    public void setAddressLine4( String aAddressLine4 ) {
        addressLine4 = aAddressLine4;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( addressLine == null ) ? 0 : addressLine.hashCode() );
        result = prime * result + ( ( addressLine2 == null ) ? 0 : addressLine2.hashCode() );
        result = prime * result + ( ( addressLine3 == null ) ? 0 : addressLine3.hashCode() );
        result = prime * result + ( ( addressLine4 == null ) ? 0 : addressLine4.hashCode() );
        result = prime * result + ( ( city == null ) ? 0 : city.hashCode() );
        result = prime * result + ( ( clientClass == null ) ? 0 : clientClass.hashCode() );
        result = prime * result + ( ( communicationType == null ) ? 0 : communicationType.hashCode() );
        result = prime * result + ( ( country == null ) ? 0 : country.hashCode() );
        result = prime * result + ( ( functionReference == null ) ? 0 : functionReference.hashCode() );
        result = prime * result + ( ( openDate == null ) ? 0 : openDate.hashCode() );
        result = prime * result + ( ( riskCityCode == null ) ? 0 : riskCityCode.hashCode() );
        result = prime * result + ( ( riskLocation == null ) ? 0 : riskLocation.hashCode() );
        result = prime * result + ( ( shortName == null ) ? 0 : shortName.hashCode() );
        result = prime * result + ( ( simpleName == null ) ? 0 : simpleName.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
        result = prime * result + ( ( statusUpdateDate == null ) ? 0 : statusUpdateDate.hashCode() );
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
        TradingAccount other = (TradingAccount) obj;
        if ( addressLine == null ) {
            if ( other.addressLine != null )
                return false;
        }
        else if ( !addressLine.equals( other.addressLine ) )
            return false;
        if ( addressLine2 == null ) {
            if ( other.addressLine2 != null )
                return false;
        }
        else if ( !addressLine2.equals( other.addressLine2 ) )
            return false;
        if ( addressLine3 == null ) {
            if ( other.addressLine3 != null )
                return false;
        }
        else if ( !addressLine3.equals( other.addressLine3 ) )
            return false;
        if ( addressLine4 == null ) {
            if ( other.addressLine4 != null )
                return false;
        }
        else if ( !addressLine4.equals( other.addressLine4 ) )
            return false;
        if ( city == null ) {
            if ( other.city != null )
                return false;
        }
        else if ( !city.equals( other.city ) )
            return false;
        if ( clientClass == null ) {
            if ( other.clientClass != null )
                return false;
        }
        else if ( !clientClass.equals( other.clientClass ) )
            return false;
        if ( communicationType == null ) {
            if ( other.communicationType != null )
                return false;
        }
        else if ( !communicationType.equals( other.communicationType ) )
            return false;
        if ( country == null ) {
            if ( other.country != null )
                return false;
        }
        else if ( !country.equals( other.country ) )
            return false;
        if ( functionReference == null ) {
            if ( other.functionReference != null )
                return false;
        }
        else if ( !functionReference.equals( other.functionReference ) )
            return false;
        if ( openDate == null ) {
            if ( other.openDate != null )
                return false;
        }
        else if ( !openDate.equals( other.openDate ) )
            return false;
        if ( riskCityCode == null ) {
            if ( other.riskCityCode != null )
                return false;
        }
        else if ( !riskCityCode.equals( other.riskCityCode ) )
            return false;
        if ( riskLocation == null ) {
            if ( other.riskLocation != null )
                return false;
        }
        else if ( !riskLocation.equals( other.riskLocation ) )
            return false;
        if ( shortName == null ) {
            if ( other.shortName != null )
                return false;
        }
        else if ( !shortName.equals( other.shortName ) )
            return false;
        if ( simpleName == null ) {
            if ( other.simpleName != null )
                return false;
        }
        else if ( !simpleName.equals( other.simpleName ) )
            return false;
        if ( status == null ) {
            if ( other.status != null )
                return false;
        }
        else if ( !status.equals( other.status ) )
            return false;
        if ( statusUpdateDate == null ) {
            if ( other.statusUpdateDate != null )
                return false;
        }
        else if ( !statusUpdateDate.equals( other.statusUpdateDate ) )
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TradingAccount [statusUpdateDate=" + statusUpdateDate + ", status=" + status + ", openDate="
               + openDate + ", clientClass=" + clientClass + ", riskLocation=" + riskLocation
               + ", riskCityCode=" + riskCityCode + ", simpleName=" + simpleName + ", shortName=" + shortName
               + ", communicationType=" + communicationType + ", addressLine=" + addressLine + ", city="
               + city + ", country=" + country + ", functionReference=" + functionReference
               + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3 + ", addressLine4="
               + addressLine4 + "]";
    }

}
