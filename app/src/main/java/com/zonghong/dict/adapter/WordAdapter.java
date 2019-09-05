package com.zonghong.dict.adapter;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daimajia.swipe.SwipeLayout;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemWordBinding;
import com.zonghong.dict.fragment.ShijiWordListFragment;
import com.zonghong.dict.utils.TTSUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class WordAdapter extends BaseQuickAdapter<WordListBean, BaseViewHolder> {

    private WeakReference<ShijiWordListFragment> wordListFragmentWeakReference;

    private TextView tvComment;

    private Handler handler;

    private WordListBean currentBean;


    public WordAdapter(@Nullable List<WordListBean> data, WeakReference<ShijiWordListFragment> wordListFragmentWeakReference) {
        super(R.layout.item_word, data);
        this.wordListFragmentWeakReference = wordListFragmentWeakReference;
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (tvComment != null) {
                    tvComment.setVisibility(View.INVISIBLE);
//                    currentBean.setClicked(false);
//                    notifyDataSetChanged();
                }
            }
        };
    }

    @Override
    protected void convert(BaseViewHolder helper, WordListBean item) {
        ItemWordBinding binding = DataBindingUtil.bind(helper.itemView);
        //set show mode.
        binding.slytContainer.setShowMode(SwipeLayout.ShowMode.PullOut);

//add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
        binding.slytContainer.addDrag(SwipeLayout.DragEdge.Right, binding.slytContainer.findViewById(R.id.bottom_wrapper));

        binding.tvDetail.setOnClickListener((v) -> {
            binding.slytContainer.toggle();
            wordListFragmentWeakReference.get().showDetailView(item.getId());
        });

        binding.tvAddBook.setOnClickListener((v) -> {
            binding.slytContainer.toggle();
            wordListFragmentWeakReference.get().showAddView(item);
        });
        binding.tvTitle.setText(item.getTitle());

        binding.llytContent.setOnClickListener((v) -> {
            if (item.isClicked()) {
                binding.tvComment.setText(item.getComment());
                binding.tvComment.setVisibility(View.VISIBLE);
                tvComment = binding.tvComment;
                currentBean = item;
                handler.sendEmptyMessageDelayed(1, 1500);
            }
            item.setClicked(true);
            TTSUtils.speak(item.getTitle());
        });

    }
}
