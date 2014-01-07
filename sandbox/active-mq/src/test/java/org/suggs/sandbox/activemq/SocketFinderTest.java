package org.suggs.sandbox.activemq;

import org.junit.Test;

import java.net.ServerSocket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.suggs.sandbox.activemq.SocketFinder.findNextAvailablePortBetween;

public class SocketFinderTest {
    private static final int LOWER_PORT = 60000;
    private static final int HIGHER_PORT = 60010;

    @Test
    public void findsTheNextAvailablePortBetweenTwoValidPortNumbers() throws Exception {
        ServerSocket serverSocket = null;
        try {
            int firstPort = findNextAvailablePortBetween(LOWER_PORT, HIGHER_PORT);
            serverSocket = new ServerSocket(firstPort);
            int secondPort = findNextAvailablePortBetween(LOWER_PORT, HIGHER_PORT);
            assertThat(firstPort, is(not(equalTo(secondPort))));
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

}
