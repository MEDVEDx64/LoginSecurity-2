package com.lenis0012.bukkit.loginsecurity.modules.queryserver;

import java.io.*;
import java.net.Socket;

public class ClientInstance extends Thread {
    Socket socket;

    public ClientInstance(Socket s) {
        socket = s;
    }

    @Override public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                // Just a test
                while(in.readLine() != null);
                out.write("Test\n");
            }
        }

        catch (IOException eIO) {
        }

        finally {
            try {
                socket.close();
            }

            catch (IOException eIO) {
            }
        }
    }
}
