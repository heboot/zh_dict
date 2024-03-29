package com.zonghong.dict.activitys;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityRecordIndexBinding;
import com.zonghong.dict.fragment.ReviewChooseFragment;
import com.zonghong.dict.fragment.ReviewWordListChooseFragment;
import com.zonghong.dict.fragment.ShijiChooseFragment;
import com.zonghong.dict.fragment.ShijiWordListFragment;
import com.zonghong.dict.utils.TTSUtils;

import java.util.List;

import me.yokeyword.fragmentation.ISupportFragment;

public class RecordWordActivity extends BaseActivity<ActivityRecordIndexBinding> {

    private ShijiChooseFragment shijiChooseFragment;

    private ReviewChooseFragment reviewChooseFragment;

    private ShijiWordListFragment shijiWordListFragment;

    private ISupportFragment currentFragment;

    private ReviewWordListChooseFragment reviewWordListChooseFragment;

    private ShijiWordListFragment reviewWordListFragment;

    private String typeId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_index;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        typeId = (String) getIntent().getExtras().get(MKey.TYPE_ID);
        shijiChooseFragment = ShijiChooseFragment.newInstance(typeId);
        reviewChooseFragment = ReviewChooseFragment.newInstance(typeId);
        mDelegate.loadMultipleRootFragment(binding.flytContainer.getId(), 0, shijiChooseFragment, reviewChooseFragment);
    }

    @Override
    public void initListener() {
        binding.llytIndex.setOnClickListener((v) -> {
            isShiji = false;
            mDelegate.showHideFragment(shijiChooseFragment, currentFragment);
            currentFragment = shijiChooseFragment;
            binding.ivIndex.setBackgroundResource(R.mipmap.icon_shiji);
            binding.ivClassify.setBackgroundResource(R.mipmap.icon_fuxi);
            binding.tvReview.setTextColor(0xffa9a9a9);
            binding.tvIndex.setTextColor(0xff9BD4FF);
        });
        binding.llytClassify.setOnClickListener((v) -> {
            MAPP.mapp.getLevelList().clear();
            mDelegate.showHideFragment(reviewChooseFragment, currentFragment);
            currentFragment = reviewChooseFragment;
            binding.ivIndex.setBackgroundResource(R.mipmap.icon_shiji_normal);
            binding.ivClassify.setBackgroundResource(R.mipmap.icon_fuxi_fouse);
            binding.tvIndex.setTextColor(0xffa9a9a9);
            binding.tvReview.setTextColor(0xff9BD4FF);
        });
    }

    public void showShijiWordListFragment(String tid) {
        isShiji = true;
            shijiWordListFragment = ShijiWordListFragment.newInstance(tid);
//        mDelegate.showHideFragment(shijiWordListFragment,currentFragment);
        mDelegate.start(shijiWordListFragment);
//        shijiChooseFragment.refreshData(tid);
        currentFragment = shijiWordListFragment;
    }

    public void showReviewWordListChooseFragment(String typeId) {
        reviewWordListChooseFragment = ReviewWordListChooseFragment.newInstance(typeId);
//        mDelegate.showHideFragment(reviewWordListChooseFragment,currentFragment);
        mDelegate.start(reviewWordListChooseFragment);
        currentFragment = reviewWordListChooseFragment;
    }

    public void showReviewWordListChooseFragment(List<WordListBean> wordListBeans) {
        reviewWordListFragment = ShijiWordListFragment.newInstance(wordListBeans);
//        mDelegate.showHideFragment(reviewWordListFragment,currentFragment);
        mDelegate.start(reviewWordListFragment);
        currentFragment = reviewWordListFragment;
    }

    public boolean isShiji = false;

    @Override
    protected void onDestroy() {
        TTSUtils.release();
        MAPP.mapp.getLevelList().clear();
        super.onDestroy();
    }
}
