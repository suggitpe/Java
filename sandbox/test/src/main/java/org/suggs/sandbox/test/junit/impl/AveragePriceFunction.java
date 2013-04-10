/*
 * AveragePriceFunction.java created on 20 Nov 2009 19:23:13 by suggitpe for project SandBox - JUnit
 * 
 */
package org.suggs.sandbox.test.junit.impl;

import org.suggs.sandbox.test.junit.DoubleDaoException;
import org.suggs.sandbox.test.junit.IDoubleDaoService;
import org.suggs.sandbox.test.junit.IFunction;

import java.util.Date;

/**
 * Function that will calculate the average price of two doubles retrieved from a DAO.
 */
public class AveragePriceFunction implements IFunction<Double> {

    private IDoubleDaoService doubleDaoService;
    private Date[] dates;

    public AveragePriceFunction( IDoubleDaoService aDoubleDaoService, Date... aDates ) {
        doubleDaoService = aDoubleDaoService;
        dates = aDates;
    }

    @Override
    public Double execute() throws DoubleDaoException {
        double total = 0.0d;
        for ( Date date : dates ) {
            total += doubleDaoService.doubleForDate( date ).doubleValue();
        }
        return Double.valueOf( total / dates.length );
    }
}
