package com.zonghong.dict.utils;

import android.content.Intent;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.activitys.CheckWorkResultActivity;
import com.zonghong.dict.activitys.ChooseActivity;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.activitys.WordBookActivity;
import com.zonghong.dict.activitys.WordListActivity;
import com.zonghong.dict.common.ChooseType;

import java.io.Serializable;
import java.util.List;


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

    public static void intent2WordBookListActivity(List<WordListBean> wordListBeanList) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), WordBookActivity.class);
        intent.putExtra(MKey.DATA, (Serializable) wordListBeanList);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }


    public static void intent2WordListActivity(List<WordListBean> wordListBeanList) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), WordListActivity.class);
        intent.putExtra(MKey.DATA, (Serializable) wordListBeanList);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

    public static void intent2CheckResultActivity(String result) {
        intent = new Intent(MAPP.mapp.getCurrentActivity(), CheckWorkResultActivity.class);
        intent.putExtra(MKey.DATA, result);
        MAPP.mapp.getCurrentActivity().startActivity(intent);
    }

}
