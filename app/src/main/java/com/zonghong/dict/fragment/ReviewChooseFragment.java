package com.zonghong.dict.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.R;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.adapter.ChooseReviewItemAdapter;
import com.zonghong.dict.base.BaseFragment;
import com.zonghong.dict.databinding.ActivityReviewChooseBinding;
import com.zonghong.dict.http.HttpObserver;
import com.zonghong.dict.utils.SignUtils;
import com.zonghong.dict.utils.ToastUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReviewChooseFragment extends BaseFragment<ActivityReviewChooseBinding> {

    private ChooseReviewItemAdapter chooseItemAdapter;

    private String typeId;

    private String selectIds = "";

    public static ReviewChooseFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        args.putString(MKey.TYPE_ID, typeId);
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
        binding.includeToolbar.tvTitle.setText("复习");
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        typeId = getArguments().getString(MKey.TYPE_ID);
        getData();
    }

    @Override
    public void initListener() {
        binding.tvOk.setOnClickListener((v) -> {
            if (chooseItemAdapter == null) {
                return;
            }
            selectIds = "";
            for (WordTypeBean wordTypeBean : chooseItemAdapter.getData()) {
                if (wordTypeBean.isCheck()) {
                    selectIds = selectIds + wordTypeBean.getId() + ",";
                }
            }
            getWordListData(true);
        });
        binding.tvCheckAll.setOnClickListener((v) -> {
            for (WordTypeBean wordTypeBean : chooseItemAdapter.getData()) {
                wordTypeBean.setCheck(true);
            }
            chooseItemAdapter.notifyDataSetChanged();
        });
    }


    private void getWordListData(boolean isSelect) {
//        params.put(MKey.TYPE_ID, selectIds.substring(0, selectIds.length() - 1));
//        HttpClient.Builder.getServer().word_read(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<WordListBean>>() {
//            @Override
//            public void onSuccess(BaseBean<List<WordListBean>> baseBean) {
//
//
//            }
//
//            @Override
//            public void onError(BaseBean<List<WordListBean>> baseBean) {
//                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
//                tipDialog.show();
//            }
//        });
        ((RecordWordActivity) _mActivity).showReviewWordListChooseFragment(selectIds.substring(0, selectIds.length() - 1));
    }

    private void getData() {
        params = SignUtils.getNormalParams();
        params.put(MKey.TYPE_ID, typeId);

        HttpClient.Builder.getServer().read(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<WordTypeBean>>() {
            @Override
            public void onSuccess(BaseBean<List<WordTypeBean>> baseBean) {
                if(baseBean.getData() == null || baseBean.getData().size() == 0){
                    ToastUtils.showToast("没有配置数据");
                    return;
                }
                if (chooseItemAdapter == null) {
                    chooseItemAdapter = new ChooseReviewItemAdapter(baseBean.getData());
                    binding.rvList.setAdapter(chooseItemAdapter);
                } else {
                    chooseItemAdapter.getData().clear();
                    chooseItemAdapter.notifyDataSetChanged();
                    chooseItemAdapter.addData(baseBean.getData());
                    chooseItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(BaseBean<List<WordTypeBean>> baseBean) {
                ToastUtils.showToast(baseBean.getMsg());
            }
        });
    }
}
