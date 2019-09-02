package com.zonghong.dict.utils;

import android.widget.Toast;

import com.zonghong.dict.MAPP;

public class ToastUtils {

    public static void showToast(String msg) {
        Toast.makeText(MAPP.mapp, msg, Toast.LENGTH_SHORT).show();
    }

}
