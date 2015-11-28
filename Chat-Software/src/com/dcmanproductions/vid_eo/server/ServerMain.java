/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo.server;

import com.dcmanproductions.vid_eo.server.Server;

public class ServerMain {
    private int port;
    private Server server;

    public ServerMain(int port) {
        this.port = port;
        this.server = new Server(port);
    }
}

