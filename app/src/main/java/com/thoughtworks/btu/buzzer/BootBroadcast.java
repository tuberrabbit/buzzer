package com.thoughtworks.btu.buzzer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootBroadcast extends BroadcastReceiver {

    private final String PACKAGE_NAME = "com.thoughtworks.btu.buzzer";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "haha", Toast.LENGTH_LONG).show();
        Intent controller = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        context.startActivity(controller);
    }
}
