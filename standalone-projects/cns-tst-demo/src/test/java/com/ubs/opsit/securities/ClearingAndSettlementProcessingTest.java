package com.ubs.opsit.securities;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: suggitpe
 * Date: 10/29/12
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClearingAndSettlementProcessingTest {

    @Test
    public void shouldProcessIncomingAllocationToProduceSettlement(){
        Trade trade = TradeBuilder.build();
        Application application = new ClearingAndSettlementApplication();
        assertThat(waitFor(application.receiveMessage), containsAll())
    }


    @Test
    public void foo(){
        new TradeBuilder().with().a().and().a()
    }

}
