package com.zonghong.dict.activitys;

import android.view.View;

import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityCheckResultBinding;

public class CheckWorkResultActivity extends BaseActivity<ActivityCheckResultBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_result;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("词汇量检测评估结果");

        binding.qlytContainer.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y15), 0.30f);


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
