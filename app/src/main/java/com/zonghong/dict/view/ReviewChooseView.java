package com.zonghong.dict.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutReviewChooseBinding;

public class ReviewChooseView extends QMUILinearLayout {

    private LayoutReviewChooseBinding binding;

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

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_review_choose, this, true);
        binding.container.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x25)
                , getResources().getDimensionPixelOffset(R.dimen.y5), 0.30f);
        binding.container.setOnClickListener((v) -> {
            if (binding.cb.isChecked()) {
                binding.cb.setChecked(false);
            } else {
                binding.cb.setChecked(true);
            }
        });
    }

    public void setBlackBackground() {
        binding.container.setBackgroundColor(Color.parseColor("#2B2D30"));
        binding.container.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x25)
                , getResources().getDimensionPixelOffset(R.dimen.y5), 0.30f);

    }

}
