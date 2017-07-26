package com.lenis0012.bukkit.loginsecurity.modules.queryserver;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import com.lenis0012.bukkit.loginsecurity.hashing.Algorithm;
import com.lenis0012.bukkit.loginsecurity.session.PlayerSession;
import com.lenis0012.bukkit.loginsecurity.storage.PlayerProfile;

import java.io.*;
import java.net.Socket;

public class ClientInstance extends Thread {
    Socket socket;

    public ClientInstance(Socket s) {
        socket = s;
    }

    public static boolean checkLoginPassword(String login, String password) {
        PlayerSession session = LoginSecurity.getSessionManager().getOfflineSession(login);
        if(session == null) {
            return false;
        }

        PlayerProfile profile = session.getProfile();
        if(profile == null) {
            return false;
        }

        Algorithm algorithm = Algorithm.getById(profile.getHashingAlgorithm());
        if(algorithm == null) {
            return false;
        }

        return algorithm.check(password, profile.getPassword());
    }

    @Override public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                String login = in.readLine();
                String password = in.readLine();

                if(login == null || password == null) {
                    return;
                }

                if(checkLoginPassword(login, password)) {
                    out.write("OK\n");
                }
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
