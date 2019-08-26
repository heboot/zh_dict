package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.dict.R;

import java.util.List;

public class ChooseItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ChooseItemAdapter(@Nullable List<String> data) {
        super(R.layout.item_choose, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
