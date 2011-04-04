/*
 * Stock.java created on 2 Sep 2010 07:26:40 by suggitpe for project sandbox-junit
 * 
 */
package org.suggs.sandbox.test.jbehave.trader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2010
 */
public class Stock {

    private static final Logger LOG = LoggerFactory.getLogger( Stock.class );

    private String stockName;
    private Double threshold;
    private Double price = Double.valueOf( 0.0 );
    private STATUS status = STATUS.OFF;

    public enum STATUS {
        OFF, ON
    }

    /**
     * Constructs a new instance.
     */
    public Stock( String aStockName, Double aThreshold ) {
        stockName = aStockName;
        threshold = aThreshold;
    }

    /**
     * Getter for the status of the stock alert.
     * 
     * @return the status of the stock alerting nmechanism
     */
    public STATUS getStatus() {
        return status;
    }

    public void tradeAt( Double aPrice ) {
        price = aPrice;
        evaluateBarrier( aPrice );
    }

    private void evaluateBarrier( Double aPrice ) {
        if ( aPrice.compareTo( threshold ) >= 0 ) {
            status = STATUS.ON;
        }
        else {
            status = STATUS.OFF;
        }
        LOG.debug( "Have evaluated that the threshold barrier for stock [" + stockName + "] is now ["
                   + status.name() + "] based on the price [" + price + "]" );
    }

}
