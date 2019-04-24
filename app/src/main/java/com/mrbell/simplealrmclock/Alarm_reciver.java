package com.mrbell.simplealrmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm_reciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver.","yea");

        intent = new Intent(context,RingtoneService.class);
        context.startService(intent);
    }
}
