package org.suggs.sandbox.concurrency;

import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class ArrayFillerTask extends RecursiveAction {

    private int startingPositionOfSubArray;
    private int endingPositionOfSubArray;
    private int[] theArray;
    private static final int THRESHOLD = 4000;


    public ArrayFillerTask(int aStartingPosition, int anEndingPosition, int[] anArray) {
        startingPositionOfSubArray = aStartingPosition;
        endingPositionOfSubArray = anEndingPosition;
        theArray = anArray;
    }

    @Override
    protected void compute() {
        if (theArrayPostionIsSmallEnoughToProcess()) {
            populateTheArrayPortionWithCrap();
        } else {
            divideAndConquer();
        }
    }

    private boolean theArrayPostionIsSmallEnoughToProcess() {
        return endingPositionOfSubArray - startingPositionOfSubArray < THRESHOLD;
    }

    private void divideAndConquer() {
        int midPointOfTheArray = ((endingPositionOfSubArray - startingPositionOfSubArray) / 2) + startingPositionOfSubArray;
        invokeAll(new ArrayFillerTask(startingPositionOfSubArray, midPointOfTheArray, theArray),
                new ArrayFillerTask(midPointOfTheArray, endingPositionOfSubArray, theArray));
    }

    private void populateTheArrayPortionWithCrap() {
        for (int i = startingPositionOfSubArray; i < endingPositionOfSubArray; ++i) {
            theArray[i] = new Random().nextInt();
        }

    }
}
