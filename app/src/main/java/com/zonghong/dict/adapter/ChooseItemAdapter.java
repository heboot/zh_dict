package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.common.ChooseType;
import com.zonghong.dict.databinding.ItemChooseBinding;
import com.zonghong.dict.utils.IntentUtils;

import java.util.List;

public class ChooseItemAdapter extends BaseQuickAdapter<WordTypeBean, BaseViewHolder> {


    private ChooseType chooseType;

    public ChooseItemAdapter(@Nullable List<WordTypeBean> data, ChooseType chooseType) {
        super(R.layout.item_choose, data);
        this.chooseType = chooseType;
    }

    @Override
    protected void convert(BaseViewHolder helper, WordTypeBean item) {
        ItemChooseBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.getRoot().setOnClickListener((v) -> {
            if (chooseType == ChooseType.CHOOSE_LEVEL) {
                IntentUtils.intent2RecordWordActivity(String.valueOf(item.getId()));
            } else if (chooseType == ChooseType.CHOOSE_TYPE) {
                if (item.isHasChild()) {
                    IntentUtils.intent2ChooseActivity(String.valueOf(item.getId()), ChooseType.CHOOSE_LEVEL);
                } else {
                    IntentUtils.intent2RecordWordActivity(String.valueOf(item.getId()));
                }

            }
//            else if (chooseType == ChooseType.CHOOSE_TYPE_CHILD) {
//                IntentUtils.intent2ChooseActivity(String.valueOf(item.getId()), ChooseType.CHOOSE_LEVEL);
//            }

        });
        binding.container.setTitle(item.getTitle());
    }
}
