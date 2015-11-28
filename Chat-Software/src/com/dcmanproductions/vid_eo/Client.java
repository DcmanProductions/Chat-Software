/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    private static final long serialVersionUID = 1;
    private DatagramSocket socket;
    private String name;
    private String address;
    private int port;
    private InetAddress ip;
    private Thread send;
    private int ID = -1;

    public Client(String name, String address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }

    public boolean openConnection(String address) {
        try {
            this.socket = new DatagramSocket();
            this.ip = InetAddress.getByName(address);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
        catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String receive() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            this.socket.receive(packet);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String message = new String(packet.getData());
        return message;
    }

    public void send(final byte[] data) {
        this.send = new Thread("Send"){

            @Override
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, Client.this.ip, Client.this.port);
                try {
                    Client.this.socket.send(packet);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.send.start();
    }

    public void close() {
        new Thread(){

            @Override
            public void run() {
                DatagramSocket datagramSocket = Client.this.socket;
                synchronized (datagramSocket) {
                    Client.this.socket.close();
                }
            }
        }.start();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return this.ID;
    }

}

