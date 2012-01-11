package org.suggs.sandbox.test.builders.pojo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple pojo that will be used to demonstrate how to build objects from traditionalbuilders.
 * <p/>
 * User: suggitpe Date: 1/10/12 Time: 6:02 PM
 */

public class Posting {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Posting.class );

    private String postingName;
    private Date postingValidFromDate;
    private double postingValue;
    private Company company;
    private Balance balance;

    public String getPostingName() {
        return postingName;
    }

    public void setPostingName( String aPostingName ) {
        postingName = aPostingName;
    }

    public Date getPostingValidFromDate() {
        return postingValidFromDate;
    }

    public void setPostingValidFromDate( Date aPostingValidFromDate ) {
        postingValidFromDate = aPostingValidFromDate;
    }

    public double getPostingValue() {
        return postingValue;
    }

    public void setPostingValue( double aPostingValue ) {
        postingValue = aPostingValue;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany( Company aCompany ) {
        company = aCompany;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance( Balance aBalance ) {
        balance = aBalance;
    }

    @Override
    public String toString() {
        return "Posting{" +
                "postingName='" + postingName + '\'' +
                ", postingValidFromDate=" + postingValidFromDate +
                ", postingValue=" + postingValue +
                ", company=" + company +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Posting ) ) return false;

        Posting that = ( Posting ) o;

        if ( Double.compare( that.postingValue, postingValue ) != 0 ) return false;
        if ( balance != null ? !balance.equals( that.balance ) : that.balance != null ) return false;
        if ( company != null ? !company.equals( that.company ) : that.company != null ) return false;
        if ( postingName != null ? !postingName.equals( that.postingName ) : that.postingName != null ) return false;
        if ( postingValidFromDate != null ? !postingValidFromDate.equals( that.postingValidFromDate ) : that.postingValidFromDate != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = postingName != null ? postingName.hashCode() : 0;
        result = 31 * result + ( postingValidFromDate != null ? postingValidFromDate.hashCode() : 0 );
        temp = postingValue != +0.0d ? Double.doubleToLongBits( postingValue ) : 0L;
        result = 31 * result + ( int ) ( temp ^ ( temp >>> 32 ) );
        result = 31 * result + ( company != null ? company.hashCode() : 0 );
        result = 31 * result + ( balance != null ? balance.hashCode() : 0 );
        return result;
    }

}
