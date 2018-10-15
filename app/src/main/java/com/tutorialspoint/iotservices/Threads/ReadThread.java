package com.tutorialspoint.iotservices.Threads;


        import android.content.Intent;
        import android.graphics.Camera;
        import android.support.v4.content.LocalBroadcastManager;

        import com.tutorialspoint.iotservices.View.MainActivity;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.Socket;

public class ReadThread extends Thread {
    static LocalBroadcastManager broadcaster = null;
    InputStream inputStream = null;
    int response = 0;
    String msg;
    String packetLineStr;
    String[] list;
    BufferedReader bufferedReader;


    public ReadThread(Socket socket) {
        broadcaster = LocalBroadcastManager.getInstance(MainActivity.context);
        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            if (inputStream != null) {
                try {
                    packetLineStr = bufferedReader.readLine();
                    if (!packetLineStr.equals(null))
                        list = packetLineStr.split(",");
                    if (list.length > 1)
                    {
                        msg = list[1];
                        sendText("msg",   msg);
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }



    public static void sendText(String view, String text) {
        Intent intent = new Intent("NEW_CMD");
        intent.putExtra("TEXT", text);
        intent.putExtra("VIEW", view);
        broadcaster.sendBroadcast(intent);
    }
}
