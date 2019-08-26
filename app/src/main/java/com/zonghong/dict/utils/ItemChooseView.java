package com.zonghong.dict.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutChooseBinding;

public class ItemChooseView extends QMUILinearLayout {

    private LayoutChooseBinding binding;

    public ItemChooseView(Context context) {
        super(context);
        initView();
    }

    public ItemChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ItemChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_choose, this, true);
        binding.container.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x25)
                , getResources().getDimensionPixelOffset(R.dimen.y5), 0.30f);
    }

}
