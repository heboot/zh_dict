package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.dict.R;

import java.util.List;

public class ChooseReviewItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ChooseReviewItemAdapter(@Nullable List<String> data) {
        super(R.layout.item_review_choose, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
