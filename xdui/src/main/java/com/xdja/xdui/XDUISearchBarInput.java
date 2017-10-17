package com.xdja.xdui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by ldy on 2017/10/9.
 */

public class XDUISearchBarInput extends XDUITopBar {
    private EditText edtSearchText;
    private View leftLastView;
    private View rightLastView;

    public XDUISearchBarInput(@NonNull Context context) {
        this(context,null);
    }

    public XDUISearchBarInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,R.attr.XDUISearchBarInputStyle);
    }

    public XDUISearchBarInput(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        edtSearchText = new AppCompatEditText(context, attrs, defStyleAttr);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        edtSearchText.setLayoutParams(layoutParams);
        setCenterView(edtSearchText);
    }

    public void addClearButton() {

    }

    @Override
    public void addLeftView(View view, int viewId, LayoutParams layoutParams) {
        super.addLeftView(view, viewId, layoutParams);
        leftLastView = view;
    }

    @Override
    public void addRightView(View view, int viewId, LayoutParams layoutParams) {
        super.addRightView(view, viewId, layoutParams);
        rightLastView = view;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int leftPadding = 0;
        if (leftLastView != null) {
            leftPadding = leftLastView.getLeft() + leftLastView.getWidth();
        }
        int rightPadding = 0;
        if (rightLastView != null) {
            rightPadding = getWidth() - rightLastView.getRight();
        }
        if (leftPadding != 0 || rightPadding != 0) {
            //通过设置
            edtSearchText.setPadding(leftPadding, 0, rightPadding, 0);
        }
    }
}
