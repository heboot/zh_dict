package com.zonghong.dict.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.StringUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.WordDetailBean;
import com.waw.hr.mutils.bean.WordListBaseBean;
import com.waw.hr.mutils.bean.WordListBean;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.R;
import com.zonghong.dict.adapter.ChooseLevelAdapter;
import com.zonghong.dict.adapter.WordAdapter;
import com.zonghong.dict.base.BaseFragment;
import com.zonghong.dict.databinding.ActivityChooseBinding;
import com.zonghong.dict.databinding.ActivityWordListBinding;
import com.zonghong.dict.http.HttpObserver;
import com.zonghong.dict.utils.SignUtils;
import com.zonghong.dict.utils.ToastUtils;
import com.zonghong.dict.view.AddBookDialog;
import com.zonghong.dict.view.WordDetailDialog;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShijiWordListFragment extends BaseFragment<ActivityWordListBinding> {

    private WordAdapter wordAdapter;

    private AddBookDialog addBookDialog;

    private String typeId;

    private WordDetailDialog wordDetailDialog;

    private List<WordListBean> wordListBeans;


    public static ShijiWordListFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        args.putString(MKey.TYPE_ID, typeId);
        ShijiWordListFragment fragment = new ShijiWordListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ShijiWordListFragment newInstance(List<WordListBean> wordListBeans) {
        Bundle args = new Bundle();
        args.putSerializable(MKey.DATA, (Serializable) wordListBeans);
        ShijiWordListFragment fragment = new ShijiWordListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        page = 1;
        if (StringUtils.isEmpty(typeId)) {
            pageDatas();
        } else {
            getData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_list;
    }

    @Override
    public void initUI() {
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        page = 1;
        typeId = getArguments().getString(MKey.TYPE_ID);
        if (StringUtils.isEmpty(typeId)) {
            binding.includeToolbar.tvTitle.setText("复习");
            wordListBeans = (List<WordListBean>) getArguments().getSerializable(MKey.DATA);
            pageDatas();
        } else {
            binding.includeToolbar.tvTitle.setText("识记");
            getData();
        }
    }

    private void pageDatas() {
        List<WordListBean> datas = new ArrayList<>();
        if (page == 1) {
            if (wordListBeans.size() > 8) {
                datas = wordListBeans.subList(page == 1 ? 0 : page, 8);
            } else {
                datas = wordListBeans.subList(page == 1 ? 0 : page, wordListBeans.size());
            }
        } else if (wordListBeans.size() < page * 8) {
            datas = wordListBeans.subList((page - 1) * 8, wordListBeans.size());
        } else {
            datas = wordListBeans.subList((page - 1) * 8, page * 8);
        }


        if (wordAdapter == null) {
            wordAdapter = new WordAdapter(datas, new WeakReference(ShijiWordListFragment.this));
            binding.rvList.setAdapter(wordAdapter);
        } else {
//            wordAdapter.getData().clear();
//            wordAdapter.notifyDataSetChanged();
            wordAdapter.setNewData(datas);
            wordAdapter.notifyDataSetChanged();
        }

        total = wordListBeans.size() / 8;
        if (wordListBeans.size() % 8 != 0) {
            total++;
        }
        binding.tvPageTip.setText("第" + page + "页/共" + total + "页");
    }

    @Override
    public boolean onBackPressedSupport() {
        _mActivity.finish();
        return super.onBackPressedSupport();
    }

    @Override
    public void initListener() {
        binding.tvLeft.setOnClickListener((v) -> {
            if (page == 1) {
                ToastUtils.showToast("已经是第一页了");
                return;
            }
            page = page - 1;
            if (StringUtils.isEmpty(typeId)) {
                pageDatas();
            } else {
                getData();
            }

        });
        binding.tvRight.setOnClickListener((v) -> {
            if (page >= total) {
                ToastUtils.showToast("已经是最后一页了");
                return;
            }
            page = page + 1;
            if (StringUtils.isEmpty(typeId)) {
                pageDatas();
            } else {
                getData();
            }
        });
        binding.includeToolbar.vBack.setOnClickListener((v) -> {
            _mActivity.finish();
        });
    }

    private void getData() {
        params = SignUtils.getNormalParams();
        params.put(MKey.TYPE_ID, typeId);
        params.put(MKey.NUM, 8);
        params.put(MKey.PAGE, page);
        HttpClient.Builder.getServer().word_read(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<WordListBaseBean>() {
            @Override
            public void onSuccess(BaseBean<WordListBaseBean> baseBean) {
                total = baseBean.getData().getNum();
                binding.tvPageTip.setText("第" + page + "页/共" + total + "页");
                if (wordAdapter == null) {
                    wordAdapter = new WordAdapter(baseBean.getData().getList(), new WeakReference(ShijiWordListFragment.this));
                    binding.rvList.setAdapter(wordAdapter);
                } else {
                    wordAdapter.getData().clear();
//                    wordAdapter.notifyDataSetChanged();
                    wordAdapter.setNewData(baseBean.getData().getList());
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

    public void showAddView(WordListBean wordListBean) {
        addBookDialog = AddBookDialog.newInstance(wordListBean);
        addBookDialog.show(getFragmentManager(), "");

    }

    @Override
    public void onDestroyView() {
        _mActivity.finish();
        super.onDestroyView();
    }
}
