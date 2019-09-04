package com.zonghong.dict.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemWordBinding;
import com.zonghong.dict.fragment.ShijiWordListFragment;
import com.zonghong.dict.utils.TTSUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class WordAdapter extends BaseQuickAdapter<WordListBean, BaseViewHolder> {

    private WeakReference<ShijiWordListFragment> wordListFragmentWeakReference;

    public WordAdapter(@Nullable List<WordListBean> data, WeakReference<ShijiWordListFragment> wordListFragmentWeakReference) {
        super(R.layout.item_word, data);
        this.wordListFragmentWeakReference = wordListFragmentWeakReference;
    }

    @Override
    protected void convert(BaseViewHolder helper, WordListBean item) {
        ItemWordBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.tvDetail.setOnClickListener((v) -> {
            wordListFragmentWeakReference.get().showDetailView(item.getId());
        });

        binding.tvAddBook.setOnClickListener((v) -> {
            wordListFragmentWeakReference.get().showAddView(item);
        });
        binding.tvTitle.setText(item.getTitle());

        binding.getRoot().setOnClickListener((v) -> {
            TTSUtils.speak(item.getTitle());
        });

    }
}
