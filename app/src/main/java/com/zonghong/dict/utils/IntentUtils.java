package com.zonghong.dict.utils;

import android.content.Intent;

import com.waw.hr.mutils.MKey;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.activitys.ChooseActivity;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.common.ChooseType;


public class IntentUtils {

    private static Intent intent;

    public static void doIntent(Class cls) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), cls);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2ChooseActivity(String typeId, ChooseType chooseType) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), ChooseActivity.class);
        intent.putExtra(MKey.TYPE_ID, typeId);
        intent.putExtra(MKey.TYPE, chooseType);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2RecordWordActivity(String typeId) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), RecordWordActivity.class);
        intent.putExtra(MKey.TYPE_ID, typeId);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

}
