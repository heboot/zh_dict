package com.zonghong.dict.activitys;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityRecordIndexBinding;
import com.zonghong.dict.fragment.ReviewChooseFragment;
import com.zonghong.dict.fragment.ShijiChooseFragment;
import com.zonghong.dict.fragment.ShijiWordListFragment;

import java.util.List;

import me.yokeyword.fragmentation.ISupportFragment;

public class RecordWordActivity extends BaseActivity<ActivityRecordIndexBinding> {

    private ShijiChooseFragment shijiChooseFragment;

    private ReviewChooseFragment reviewChooseFragment;

    private ShijiWordListFragment shijiWordListFragment;

    private ISupportFragment currentFragment;

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
            mDelegate.showHideFragment(shijiChooseFragment, currentFragment);
            currentFragment = shijiChooseFragment;
            binding.ivIndex.setBackgroundResource(R.mipmap.icon_shiji);
            binding.ivClassify.setBackgroundResource(R.mipmap.icon_fuxi);
        });
        binding.llytClassify.setOnClickListener((v) -> {
            mDelegate.showHideFragment(reviewChooseFragment, currentFragment);
            currentFragment = reviewChooseFragment;
            binding.ivIndex.setBackgroundResource(R.mipmap.icon_shiji_normal);
            binding.ivClassify.setBackgroundResource(R.mipmap.icon_fuxi_fouse);
        });
    }

    public void showShijiWordListFragment() {
        shijiWordListFragment = ShijiWordListFragment.newInstance(typeId);
        mDelegate.start(shijiWordListFragment);
        currentFragment = shijiWordListFragment;
    }

    public void showReviewWordListFragment(List<WordListBean> wordListBeanList) {

    }


}
