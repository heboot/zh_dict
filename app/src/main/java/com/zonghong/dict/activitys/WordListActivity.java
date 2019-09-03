package com.zonghong.dict.activitys;

import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityWordListBinding;
import com.zonghong.dict.databinding.ActivityWordListContainerBinding;
import com.zonghong.dict.fragment.ShijiWordListFragment;

import java.util.List;

public class WordListActivity extends BaseActivity<ActivityWordListContainerBinding> {

    private List<WordListBean> wordListBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_list_container;
    }

    @Override
    public void initUI() {
//        setBackVisibility(View.VISIBLE);
//        binding.includeToolbar.tvTitle.setText("识记");
//        binding.rvList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        wordListBeans = (List<WordListBean>) getIntent().getExtras().get(MKey.DATA) == null ? null : (List<WordListBean>) getIntent().getExtras().get(MKey.DATA);
        if (wordListBeans == null) {
            finish();
        }
        getSupportDelegate().loadRootFragment(binding.container.getId(), ShijiWordListFragment.newInstance(wordListBeans));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onBackPressedSupport() {
        finish();
    }
}
