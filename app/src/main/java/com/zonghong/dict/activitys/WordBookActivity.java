package com.zonghong.dict.activitys;

import android.app.Dialog;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.WordDetailBean;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.adapter.BookWordAdapter;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityWordBookBinding;
import com.zonghong.dict.http.HttpObserver;
import com.zonghong.dict.utils.BookUtils;
import com.zonghong.dict.utils.IntentUtils;
import com.zonghong.dict.utils.SignUtils;
import com.zonghong.dict.view.AddBookDialog;
import com.zonghong.dict.view.DelBookDialog;
import com.zonghong.dict.view.WordDetailDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WordBookActivity extends BaseActivity<ActivityWordBookBinding> {


    private BookWordAdapter wordAdapter;

    private DelBookDialog delBookDialog;

    private WordDetailDialog wordDetailDialog;

    private List<WordListBean> wordListBeans = new ArrayList<>();

    private List<WordListBean> allWords = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_book;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("生词本");
        binding.rvList.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void initData() {
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
        binding.tvOk.setOnClickListener((v) -> {
            if (wordAdapter == null || wordAdapter.getData() == null || wordAdapter.getData().size() == 0) {
                tipDialog = DialogUtils.getInfolDialog(WordBookActivity.this, "快去添加生词吧", true);
                tipDialog.show();
                return;
            }
            wordListBeans.clear();
            for (WordListBean wordListBean : wordAdapter.getData()) {
                if (wordListBean.isCheck()) {
                    wordListBeans.add(wordListBean);
                }
            }
//            IntentUtils.intent2WordBookListActivity(wordListBeans);


            if (wordListBeans == null || wordListBeans.size() == 0) {
                tipDialog = DialogUtils.getInfolDialog(WordBookActivity.this, "请选择你要重新识记的单词", true);
                tipDialog.show();
                return;
            }

            IntentUtils.intent2WordListActivity(wordListBeans);
        });

        binding.tvCheckAll.setOnClickListener((v) -> {
            if (wordAdapter == null || wordAdapter.getData() == null || wordAdapter.getData().size() == 0) {
                tipDialog = DialogUtils.getInfolDialog(WordBookActivity.this, "快去添加生词吧", true);
                tipDialog.show();
                return;
            }
            for (WordListBean wordListBean : wordAdapter.getData()) {
                wordListBean.setCheck(true);
            }
            wordAdapter.notifyDataSetChanged();
        });

        binding.tvDel.setOnClickListener((v) -> {
            if (wordAdapter == null || wordAdapter.getData() == null || wordAdapter.getData().size() == 0) {
                tipDialog = DialogUtils.getInfolDialog(WordBookActivity.this, "快去添加生词吧", true);
                tipDialog.show();
                return;
            }
            for (WordListBean wordListBean : wordAdapter.getData()) {
                if (wordListBean.isCheck()) {
                    allWords.remove(wordListBean);
                }
            }

            if (allWords.size() == wordAdapter.getData().size()) {
                tipDialog = DialogUtils.getInfolDialog(WordBookActivity.this, "请选择你要删除的单词", true);
                tipDialog.show();
                return;
            }


            delBookDialog = DelBookDialog.newInstance(allWords);
            delBookDialog.show(getSupportFragmentManager(), "");


        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {

        List<WordListBean> wordListBeanList = BookUtils.getLocalWordlistBean();

        allWords = wordListBeanList;

        if (wordListBeanList == null) {
            return;
        }

        if (wordAdapter == null) {
            wordAdapter = new BookWordAdapter(wordListBeanList);
            binding.rvList.setAdapter(wordAdapter);
        } else {
            wordAdapter.getData().clear();
            wordAdapter.notifyDataSetChanged();
            wordAdapter.addData(wordListBeanList);
            wordAdapter.notifyDataSetChanged();
        }


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
                wordDetailDialog.show(getSupportFragmentManager(), "");
            }

            @Override
            public void onError(BaseBean<WordDetailBean> baseBean) {
                tipDialog = DialogUtils.getFailDialog(WordBookActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    public void showDetailView(int id) {
        getDetailData(String.valueOf(id));

    }


}
