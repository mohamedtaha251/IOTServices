package com.tutorialspoint.iotservices.View;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorialspoint.iotservices.Control.Flash;
import com.tutorialspoint.iotservices.R;
import com.tutorialspoint.iotservices.Threads.ClientThread;
import com.tutorialspoint.iotservices.Threads.WriteThread;

public class IOTActivity extends AppCompatActivity {
    Button btnFlash;
    Button btnConnectMQTT;
    Button btnSubscribe;
    Button btnPublish;
    EditText etIP;
    EditText etPort;
    EditText etID;
    EditText etTopic;
    EditText etMsg;

    String serverIP;
    String serverPort;
    String clientID;
    String topic;
    String msg;

    EditText tvLog;

    ClientThread clientThread;
    BroadcastReceiver receiver = null;


    static final int SUbSCRIBE_FLAG = 1;
    static final int PUBLISH_FLAG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iot_layout);



        Flash.checkPermission(this);

        btnFlash = (Button) findViewById(R.id.btn_flash);
        btnConnectMQTT = (Button) findViewById(R.id.btn_connect_mqtp);
        btnSubscribe = (Button) findViewById(R.id.btn_subscribe);
        btnPublish = (Button) findViewById(R.id.btn_publish);

        etIP = (EditText) findViewById(R.id.et_ip);
        etPort = (EditText) findViewById(R.id.et_port);
        etID = (EditText) findViewById(R.id.et_id);
        etTopic = (EditText) findViewById(R.id.et_topic);
        etMsg = (EditText) findViewById(R.id.et_msg);

        tvLog = (EditText) findViewById(R.id.tv_log);


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context con, Intent intent) {
                String viewName = intent.getStringExtra("VIEW");
                String readText = intent.getStringExtra("TEXT");

                //write thread send broadcast on tvlog
                if (viewName.equals("tvLog")) {
                    tvLog.append("RX: " + readText + "\n");
                }

                //read thread send broadcast on msg
                else if (viewName.equals("msg")) {
                    if (readText.equals("ON")) {
                        Toast.makeText(con, readText, Toast.LENGTH_SHORT).show();

                        Flash.turnOn(getBaseContext());
                    } else if (readText.equals("OFF")) {
                        Toast.makeText(con, readText, Toast.LENGTH_SHORT).show();
                        Flash.turnOff(getBaseContext());
                    } else {
                        Toast.makeText(getApplicationContext(), "Not Working and length=" + readText, Toast.LENGTH_LONG).show();

                    }
                }
            }
        };


        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Flash.toggle(getBaseContext());
            }
        });
        btnConnectMQTT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                serverIP = etIP.getText().toString();
                serverPort = etPort.getText().toString();
                clientID = etID.getText().toString();

                clientThread = new ClientThread(serverIP, serverPort, clientID);
                clientThread.start();
            }
        });
        btnSubscribe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                topic = etTopic.getText().toString();

                //send subscribe packet
                WriteThread.topic = topic;
                WriteThread.flag = SUbSCRIBE_FLAG;
            }
        });
        btnPublish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                topic = etTopic.getText().toString();
                msg = etMsg.getText().toString();

                //send publish packet
                WriteThread.topic = topic;
                WriteThread.msg = "," + msg + "," + "\n";
                WriteThread.flag = PUBLISH_FLAG;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        //listen on broad cast
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("NEW_CMD"));
    }

    @Override
    protected void onStop() {
        super.onStop();

        //stop listnining on broadcast reciever
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);

        //turn off flash
        Flash.turnOff(getBaseContext());

    }


}