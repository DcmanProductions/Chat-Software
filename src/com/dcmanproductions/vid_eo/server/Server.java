package com.dcmanproductions.vid_eo.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dcmanproductions.vid_eo.ClientWindow;
@SuppressWarnings("all")
public class Server
implements Runnable {
    private List<ServerClient> clients = new ArrayList<ServerClient>();
    private List<Integer> clientResponse = new ArrayList<Integer>();
    private DatagramSocket socket;
    private int port;
    private boolean running = false;
    private Thread run;
    private Thread manage;
    private Thread send;
    private Thread receive;
    private final int MAX_ATTEMPTS = 5;
    private ClientWindow cw;
    private boolean raw = false;

    public Server(int port) {
        this.port = port;
        try {
            this.socket = new DatagramSocket(port);
        }
        catch (SocketException e) {
            e.printStackTrace();
            return;
        }
        this.run = new Thread((Runnable)this, "Server");
        this.run.start();
    }

    @Override
    public void run() {
        this.running = true;
        System.out.println("Server started on port " + this.port);
        this.manageClients();
        this.receive();
        Scanner scanner = new Scanner(System.in);
        block2 : while (this.running) {
            String text = scanner.nextLine();
            if (!text.startsWith("/")) {
                this.sendToAll("/m/Server: " + text + "/e/");
                continue;
            }
            if ((text = text.substring(1)).equals("raw")) {
                if (this.raw) {
                    System.out.println("Raw mode off.");
                } else {
                    System.out.println("Raw mode on.");
                }
                this.raw = !this.raw;
                continue;
            }
            if (text.equals("clients")) {
                System.out.println("Clients:");
                System.out.println("========");
                int i = 0;
                while (i < this.clients.size()) {
                    ServerClient c = this.clients.get(i);
                    System.out.println(String.valueOf(c.name) + "(" + c.getID() + "): " + c.address.toString() + ":" + c.port);
                    ++i;
                }
                System.out.println("========");
                continue;
            }
            if (this.cw.messageGLOBAL.contains("/kick")) {
                String name = text.split(" ")[1];
                int id = -1;
                boolean number = true;
                try {
                    id = Integer.parseInt(name);
                }
                catch (NumberFormatException e) {
                    number = false;
                }
                if (number) {
                    boolean exists = false;
                    int i = 0;
                    while (i < this.clients.size()) {
                        if (this.clients.get(i).getID() == id) {
                            exists = true;
                            break;
                        }
                        ++i;
                    }
                    if (exists) {
                        this.disconnect(id, true);
                        continue;
                    }
                    System.out.println("Client " + id + " doesn't exist! Check ID number.");
                    continue;
                }
                int i = 0;
                while (i < this.clients.size()) {
                    ServerClient c = this.clients.get(i);
                    if (name.equals(c.name)) {
                        this.disconnect(c.getID(), true);
                        continue block2;
                    }
                    ++i;
                }
                continue;
            }
            if (text.equals("help")) {
                this.printHelp();
                continue;
            }
            if (text.equals("quit")) {
                this.quit();
                continue;
            }
            System.out.println("Unknown command.");
            this.printHelp();
        }
        scanner.close();
    }

    private void printHelp() {
        System.out.println("Here is a list of all available commands:");
        System.out.println("=========================================");
        System.out.println("/raw - enables raw mode.");
        System.out.println("/clients - shows all connected clients.");
        System.out.println("/kick [users ID or username] - kicks a user.");
        System.out.println("/help - shows this help message.");
        System.out.println("/quit - shuts down the server.");
    }

    private void manageClients() {
        this.manage = new Thread("Manage"){

            @Override
            public void run() {
                while (Server.this.running) {
                    Server.this.sendToAll("/i/server");
                    Server.this.sendStatus();
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int i = 0;
                    while (i < Server.this.clients.size()) {
                        ServerClient c = (ServerClient)Server.this.clients.get(i);
                        if (!Server.this.clientResponse.contains(c.getID())) {
                            if (c.attempt >= 5) {
                                Server.this.disconnect(c.getID(), false);
                            } else {
                                ++c.attempt;
                            }
                        } else {
                            Server.this.clientResponse.remove(new Integer(c.getID()));
                            c.attempt = 0;
                        }
                        ++i;
                    }
                }
            }
        };
        this.manage.start();
    }

    private void sendStatus() {
        if (this.clients.size() <= 0) {
            return;
        }
        String users = "/u/";
        int i = 0;
        while (i < this.clients.size() - 1) {
            users = String.valueOf(users) + this.clients.get((int)i).name + "/n/";
            ++i;
        }
        users = String.valueOf(users) + this.clients.get((int)(this.clients.size() - 1)).name + "/e/";
        this.sendToAll(users);
    }

    private void receive() {
        this.receive = new Thread("Receive"){

            @Override
            public void run() {
                while (Server.this.running) {
                    byte[] data = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    try {
                        Server.this.socket.receive(packet);
                    }
                    catch (SocketException var3_4) {
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Server.this.process(packet);
                }
            }
        };
        this.receive.start();
    }

    private void sendToAll(String message) {
        if (message.startsWith("/m/")) {
            String text = message.substring(3);
            text = text.split("/e/")[0];
            System.out.println(message);
        }
        int i = 0;
        while (i < this.clients.size()) {
            ServerClient client = this.clients.get(i);
            this.send(message.getBytes(), client.address, client.port);
            ++i;
        }
    }

    private void send(final byte[] data, final InetAddress address, final int port) {
        this.send = new Thread("Send"){

            @Override
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                try {
                    Server.this.socket.send(packet);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.send.start();
    }

    private void send(String message, InetAddress address, int port) {
        message = String.valueOf(message) + "/e/";
        this.send(message.getBytes(), address, port);
    }

    private void process(DatagramPacket packet) {
        String string = new String(packet.getData());
        if (this.raw) {
            System.out.println(string);
        }
        if (string.startsWith("/c/")) {
            int id = UniqueIdentifier.getIdentifier();
            String name = string.split("/c/|/e/")[1];
            System.out.println(String.valueOf(name) + "(" + id + ") connected!");
            this.clients.add(new ServerClient(name, packet.getAddress(), packet.getPort(), id));
            String ID = "/c/" + id;
            this.send(ID, packet.getAddress(), packet.getPort());
        } else if (string.startsWith("/m/")) {
            this.sendToAll(string);
        } else if (string.startsWith("/d/")) {
            String id = string.split("/d/|/e/")[1];
            this.disconnect(Integer.parseInt(id), true);
        } else if (string.startsWith("/i/")) {
            this.clientResponse.add(Integer.parseInt(string.split("/i/|/e/")[1]));
        } else {
            System.out.println(string);
        }
    }

    private void quit() {
        int i = 0;
        while (i < this.clients.size()) {
            this.disconnect(this.clients.get(i).getID(), true);
            ++i;
        }
        this.running = false;
        this.socket.close();
    }

    private void disconnect(int id, boolean status) {
        ServerClient c = null;
        boolean existed = false;
        int i = 0;
        while (i < this.clients.size()) {
            if (this.clients.get(i).getID() == id) {
                c = this.clients.get(i);
                this.clients.remove(i);
                existed = true;
                break;
            }
            ++i;
        }
        if (!existed) {
            return;
        }
        String message = "";
        message = status ? "Client " + c.name + " (" + c.getID() + ") @ " + c.address.toString() + ":" + c.port + " disconnected." : "Client " + c.name + " (" + c.getID() + ") @ " + c.address.toString() + ":" + c.port + " timed out.";
        System.out.println(message);
    }

}

