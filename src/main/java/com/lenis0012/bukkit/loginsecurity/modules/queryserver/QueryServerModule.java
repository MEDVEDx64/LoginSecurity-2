package com.lenis0012.bukkit.loginsecurity.modules.queryserver;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import com.lenis0012.pluginutils.Module;

public class QueryServerModule extends Module<LoginSecurity> {
    QueryServerThread server = null;

    @Override public void enable() {
        server = new QueryServerThread();
        server.start();
    }

    @Override public void disable() {
        if(server != null) {
            server.shutdown();
            server = null;
        }
    }

    public QueryServerModule(LoginSecurity plugin) {
        super(plugin);
    }
}
