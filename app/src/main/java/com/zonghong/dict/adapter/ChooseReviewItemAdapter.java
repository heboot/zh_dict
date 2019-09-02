package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemChooseBinding;
import com.zonghong.dict.databinding.ItemReviewChooseBinding;

import java.util.List;

public class ChooseReviewItemAdapter extends BaseQuickAdapter<WordTypeBean, BaseViewHolder> {

    public ChooseReviewItemAdapter(@Nullable List<WordTypeBean> data) {
        super(R.layout.item_review_choose, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WordTypeBean item) {
        ItemReviewChooseBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.container.setWordTypeBean(item);
    }


}
