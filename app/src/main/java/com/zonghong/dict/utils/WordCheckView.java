package com.zonghong.dict.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutWorkCheckBinding;

public class WordCheckView extends ConstraintLayout {

    private LayoutWorkCheckBinding binding;

    public WordCheckView(Context context) {
        super(context);
        initView();
    }

    public WordCheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WordCheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_work_check, this, true);

        binding.container.setOnClickListener((v) -> {
            if (binding.tvNo.isChecked()) {
                binding.tvNo.setChecked(false);
            } else {
                binding.tvNo.setChecked(true);
            }
        });


    }


}


