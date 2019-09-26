package com.zonghong.dict.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.CheckWordLocalBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemWordCheckBinding;

import java.util.List;

public class CheckWorkAdapter extends BaseQuickAdapter<CheckWordLocalBean, BaseViewHolder> {


    private CheckWordLocalBean selectBean;

    public CheckWorkAdapter(@Nullable List<CheckWordLocalBean> data) {
        super(R.layout.item_word_check, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckWordLocalBean item) {
        ItemWordCheckBinding binding = DataBindingUtil.bind(helper.itemView);

        binding.container.setCheckWordLocalBean(item);

        if (selectBean != null && item.getOption().equals(selectBean.getOption())) {
            item.setChecked(true);
            binding.container.getBinding().tvNo.setChecked(true);
        } else {
            item.setChecked(false);
            binding.container.getBinding().tvNo.setChecked(false);
        }


//        binding.container.setOnClickListener(onClickListener);


        View.OnClickListener onClickListener = (v) -> {
            if (item.isChecked()) {//binding.container.getBinding().tvNo.isChecked()
                item.setChecked(false);
                binding.container.getBinding().tvNo.setChecked(false);
                notifyDataSetChanged();
            } else {
                selectBean = item;
                item.setChecked(true);
                binding.container.getBinding().tvNo.setChecked(true);
                notifyDataSetChanged();
            }
        };

        binding.container.getBinding().getRoot().setOnClickListener(onClickListener);
        binding.container.getBinding().tvNo.setOnClickListener(onClickListener);
//        binding.container.getBinding().getRoot().setOnClickListener(onClickListener);
//        binding.container.getBinding().getRoot().setOnClickListener(onClickListener);

    }

    public void setSelectBean(CheckWordLocalBean selectBean) {
        this.selectBean = selectBean;
    }
}