package com.tutorialspoint.iotservices.Threads;


        import com.tutorialspoint.iotservices.Utilities.MQTT;

        import java.io.IOException;
        import java.io.OutputStream;
        import java.net.Socket;

public class WriteThread extends Thread {
    OutputStream outputStream = null;
    String clientID;

    public static int flag = 0;
    public static String topic;
    public static String msg;


    public WriteThread(Socket socket, String id) {
        try {
            outputStream = socket.getOutputStream();
            clientID = id;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            MQTT.connect(clientID);
            ReadThread.sendText("tvLog",""+MQTT.packet);
            for (byte b : MQTT.packet) {
                outputStream.write(b);
            }
            outputStream.flush();
            Thread.sleep(100);

            while(true){
                if (flag == 1) {
                    flag = 0;
                    MQTT.subscribe(topic);
                    ReadThread.sendText("tvLog",""+MQTT.packet);
                    for (byte b : MQTT.packet) {
                        outputStream.write(b);
                    }
                    outputStream.flush();
                } else if (flag == 2){
                    flag = 0;
                    MQTT.publish(topic, msg, msg.length(), 0);
                    ReadThread.sendText("tvLog",""+MQTT.packet);
                    for (byte b : MQTT.packet) {
                        outputStream.write(b);
                    }
                    outputStream.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
