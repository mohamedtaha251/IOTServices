package com.tutorialspoint.iotservices.Threads;


import com.tutorialspoint.iotservices.Threads.ReadThread;
import com.tutorialspoint.iotservices.Threads.WriteThread;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {
    Socket socket = null;
    ReadThread readThread;
    WriteThread writeThread;
    String ip;
    int port;
    String id;

    public ClientThread(String serverIp, String serverPort, String clientID) {
        ip = serverIp;
        port = Integer.parseInt(serverPort);
        id = clientID;
    }

    @Override
    public void run() {
        super.run();
        try {
            socket = new Socket(ip, port);
            readThread = new ReadThread(socket);
            readThread.start();
            writeThread = new WriteThread(socket, id);
            writeThread.start();

        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
