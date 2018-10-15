package com.tutorialspoint.iotservices.View;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorialspoint.iotservices.R;

public class SMSActivity extends AppCompatActivity {
    Button bSend;
    EditText etMobileNumber;
    EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        bSend = (Button) findViewById(R.id.bSend);
        etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        etText = (EditText) findViewById(R.id.etText);

        bSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.putExtra("address", etMobileNumber.getText().toString());
//                i.putExtra("sms_body", etText.getText().toString());
//                i.setType("vnd.android-dir/mms-sms");
//                startActivity(i);

                //different way
//                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("smsto:" + Uri.encode("01015148572")));
//                startActivity(intent);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String[] permissions = {Manifest.permission.SEND_SMS};
                    requestPermissions(permissions,1);
                }

                sendSMS(etMobileNumber.getText().toString(), etText.getText().toString());



            }
        });

    }

    private void sendSMS(String num, String body) {
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage("01015148572",null,"hello Android",null,null);

    }

}