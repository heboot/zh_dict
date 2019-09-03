package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.CheckWordLocalBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemWordCheckBinding;

import java.util.List;

public class CheckWorkAdapter extends BaseQuickAdapter<CheckWordLocalBean, BaseViewHolder> {

    public CheckWorkAdapter(@Nullable List<CheckWordLocalBean> data) {
        super(R.layout.item_word_check, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckWordLocalBean item) {
        ItemWordCheckBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.container.setCheckWordLocalBean(item);
    }
}