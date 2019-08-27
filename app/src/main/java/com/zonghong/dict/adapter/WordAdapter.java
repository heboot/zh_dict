package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemWordBinding;
import com.zonghong.dict.fragment.ShijiWordListFragment;

import java.lang.ref.WeakReference;
import java.util.List;

public class WordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private WeakReference<ShijiWordListFragment> wordListFragmentWeakReference;

    public WordAdapter(@Nullable List<String> data, WeakReference<ShijiWordListFragment> wordListFragmentWeakReference) {
        super(R.layout.item_word, data);
        this.wordListFragmentWeakReference = wordListFragmentWeakReference;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ItemWordBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.getRoot().setOnClickListener((v) -> {
            wordListFragmentWeakReference.get().showDetailView();
        });

        binding.tvAddBook.setOnClickListener((v) -> {
            wordListFragmentWeakReference.get().showAddView();
        });

    }
}
