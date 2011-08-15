/*
 * NullSafeRestrictions.java created on 22 Mar 2010 07:00:51 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.nullsaferestrictions;

import java.util.Collection;
import java.util.Map;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.NaturalIdentifier;
import org.hibernate.criterion.PropertyExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

/**
 * Static class to call out to the Restrictions class after assessing whether the passed in attributes are
 * actually NULL, it is implemented as a proxy/decorator to the original Restrictions class. If they are null
 * it will make the relevant checks to ensure that a search for null is done rather than the normal SQL
 * operand.
 * 
 * @author suggitpe
 * @version 1.0 22 Mar 2010
 */
public final class NullSafeRestrictions {

    /**
     * Cannot instantiate this class.
     */
    private NullSafeRestrictions() {}

    public static Criterion idEq( Object value ) {
        return Restrictions.idEq( value );
    }

    public static Criterion eq( String propertyName, Object value ) {
        if ( value == null ) {
            return Restrictions.isNull( propertyName );
        }
        return Restrictions.eq( propertyName, value );
    }

    public static Criterion ne( String propertyName, Object value ) {
        if ( value == null ) {
            return Restrictions.isNotNull( propertyName );
        }
        return Restrictions.ne( propertyName, value );
    }

    public static Criterion like( String propertyName, Object value ) {
        if ( value == null ) {
            return Restrictions.isNull( propertyName );
        }
        return Restrictions.like( propertyName, value );
    }

    public static Criterion like( String propertyName, String value, MatchMode matchMode ) {
        if ( value == null ) {
            return Restrictions.isNull( propertyName );
        }
        return Restrictions.like( propertyName, value, matchMode );
    }

    public static Criterion ilike( String propertyName, String value, MatchMode matchMode ) {
        if ( value == null ) {
            return Restrictions.isNull( propertyName );
        }
        return Restrictions.ilike( propertyName, value, matchMode );
    }

    public static Criterion ilike( String propertyName, Object value ) {
        if ( value == null ) {
            return Restrictions.isNull( propertyName );
        }
        return Restrictions.ilike( propertyName, value );
    }

    public static Criterion gt( String propertyName, Object value ) {
        return Restrictions.gt( propertyName, value );
    }

    public static Criterion lt( String propertyName, Object value ) {
        return Restrictions.lt( propertyName, value );
    }

    public static Criterion le( String propertyName, Object value ) {
        return Restrictions.le( propertyName, value );
    }

    public static Criterion ge( String propertyName, Object value ) {
        return Restrictions.ge( propertyName, value );
    }

    public static Criterion between( String propertyName, Object lo, Object hi ) {
        return Restrictions.between( propertyName, lo, hi );
    }

    public static Criterion in( String propertyName, Object[] values ) {
        return Restrictions.in( propertyName, values );
    }

    public static Criterion in( String propertyName, Collection<?> values ) {
        return Restrictions.in( propertyName, values );
    }

    public static Criterion isNull( String propertyName ) {
        return Restrictions.isNull( propertyName );
    }

    public static PropertyExpression eqProperty( String propertyName, String otherPropertyName ) {
        return Restrictions.eqProperty( propertyName, otherPropertyName );
    }

    public static PropertyExpression neProperty( String propertyName, String otherPropertyName ) {
        return Restrictions.neProperty( propertyName, otherPropertyName );
    }

    public static PropertyExpression ltProperty( String propertyName, String otherPropertyName ) {
        return Restrictions.ltProperty( propertyName, otherPropertyName );
    }

    public static PropertyExpression leProperty( String propertyName, String otherPropertyName ) {
        return Restrictions.leProperty( propertyName, otherPropertyName );
    }

    public static PropertyExpression gtProperty( String propertyName, String otherPropertyName ) {
        return Restrictions.gtProperty( propertyName, otherPropertyName );
    }

    public static PropertyExpression geProperty( String propertyName, String otherPropertyName ) {
        return Restrictions.geProperty( propertyName, otherPropertyName );
    }

    public static Criterion isNotNull( String propertyName ) {
        return Restrictions.isNotNull( propertyName );
    }

    public static LogicalExpression and( Criterion lhs, Criterion rhs ) {
        return Restrictions.and( lhs, rhs );
    }

    public static LogicalExpression or( Criterion lhs, Criterion rhs ) {
        return Restrictions.or( lhs, rhs );
    }

    public static Criterion not( Criterion expression ) {
        return Restrictions.not( expression );
    }

    public static Criterion sqlRestriction( String sql, Object[] values, Type[] types ) {
        return Restrictions.sqlRestriction( sql, values, types );
    }

    public static Criterion sqlRestriction( String sql, Object value, Type type ) {
        return Restrictions.sqlRestriction( sql, value, type );
    }

    public static Criterion sqlRestriction( String sql ) {
        return Restrictions.sqlRestriction( sql );
    }

    public static Conjunction conjunction() {
        return Restrictions.conjunction();
    }

    public static Disjunction disjunction() {
        return Restrictions.disjunction();
    }

    public static Criterion allEq( Map<?, ?> propertyNameValues ) {
        return Restrictions.allEq( propertyNameValues );
    }

    public static Criterion isEmpty( String propertyName ) {
        return Restrictions.isEmpty( propertyName );
    }

    public static Criterion isNotEmpty( String propertyName ) {
        return Restrictions.isNotEmpty( propertyName );
    }

    public static Criterion sizeEq( String propertyName, int size ) {
        return Restrictions.sizeEq( propertyName, size );
    }

    public static Criterion sizeNe( String propertyName, int size ) {
        return Restrictions.sizeNe( propertyName, size );
    }

    public static Criterion sizeGt( String propertyName, int size ) {
        return Restrictions.sizeGt( propertyName, size );
    }

    public static Criterion sizeLt( String propertyName, int size ) {
        return Restrictions.sizeLt( propertyName, size );
    }

    public static Criterion sizeGe( String propertyName, int size ) {
        return Restrictions.sizeGe( propertyName, size );
    }

    public static Criterion sizeLe( String propertyName, int size ) {
        return Restrictions.sizeLe( propertyName, size );
    }

    public static NaturalIdentifier naturalId() {
        return Restrictions.naturalId();
    }

}
