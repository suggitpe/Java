/*
 * JaxbContextUtilityTest.java created on 7 Mar 2010 09:47:10 by suggitpe for project sandbox-jaxb-test-client
 * 
 */
package org.suggs.sandbox.jaxb.util;

import org.suggs.sandbox.jaxb.dummydata.DummyData;
import org.suggs.sandbox.jaxb.dummydata.ObjectFactory;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Test class for the JaxbContext Utility.
 * 
 * @author suggitpe
 * @version 1.0 7 Mar 2010
 */
public class JaxbContextUtilityTest {

    private static final String SIMPLE_XML_OBJECT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<ns2:DummyData xmlns:ns2=\"urn:suggs:dummy-data:2010-01-01\"/>\n";

    @Test
    public void singletonProducesSameObjectInMemory() {
        JaxbContextUtility util1 = JaxbContextUtility.instance();
        JaxbContextUtility util2 = JaxbContextUtility.instance();
        assertThat( util1, sameInstance( util2 ) );
    }

    @Test
    public void marshallerProducesCorrectOutputForSimpleObject() throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        Object o = factory.createDummyData();
        String xml = JaxbContextUtility.instance().marshalObject( o );
        assertThat( SIMPLE_XML_OBJECT, equalTo( xml ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void marshallerThrowsExceptionWithNullObject() throws JAXBException {
        JaxbContextUtility.instance().marshalObject( null );
    }

    @Test(expected = JAXBException.class)
    public void marshallerThrowsExceptionWithInvalidObject() throws JAXBException {
        JaxbContextUtility.instance().marshalObject( new String( "foo" ) );
    }

    @SuppressWarnings("boxing")
    @Test
    public void unmarshallerCreatesCorrectObjectFromSimpleXml() throws JAXBException {
        ObjectFactory factory = new ObjectFactory();
        DummyData expectedResult = factory.createDummyData();
        DummyData dummyData = JaxbContextUtility.instance().unmarshalObject( SIMPLE_XML_OBJECT,
                                                                             DummyData.class );
        assertThat( expectedResult.isSetName(), equalTo( Boolean.FALSE ) );
        assertThat( expectedResult.isSetAddress(), equalTo( Boolean.FALSE ) );
        assertThat( expectedResult.isSetAttributes(), equalTo( Boolean.FALSE ) );
        assertThat( dummyData.isSetName(), equalTo( Boolean.FALSE ) );
        assertThat( dummyData.isSetAddress(), equalTo( Boolean.FALSE ) );
        assertThat( dummyData.isSetAttributes(), equalTo( Boolean.FALSE ) );
    }

    @Test
    public void unmarshallerCorrectlyValidatesXml() throws JAXBException {
        String xml = createValidDummyDataXml();
        DummyData data = JaxbContextUtility.instance().unmarshalObject( xml,
                                                                        DummyData.class,
                                                                        "xsd/dummy-data.xsd" );
        assertThat( data, not( nullValue() ) );
    }

    private String createValidDummyDataXml() {
        StringBuilder builder = new StringBuilder();
        builder.append( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" );
        builder.append( "<ns2:DummyData xmlns:ns2=\"urn:suggs:dummy-data:2010-01-01\">" );
        builder.append( "<name>" );
        builder.append( "<firstName>Jonny</firstName>" );
        builder.append( "<lastName>Morris</lastName>" );
        builder.append( "</name>" );
        builder.append( "<address/>" );
        builder.append( "<attributes/>" );
        builder.append( "</ns2:DummyData>" );
        return builder.toString();
    }

    @Test(expected = JAXBException.class)
    public void unmarshallerThrowsExceptionWhenNonCompliantXmlToSchema() throws JAXBException {
        JaxbContextUtility.instance().unmarshalObject( SIMPLE_XML_OBJECT,
                                                       DummyData.class,
                                                       "xsd/dummy-data.xsd" );
    }

    @Test(expected = IllegalArgumentException.class)
    public void unmarshallerThrowsExceptionWithUnreadableSchema() throws JAXBException {
        JaxbContextUtility.instance().unmarshalObject( SIMPLE_XML_OBJECT,
                                                       DummyData.class,
                                                       "xsd/test-unparsable.xsd" );
    }

    @Test(expected = IllegalArgumentException.class)
    public void unmarshallerThrowsExceptionFromMissingSchema() throws JAXBException {
        JaxbContextUtility.instance().unmarshalObject( SIMPLE_XML_OBJECT,
                                                       DummyData.class,
                                                       "xsd/you-wont-find-me.xsd" );
    }

    @Test(expected = JAXBException.class)
    public void unmarshallerThrowsExceptionWhenXmlAndClassMismatch() throws JAXBException {
        JaxbContextUtility.instance().unmarshalObject( SIMPLE_XML_OBJECT, String.class );
    }

    @Test(expected = JAXBException.class)
    public void unmarshallerThrowsExceptionWhithNonXmlString() throws JAXBException {
        JaxbContextUtility.instance().unmarshalObject( "yeah right this won't work!!", DummyData.class );
    }

    @Test(expected = IllegalArgumentException.class)
    public void unmarshallerThrowsExceptionWhithNullXmlString() throws JAXBException {
        JaxbContextUtility.instance().unmarshalObject( null, DummyData.class );
    }

    @Test(expected = IllegalArgumentException.class)
    public void unmarshallerThrowsExceptionWhithNullClass() throws JAXBException {
        JaxbContextUtility.instance().unmarshalObject( SIMPLE_XML_OBJECT, null );
    }

}
