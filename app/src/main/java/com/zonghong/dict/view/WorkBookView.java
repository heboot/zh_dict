package com.zonghong.dict.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutWordBookBinding;
import com.zonghong.dict.utils.TTSUtils;

public class WorkBookView extends ConstraintLayout {

    private LayoutWordBookBinding binding;

    private WordListBean wordListBean;

    public WorkBookView(Context context) {
        super(context);
        initView();
    }

    public WorkBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WorkBookView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private OnClickListener clickListener;

    private void initView() {

        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_word_book, this, true);

        clickListener = (v) -> {
            if (binding.cb.isChecked()) {
                if (wordListBean != null) {
                    wordListBean.setCheck(false);
                }
                binding.cb.setChecked(false);
            } else {
                if (wordListBean != null) {
                    wordListBean.setCheck(true);
                }
                binding.cb.setChecked(true);
            }
        };

        binding.container.setOnClickListener(clickListener);
        binding.cb.setOnClickListener(clickListener);

        binding.getRoot().setOnClickListener((v) -> {
            TTSUtils.speak(wordListBean.getTitle());
        });

    }

    public WordListBean getWordListBean() {
        return wordListBean;
    }

    public void setWordListBean(WordListBean wordListBean) {
        this.wordListBean = wordListBean;
        if (wordListBean.isCheck()) {
            binding.cb.setChecked(true);
        } else {
            binding.cb.setChecked(false);
        }
        binding.tvTitle.setText(wordListBean.getTitle());
    }
}
