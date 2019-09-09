package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.R;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.databinding.ItemChooseBinding;

import java.lang.ref.WeakReference;
import java.util.List;

public class ChooseLevelAdapter extends BaseQuickAdapter<WordTypeBean, BaseViewHolder> {


    private WeakReference<RecordWordActivity> recordWordActivityWeakReference;

    public ChooseLevelAdapter(@Nullable List<WordTypeBean> data, WeakReference<RecordWordActivity> recordWordActivityWeakReference) {
        super(R.layout.item_choose, data);
        this.recordWordActivityWeakReference = recordWordActivityWeakReference;
    }

    @Override
    protected void convert(BaseViewHolder helper, WordTypeBean item) {
        ItemChooseBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.getRoot().setOnClickListener((v) -> {
            recordWordActivityWeakReference.get().showShijiWordListFragment(String.valueOf(item.getId()));
        });
        binding.container.setTitle(item.getTitle());
    }
}
