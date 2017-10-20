package com.xdja.xdui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * Created by ldy on 2017/10/9.
 */

public class XDUISearchBarInput extends FrameLayout {
    private EditText edtSearchText;
    private AppCompatImageView searchIcon;
    private QMUIAlphaImageButton clearButton;

    public XDUISearchBarInput(@NonNull Context context) {
        this(context, null);
    }

    public XDUISearchBarInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.XDUISearchBarInputStyle);
    }

    public XDUISearchBarInput(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        edtSearchText = new AppCompatEditText(context);
        edtSearchText.setSingleLine();

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        edtSearchText.setLayoutParams(layoutParams);
        addView(edtSearchText, layoutParams);
    }

    public void setSearchIcon() {
        if (searchIcon == null) {
            searchIcon = new AppCompatImageView(getContext());
            searchIcon.setImageResource(R.drawable.xdui_ic_search);
            addView(searchIcon, getIconParams(true));
        }
    }

    public void setClearButton() {
        if (clearButton == null) {
            clearButton = new QMUIAlphaImageButton(getContext());
            clearButton.setImageResource(R.drawable.xdui_ic_close);
            clearButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtSearchText != null) {
                        edtSearchText.setText("");
                    }
                }
            });
            addView(clearButton, getIconParams(false));
        }
    }

    @NonNull
    private LayoutParams getIconParams(boolean isLeft) {
        LayoutParams params = new LayoutParams(getIconWidth(), getIconHeight());

        if (!isLeft) {
            params.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        } else {
            params.gravity = Gravity.CENTER_VERTICAL;
        }
        return params;
    }

    private int getIconHeight() {
        return QMUIDisplayHelper.dp2px(getContext(), 14);
    }

    private int getIconWidth() {
        return QMUIDisplayHelper.dp2px(getContext(), 14);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int leftPadding = 0;
        if (searchIcon != null) {
            leftPadding = searchIcon.getLeft() + searchIcon.getWidth();
        }
        int rightPadding = 0;
        if (clearButton != null) {
            rightPadding = getWidth() - clearButton.getLeft();
        }
        if (leftPadding != 0 || rightPadding != 0) {
            //通过设置
            edtSearchText.setPadding(leftPadding, 0, rightPadding, 0);
        }
    }
}
