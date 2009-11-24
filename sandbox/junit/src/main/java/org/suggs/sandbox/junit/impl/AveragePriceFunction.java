/*
 * AveragePriceFunction.java created on 20 Nov 2009 19:23:13 by suggitpe for project SandBox - JUnit
 * 
 */
package org.suggs.sandbox.junit.impl;

import org.suggs.sandbox.junit.IDoubleDaoService;
import org.suggs.sandbox.junit.IFunction;

import java.util.Date;

/**
 * Function that will calculate the average price of two doubles
 * retrieved from a DAO.
 * 
 * @author suggitpe
 * @version 1.0 20 Nov 2009
 */
public class AveragePriceFunction implements IFunction<Double>
{

    private IDoubleDaoService doubleDaoService_;
    private Date[] dates_;

    /**
     * Constructs a new instance.
     * 
     * @param aDoubleDaoService
     * @param aDates
     */
    public AveragePriceFunction( IDoubleDaoService aDoubleDaoService, Date... aDates )
    {
        doubleDaoService_ = aDoubleDaoService;
        dates_ = aDates;
    }

    /**
     * @see org.suggs.sandbox.junit.IFunction#execute()
     */
    @Override
    public Double execute() throws Exception
    {
        double total = 0.0d;
        for ( Date date : dates_ )
        {
            total += doubleDaoService_.doubleForDate( date ).doubleValue();
        }
        return Double.valueOf( total / dates_.length );
    }
}
