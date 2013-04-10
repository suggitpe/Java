package org.suggs.sandbox.test.builders.makeiteasy;

import org.suggs.sandbox.test.builders.pojo.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

import static com.natpryce.makeiteasy.Property.newProperty;

/**
 * Nat Pryce maker for Companies.
 */

public final class CompanyMaker {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( CompanyMaker.class );

    public static final Property<Company, Boolean> simpleFlag = newProperty();
    public static final Property<Company, String> companyName = newProperty();
    public static final Property<Company, Integer> size = Property.newProperty();

    public static final Instantiator<Company> Company = new Instantiator<Company>() {
        @Override
        public Company instantiate( PropertyLookup<Company> lookup ) {
            Company company = new Company();
            company.setSimpleFlag( lookup.valueOf( simpleFlag, Boolean.TRUE ) );
            company.setCompanyName( lookup.valueOf( companyName, "Default company name"));
            company.setSize( lookup.valueOf(size, 100000 ) );
            return company;
        }
    };
}
