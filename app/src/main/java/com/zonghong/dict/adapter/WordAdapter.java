package com.zonghong.dict.adapter;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import android.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemWordBinding;
import com.zonghong.dict.fragment.ShijiWordListFragment;
import com.zonghong.dict.utils.TTSUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends BaseQuickAdapter<WordListBean, BaseViewHolder> {

    private WeakReference<ShijiWordListFragment> wordListFragmentWeakReference;

    private List<TextView> tvComments = new ArrayList<>();

    private Handler handler;

    private WordListBean currentBean;


    public WordAdapter(@Nullable List<WordListBean> data, WeakReference<ShijiWordListFragment> wordListFragmentWeakReference) {
        super(R.layout.item_word, data);
        this.wordListFragmentWeakReference = wordListFragmentWeakReference;
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (tvComments != null) {
                    for (TextView textView : tvComments) {
                        if (msg.what == (int) textView.getTag()) {
                            textView.setVisibility(View.INVISIBLE);
                            currentBean.setClicked(false);
                        }
                    }
//                    tvComments.clear();
//                    tvComment.setVisibility(View.INVISIBLE);
//                    currentBean.setClicked(false);
//                    notifyDataSetChanged();
                }
            }
        };
    }

    @Override
    protected void convert(BaseViewHolder helper, WordListBean item) {
        ItemWordBinding binding = DataBindingUtil.bind(helper.itemView);

        binding.tvDetail.setOnClickListener((v) -> {
            ((SwipeMenuLayout) binding.slytContainer).quickClose();
            wordListFragmentWeakReference.get().showDetailView(item.getId());
        });

        binding.tvAddBook.setOnClickListener((v) -> {
            ((SwipeMenuLayout) binding.slytContainer).quickClose();
            wordListFragmentWeakReference.get().showAddView(item);
        });
        binding.tvTitle.setText(item.getTitle());

        binding.llytContent.setOnClickListener((v) -> {
            if (item.isClicked()) {
                binding.tvComment.setText(item.getComment());
                binding.tvComment.setVisibility(View.VISIBLE);
                binding.tvComment.setTag(item.getId());
                tvComments.add(binding.tvComment);
                currentBean = item;
                handler.sendEmptyMessageDelayed(item.getId(), 1500);
            }
            item.setClicked(true);
            TTSUtils.speak(item.getTitle());
        });

    }
}
