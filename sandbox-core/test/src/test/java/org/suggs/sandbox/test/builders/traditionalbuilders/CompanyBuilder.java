package org.suggs.sandbox.test.builders.traditionalbuilders;

import org.suggs.sandbox.test.builders.pojo.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder for company objects.
 * <p/>
 * User: suggitpe Date: 1/10/12 Time: 7:09 PM
 */

public class CompanyBuilder implements Builder<Company> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CompanyBuilder.class );

    private Company company;

    private CompanyBuilder() {
        company = new Company();
    }

    public static final CompanyBuilder aCompany() {
        return new CompanyBuilder();
    }

    public CompanyBuilder withSimpleFlagSetTo( boolean aFlagSet ) {
        company.setSimpleFlag( aFlagSet );
        return this;
    }

    public CompanyBuilder withCompanyName( String aCompanyName ) {
        company.setCompanyName( aCompanyName );
        return this;
    }

    public CompanyBuilder withSize( int aSize ) {
        company.setSize( aSize );
        return this;
    }

    @Override
    public Company build() {
        return company;
    }
}
