package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.dict.R;

import java.util.List;

public class CheckWorkAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CheckWorkAdapter(@Nullable List<String> data) {
        super(R.layout.item_word_check, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}