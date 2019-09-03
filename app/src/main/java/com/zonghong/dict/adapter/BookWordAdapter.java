package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemChooseBinding;
import com.zonghong.dict.databinding.ItemWordBinding;
import com.zonghong.dict.databinding.ItemWordBookBinding;

import java.util.List;

public class BookWordAdapter extends BaseQuickAdapter<WordListBean, BaseViewHolder> {

    public BookWordAdapter(@Nullable List<WordListBean> data) {
        super(R.layout.item_word_book, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WordListBean item) {
        ItemWordBookBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.container.setWordListBean(item);
    }
}