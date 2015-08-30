package com.thoughtworks.btu.buzzer;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;

public class Controller extends Activity {

    private static final int REQUEST_ENABLE = 47;   //大于等于0的任意整数
    private MediaPlayer mediaPlayer;
    private DevicePolicyManager devicePolicyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller);
        initResource();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_POWER) {
            playBuzzer();
            lockScreen();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    private void initResource() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.gitar);
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminComponent = new ComponentName(this, Device.class);
        if (!devicePolicyManager.isAdminActive(adminComponent)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
            startActivityForResult(intent, REQUEST_ENABLE);
        }
    }

    private void playBuzzer() {
        mediaPlayer.start();
    }

    private void lockScreen() {
        devicePolicyManager.lockNow();
    }

}
