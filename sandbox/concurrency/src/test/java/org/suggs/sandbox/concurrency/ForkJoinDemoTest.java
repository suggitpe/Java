package org.suggs.sandbox.concurrency;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ForkJoinDemoTest {

    public static final int ARRAY_SIZE = 1000001;
    public static final int ZERO = 0;

    @Test
    public void usesParallelLoadingToPopulateArray() {

        int[] theChuffingArrayToFill = new int[ARRAY_SIZE];

        ForkJoinPool threadPool = new ForkJoinPool();
        threadPool.invoke(new ArrayFillerTask(0, theChuffingArrayToFill.length, theChuffingArrayToFill));

        assertThat(theCountOfEmptyBoxesIn(theChuffingArrayToFill), is(ZERO));
    }

    private int theCountOfEmptyBoxesIn(int[] aArray) {
        int counter = 0;
        for(int cell: aArray){
            if(cell == 0){
                counter++;
            }
        }
        return counter;
    }

}
