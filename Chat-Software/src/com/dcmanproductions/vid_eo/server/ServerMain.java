package com.dcmanproductions.vid_eo.server;

import com.dcmanproductions.vid_eo.server.Server;
@SuppressWarnings("all")
public class ServerMain {
    private int port;
    private Server server;

    public ServerMain(int port) {
        this.port = port;
        this.server = new Server(port);
    }
}

