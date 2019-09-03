package com.zonghong.dict.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutReviewChooseBinding;
import com.zonghong.dict.utils.TTSUtils;

public class ReviewChooseView extends QMUILinearLayout {

    private LayoutReviewChooseBinding binding;

    private WordTypeBean wordTypeBean;

    public ReviewChooseView(Context context) {
        super(context);
        initView();
    }

    public ReviewChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ReviewChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private OnClickListener onClickListener;

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_review_choose, this, true);
        binding.container.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x25)
                , getResources().getDimensionPixelOffset(R.dimen.y5), 0.30f);


        onClickListener =
                (v) -> {
                    if (binding.cb.isChecked()) {
                        wordTypeBean.setCheck(false);
                        binding.cb.setChecked(false);
                    } else {
                        wordTypeBean.setCheck(true);
                        binding.cb.setChecked(true);
                    }
                };

        binding.container.setOnClickListener(onClickListener);
        binding.cb.setOnClickListener(onClickListener);


//        binding.tvTitle.setOnClickListener((v) -> {
//            TTSUtils.speak(wordTypeBean.getTitle());
//        });
    }

    public WordTypeBean getWordTypeBean() {
        return wordTypeBean;
    }

    public void setWordTypeBean(WordTypeBean wordTypeBean) {
        this.wordTypeBean = wordTypeBean;
        binding.tvTitle.setText(wordTypeBean.getTitle());
        if (wordTypeBean.isCheck()) {
            binding.cb.setChecked(true);
        } else {
            binding.cb.setChecked(false);
        }
    }


    public boolean isCheck() {
        return binding.cb.isChecked();
    }

    public void setBlackBackground() {
        binding.container.setBackgroundColor(Color.parseColor("#2B2D30"));
        binding.container.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x25)
                , getResources().getDimensionPixelOffset(R.dimen.y5), 0.30f);

    }

}
