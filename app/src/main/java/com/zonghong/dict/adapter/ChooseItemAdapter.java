package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.dict.R;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.databinding.ItemChooseBinding;
import com.zonghong.dict.utils.IntentUtils;

import java.util.List;

public class ChooseItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ChooseItemAdapter(@Nullable List<String> data) {
        super(R.layout.item_choose, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ItemChooseBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.getRoot().setOnClickListener((v) -> {
            IntentUtils.doIntent(RecordWordActivity.class);
        });
    }
}
