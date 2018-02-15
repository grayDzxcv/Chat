package com.emoskvc.app.server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

    private int port;
    private DatagramSocket socket;
    private Thread serverRun, manage, receive;
    private boolean running = false;

    public Server(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        serverRun = new Thread(new Runnable() {
            public void run() {
                running = true;
                manage();
                receive();
            }
        }, "ServerRun");
    }
    private void manage() {
        manage = new Thread(new Runnable() {
            public void run() {
                while (running) {

                }
            }
        }, "manage");
        manage.start();
    }

    private void receive() {
        receive = new Thread(new Runnable() {
            public void run() {
                while (running) {

                }
            }
        }, "receive");
        receive.start();

    }





}
