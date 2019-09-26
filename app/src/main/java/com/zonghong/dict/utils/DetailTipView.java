package com.zonghong.dict.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.databinding.DataBindingUtil;

import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.waw.hr.mutils.bean.WordDetailBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutDetailTipBinding;

public class DetailTipView extends QMUILinearLayout {

    private LayoutDetailTipBinding binding;

    public DetailTipView(Context context) {
        super(context);
        initView();
    }

    public DetailTipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DetailTipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_detail_tip, this, true);
        binding.container.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y5), 0.30f);
        binding.llytContainer.setOnClickListener((v) -> {
            if (binding.getRoot().getVisibility() == View.VISIBLE) {
                binding.getRoot().setVisibility(View.GONE);
            }
        });
    }

    public void show(WordDetailBean wordDetailBean) {
        binding.tvInfo.setText(wordDetailBean.getTitle() + "\n" + wordDetailBean.getComment() + "\n" + wordDetailBean.getExample());
        binding.getRoot().setVisibility(View.VISIBLE);
        binding.llytContainer.setVisibility(View.VISIBLE);
    }
}
