package com.zonghong.dict.activitys;


import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityMainBinding;
import com.zonghong.dict.utils.IntentUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        binding.qlytTeaching.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y15), 0.30f);
        binding.tvDetection.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y15), 0.30f);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        binding.qlytTeaching.setOnClickListener((v) -> {
            IntentUtils.doIntent(TeachIndexActivity.class);
        });
        binding.tvDetection.setOnClickListener((v) -> {
            IntentUtils.doIntent(CheckWordActivity.class);
        });
    }
}
