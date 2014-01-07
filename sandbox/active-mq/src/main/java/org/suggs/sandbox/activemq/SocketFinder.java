package org.suggs.sandbox.activemq;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class SocketFinder {

    public static int findNextAvailablePortBetween(int lowerPort, int higherPort) throws IOException {
        for (int i = lowerPort; i < higherPort; i++) {
            if (isPortAvailable(i)) {
                return i;
            }
        }
        throw new IllegalStateException("Unable to find an available port between ["
                + lowerPort + "] and [" + higherPort + "]");
    }

    private static boolean isPortAvailable(int port) {
        ServerSocket serverSocket = null;
        DatagramSocket dataSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            dataSocket = new DatagramSocket(port);
            dataSocket.setReuseAddress(true);
            return true;
        } catch (final IOException e) {
            return false;
        } finally {
            if (dataSocket != null) {
                dataSocket.close();
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (final IOException e) {
                }
            }
        }
    }
}
