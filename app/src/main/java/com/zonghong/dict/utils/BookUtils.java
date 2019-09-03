package com.zonghong.dict.utils;

import com.alibaba.fastjson.JSON;
import com.waw.hr.mutils.PreferencesUtils;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.MAPP;

import java.util.ArrayList;
import java.util.List;

public class BookUtils {

    public static void addWordBook(WordListBean wordListBean) {
        String json = PreferencesUtils.getString(MAPP.mapp, "wordList");

        List<WordListBean> wordListBeanList;

        if (StringUtils.isEmpty(json)) {
            wordListBeanList = new ArrayList<>();
        } else {
            wordListBeanList = JSON.parseArray(json, WordListBean.class);
        }

        wordListBeanList.add(wordListBean);

        String result = JSON.toJSONString(wordListBeanList);

        PreferencesUtils.putString(MAPP.mapp, "wordList", result);

        ToastUtils.showToast("添加成功");
    }


    public static List<WordListBean> getLocalWordlistBean() {
        String json = PreferencesUtils.getString(MAPP.mapp, "wordList");
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSON.parseArray(json, WordListBean.class);
    }


    public static void delWordBook(List<WordListBean> wordListBeans) {
        PreferencesUtils.putString(MAPP.mapp, "wordList", JSON.toJSONString(wordListBeans));
    }


}
