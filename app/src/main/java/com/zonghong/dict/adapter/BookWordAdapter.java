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
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.ItemChooseBinding;
import com.zonghong.dict.databinding.ItemWordBinding;
import com.zonghong.dict.databinding.ItemWordBookBinding;
import com.zonghong.dict.utils.TTSUtils;

import java.util.ArrayList;
import java.util.List;

public class BookWordAdapter extends BaseQuickAdapter<WordListBean, BaseViewHolder> {

    private List<TextView> tvComments = new ArrayList<>();

    private Handler handler;

    private WordListBean currentBean;

    public BookWordAdapter(@Nullable List<WordListBean> data) {
        super(R.layout.item_word_book, data);
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
        ItemWordBookBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.container.setWordListBean(item);

        if (item.isCheck()) {
            binding.container.getBinding().cb.setChecked(true);
        } else {
            binding.container.getBinding().cb.setChecked(false);
        }

        View.OnClickListener clickListener = (v) -> {
            if (item.isCheck()) {
                item.setCheck(false);
                binding.container.getBinding().cb.setChecked(false);
            } else {
                item.setCheck(true);
                binding.container.getBinding().cb.setChecked(true);
            }
        };

        binding.container.getBinding().getRoot().setOnClickListener((v) -> {
            if (StringUtils.isEmpty((String) binding.container.getBinding().tvTitle.getTag()) || binding.container.getBinding().tvTitle.getTag().equals("title")) {
                binding.container.getBinding().tvTitle.setText(item.getComment());
                binding.container.getBinding().tvTitle.setTag("comment");
            } else {
                binding.container.getBinding().tvTitle.setText(item.getTitle());
                binding.container.getBinding().tvTitle.setTag("title");

            }
            TTSUtils.speak(item.getTitle());
        });
        binding.container.getBinding().cb.setOnClickListener(clickListener);


    }
}