/*
 * RestrictionsMethodExecutionComparitor.java created on 24 Mar 2010 20:02:11 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.nullsaferestrictions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.NaturalIdentifier;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

/**
 * Test class that will test any method with a default set of
 * parameters for the Restrictions class.
 * 
 * @author suggitpe
 * @version 1.0 24 Mar 2010
 */
public class RestrictionsMethodExecutionComparitor {

    private static final Log LOG = LogFactory.getLog( RestrictionsMethodExecutionComparitor.class );

    private Method baseLine;
    private Method testee;
    private Map<Class<?>, Object> paramMap = new HashMap<Class<?>, Object>();

    public RestrictionsMethodExecutionComparitor( Method aBaseline, Method aTestee ) {
        baseLine = aBaseline;
        testee = aTestee;
        initialise();
    }

    private void initialise() {
        paramMap.put( String.class, "testString" );
        paramMap.put( Object.class, new Object() );
        paramMap.put( Object[].class, new Object[0] );
        paramMap.put( Collection.class, new ArrayList<String>() );
        paramMap.put( Criterion.class, Example.create( "foo" ) );
        paramMap.put( MatchMode.class, MatchMode.ANYWHERE );
        paramMap.put( Type.class, new StringType() );
        paramMap.put( Type[].class, new Type[0] );
        paramMap.put( Map.class, new HashMap<String, String>() );
        paramMap.put( int.class, new Integer( 0 ) );
    }

    public boolean compareMethods() {
        List<Object> params = buildParameterList();
        LOG.debug( "Comparing method [" + baseLine.getName() + "] with [" + params + "]" );

        Criterion c1 = invokeStaticMethod( baseLine, params );
        Criterion c2 = invokeStaticMethod( testee, params );
        LOG.debug( "Execution returned [" + c1.toString() + "] and [" + c2.toString() + "]" );
        if ( c1 instanceof NaturalIdentifier ) {
            // not sure what to do with them
            if ( c1 != null && c2 != null ) {
                return true;
            }
        }
        else if ( c1.toString().equals( c2.toString() ) ) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    private List<Object> buildParameterList() {
        List<Object> params = new ArrayList<Object>();
        for ( Class<?> clazz : baseLine.getParameterTypes() ) {
            Object o = paramMap.get( clazz );
            if ( o == null ) {
                throw new IllegalStateException( "Failed to get parameter for class ["
                                                 + clazz.getSimpleName() + "]" );
            }
            params.add( o );
        }
        return params;
    }

    private Criterion invokeStaticMethod( Method aMethodToInvoke, List<Object> params ) {
        try {
            return (Criterion) aMethodToInvoke.invoke( null, params.toArray( new Object[0] ) );
        }
        catch ( Exception e ) {
            throw new IllegalStateException( "failed to invoke method ["
                                             + aMethodToInvoke.getName() + "]", e );
        }
    }
}
