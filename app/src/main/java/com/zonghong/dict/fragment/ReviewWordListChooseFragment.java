package com.zonghong.dict.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;


import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.WordDetailBean;
import com.waw.hr.mutils.bean.WordListBaseBean;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.adapter.BookWordAdapter;
import com.zonghong.dict.base.BaseFragment;
import com.zonghong.dict.databinding.FragmentReviewWordListChooseBinding;
import com.zonghong.dict.http.HttpObserver;
import com.zonghong.dict.utils.SignUtils;
import com.zonghong.dict.view.AddBookDialog;
import com.zonghong.dict.view.WordDetailDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReviewWordListChooseFragment extends BaseFragment<FragmentReviewWordListChooseBinding> {

    private BookWordAdapter wordAdapter;

    private AddBookDialog addBookDialog;

    private String typeId;

    private WordDetailDialog wordDetailDialog;

    private List<WordListBean> wordListBeans = new ArrayList<>();


    public static ReviewWordListChooseFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        args.putString(MKey.TYPE_ID, typeId);
        ReviewWordListChooseFragment fragment = new ReviewWordListChooseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_review_word_list_choose;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("识记");
        binding.rvList.setLayoutManager(new GridLayoutManager(_mActivity, 2));
    }

    @Override
    public void initData() {
        page = 1;
        typeId = getArguments().getString(MKey.TYPE_ID);
        getData();
    }


    @Override
    public void initListener() {
//        binding.tvLeft.setOnClickListener((v) -> {
//            if (page == 1) {
//                ToastUtils.showToast("已经是第一页了");
//                return;
//            }
//            page = page - 1;
//            getData();
//        });
//        binding.tvRight.setOnClickListener((v) -> {
////            if (page == 1) {
////                ToastUtils.showToast("已经是第一页了");
////                return;
////            }
//            page = page + 1;
//            getData();
//        });

        binding.tvCheckAll.setOnClickListener((v) -> {
            for (WordListBean wordListBean : wordAdapter.getData()) {
                wordListBean.setCheck(true);
            }
            wordAdapter.notifyDataSetChanged();
        });

        binding.tvOk.setOnClickListener((v) -> {
            if (wordAdapter == null) {
                return;
            }
            wordListBeans.clear();
            for (WordListBean wordListBean : wordAdapter.getData()) {
                if (wordListBean.isCheck()) {
                    wordListBeans.add(wordListBean);
                }
            }
            ((RecordWordActivity) _mActivity).showReviewWordListChooseFragment(wordListBeans);
        });
    }

    private void getData() {
        params = SignUtils.getNormalParams();
        params.put(MKey.TYPE_ID, typeId);
        params.put(MKey.NUM, 999999);
        params.put(MKey.PAGE, page);
        HttpClient.Builder.getServer().word_read(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<WordListBaseBean>() {
            @Override
            public void onSuccess(BaseBean<WordListBaseBean> baseBean) {
                if (wordAdapter == null) {
                    wordAdapter = new BookWordAdapter(baseBean.getData().getList());
                    binding.rvList.setAdapter(wordAdapter);
                } else {
                    wordAdapter.getData().clear();
                    wordAdapter.notifyDataSetChanged();
                    wordAdapter.addData(baseBean.getData().getList());
                    wordAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(BaseBean<WordListBaseBean> baseBean) {
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }


    private void getDetailData(String wordId) {
        params = SignUtils.getNormalParams();
        params.put(MKey.WORD_ID, wordId);
        HttpClient.Builder.getServer().vocab_read(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<WordDetailBean>() {
            @Override
            public void onSuccess(BaseBean<WordDetailBean> baseBean) {
//                if (wordDetailDialog == null) {
                wordDetailDialog = WordDetailDialog.newInstance(baseBean.getData());
//                } else {
//                    wordDetailDialog.setWordDetailBean(baseBean.getData());
//                }
                wordDetailDialog.show(getFragmentManager(), "");
            }

            @Override
            public void onError(BaseBean<WordDetailBean> baseBean) {
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    public void showDetailView(int id) {
        getDetailData(String.valueOf(id));

    }

//    public void showAddView() {
//        if (addBookDialog == null) {
//            addBookDialog = AddBookDialog.newInstance();
//        }
//        addBookDialog.show(getFragmentManager(), "");
//
//    }
}
