package com.tutorialspoint.iotservices.Utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle=intent.getExtras();
        if(bundle==null)
            return;

        Object[] pdus= (Object[]) bundle.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage msg=SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender=msg.getDisplayOriginatingAddress();
            String body=msg.getDisplayMessageBody();

            Toast.makeText(context, "SMS: "+sender+"/n body:"+body, Toast.LENGTH_LONG).show();
        }
    }
}
