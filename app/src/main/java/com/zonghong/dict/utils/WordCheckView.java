package com.zonghong.dict.utils;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import android.databinding.DataBindingUtil;

import com.waw.hr.mutils.bean.CheckWordLocalBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutWorkCheckBinding;

public class WordCheckView extends ConstraintLayout {

    private LayoutWorkCheckBinding binding;

    private CheckWordLocalBean checkWordLocalBean;

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

    private OnClickListener onClickListener;

    private void initView() {

        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_work_check, this, true);

//        onClickListener = (v) -> {
//            if (binding.tvNo.isChecked()) {
//                if (checkWordLocalBean != null) {
//                    checkWordLocalBean.setChecked(false);
//                }
//                binding.tvNo.setChecked(false);
//            } else {
//                if (checkWordLocalBean != null) {
//                    checkWordLocalBean.setChecked(true);
//                }
//                binding.tvNo.setChecked(true);
//            }
//        };
//
//        binding.container.setOnClickListener(onClickListener);
//        binding.tvNo.setOnClickListener(onClickListener);


//        binding.tvTitle.setOnClickListener((v) -> {
//            TTSUtils.speak(checkWordLocalBean.getTitle());
//        });


    }

    public LayoutWorkCheckBinding getBinding() {
        return binding;
    }


    public CheckWordLocalBean getCheckWordLocalBean() {
        return checkWordLocalBean;
    }

    public void setCheckWordLocalBean(CheckWordLocalBean checkWordLocalBean) {
        this.checkWordLocalBean = checkWordLocalBean;
        binding.tvTitle.setText(checkWordLocalBean.getTitle());
        binding.tvNo.setText(checkWordLocalBean.getOption());
//        if (checkWordLocalBean.isChecked()) {
//            binding.tvNo.setChecked(true);
//        } else {
//            binding.tvNo.setChecked(false);
//        }
    }
}


