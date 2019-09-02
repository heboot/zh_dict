package com.zonghong.dict.activitys;

import android.view.View;

import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.common.ChooseType;
import com.zonghong.dict.databinding.ActivityTeachIndexBinding;
import com.zonghong.dict.utils.IntentUtils;

public class TeachIndexActivity extends BaseActivity<ActivityTeachIndexBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_teach_index;
    }

    @Override
    public void initUI() {
        binding.qlytRecite.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y15), 0.30f);
        binding.qlytBook.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y15), 0.30f);
        binding.includeToolbar.tvTitle.setText("单词教学");
        setBackVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.qlytBook.setOnClickListener((v) -> {
            IntentUtils.doIntent(ChooseActivity.class);
        });
        binding.qlytRecite.setOnClickListener((v) -> {
            IntentUtils.intent2ChooseActivity("0", ChooseType.CHOOSE_TYPE);
        });
    }
}
