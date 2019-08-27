package com.zonghong.dict.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutWordBookBinding;

public class WorkBookView extends ConstraintLayout {

    private LayoutWordBookBinding binding;

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

    private void initView() {

        binding = DataBindingUtil.inflate(LayoutInflater.from(MAPP.mapp), R.layout.layout_word_book, this, true);

        binding.container.setOnClickListener((v) -> {
            if (binding.cb.isChecked()) {
                binding.cb.setChecked(false);
            } else {
                binding.cb.setChecked(true);
            }
        });


    }

}
