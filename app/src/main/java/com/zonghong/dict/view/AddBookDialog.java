package com.zonghong.dict.view;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.WordListBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutAddBookTipBinding;
import com.zonghong.dict.utils.BookUtils;

public class AddBookDialog extends DialogFragment {

    private WordListBean wordListBean;

    private LayoutAddBookTipBinding binding;

    public static AddBookDialog newInstance(WordListBean wordListBean) {
        Bundle args = new Bundle();
        args.putSerializable(MKey.DATA, wordListBean);
        AddBookDialog fragment = new AddBookDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.setWindowAnimations(R.style.BottomDialog_Animation);
        //设置边距
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(getResources().getDimensionPixelOffset(R.dimen.x275), getResources().getDimensionPixelOffset(R.dimen.y180));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_book_tip, container, false);
        binding = DataBindingUtil.bind(view);
        wordListBean = (WordListBean) getArguments().getSerializable(MKey.DATA);
        binding.qrbOk.setOnClickListener((v) -> {
            BookUtils.addWordBook(wordListBean);
            dismiss();
        });
        binding.qrbNo.setOnClickListener((v) -> {
            dismiss();
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

}
