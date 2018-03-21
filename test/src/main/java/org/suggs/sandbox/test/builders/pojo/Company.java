package org.suggs.sandbox.test.builders.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple pojo to represent a compnay for builder tests.
 */

public class Company {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Company.class );


    private boolean simpleFlag;
    private String companyName;
    private int size;

    public boolean isSimpleFlag() {
        return simpleFlag;
    }

    public void setSimpleFlag( boolean aSimpleFlag ) {
        simpleFlag = aSimpleFlag;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName( String aCompanyName ) {
        companyName = aCompanyName;
    }

    public int getSize() {
        return size;
    }

    public void setSize( int aSize ) {
        size = aSize;
    }

    @Override
    public String toString() {
        return "Company{" +
                "simpleFlag=" + simpleFlag +
                ", companyName='" + companyName + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Company ) ) return false;

        Company that = ( Company ) o;

        if ( simpleFlag != that.simpleFlag ) return false;
        if ( size != that.size ) return false;
        if ( companyName != null ? !companyName.equals( that.companyName ) : that.companyName != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ( simpleFlag ? 1 : 0 );
        result = 31 * result + ( companyName != null ? companyName.hashCode() : 0 );
        result = 31 * result + size;
        return result;
    }
}
