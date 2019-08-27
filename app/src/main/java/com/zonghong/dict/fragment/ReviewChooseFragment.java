package com.zonghong.dict.fragment;

import android.os.Bundle;

import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseFragment;
import com.zonghong.dict.databinding.ActivityReviewChooseBinding;

public class ReviewChooseFragment extends BaseFragment<ActivityReviewChooseBinding> {


    public static ReviewChooseFragment newInstance() {
        Bundle args = new Bundle();
        ReviewChooseFragment fragment = new ReviewChooseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_review_choose;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
