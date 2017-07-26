package com.lenis0012.bukkit.loginsecurity.modules.queryserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class QueryServerThread extends Thread {
    public static final short PORT = 8563;

    boolean running = true;
    ServerSocket serverSocket = null;

    @Override public void run() {
        while(running) {
            try {
                serverSocket = new ServerSocket(PORT, 0, InetAddress.getLoopbackAddress());
                while(running) {
                    try {
                        new ClientInstance(serverSocket.accept()).start();
                    }

                    catch (Exception e) {
                    }
                }
            }

            catch (Exception e) {
                try {
                    if(!serverSocket.isClosed()) {
                        serverSocket.close();
                    }

                    Thread.sleep(2500);
                }

                catch (IOException eIO) {
                }

                catch (InterruptedException eInt) {
                }
            }
        }
    }

    public void shutdown() {
        running = false;
        try {
            serverSocket.close();
        }

        catch (IOException eIO) {
        }
    }
}
