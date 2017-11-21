package com.xdja.xdui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIDrawableHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.util.QMUIViewHelper;

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

        edtSearchText = new EditText(context);
        edtSearchText.setSingleLine();
        LayerDrawable bg = QMUIDrawableHelper.createItemSeparatorBg(0xff999999, Color.WHITE, QMUIDisplayHelper.dp2px(context, 1), false);
        edtSearchText.setBackgroundDrawable(bg);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(edtSearchText, layoutParams);
    }

    public void setSearchIcon() {
        if (searchIcon == null) {
            searchIcon = new AppCompatImageView(getContext());
            searchIcon.setImageResource(R.drawable.xdui_ic_search_16);
            addView(searchIcon, getIconParams(true));
        }
    }

    public void setClearButton() {
        if (clearButton == null) {
            clearButton = new QMUIAlphaImageButton(getContext());
            clearButton.setImageResource(R.drawable.xdui_ic_clear_16);
            clearButton.setBackgroundColor(Color.TRANSPARENT);
            clearButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtSearchText != null) {
                        edtSearchText.setText("");
                    }
                }
            });
            addView(clearButton, getIconParams(false));
            QMUIViewHelper.expendTouchArea(clearButton, QMUIDisplayHelper.dp2px(getContext(), 8));

            edtSearchText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (clearButton == null) {
                        return;
                    }
                    if (s.toString().isEmpty()) {
                        clearButton.setVisibility(INVISIBLE);
                    } else {
                        clearButton.setVisibility(VISIBLE);
                    }
                }
            });
            clearButton.setVisibility(INVISIBLE);
        }
    }

    @NonNull
    private LayoutParams getIconParams(boolean isLeft) {
        LayoutParams params = new LayoutParams(getIconWidth(), getIconHeight());

        if (isLeft) {
            params.gravity = Gravity.CENTER_VERTICAL;
            params.leftMargin = QMUIResHelper.getAttrDimen(getContext(), R.attr.xdui_searchbar_input_icon_margin_edge_hor);
        } else {
            params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
            params.rightMargin = QMUIResHelper.getAttrDimen(getContext(), R.attr.xdui_searchbar_input_icon_margin_edge_hor);
        }
        return params;
    }

    private int getIconHeight() {
        return QMUIDisplayHelper.dp2px(getContext(), 16);
    }

    private int getIconWidth() {
        return QMUIDisplayHelper.dp2px(getContext(), 16);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int inputHorPadding = QMUIResHelper.getAttrDimen(getContext(), R.attr.xdui_searchbar_input_margin_horizontal);
        int leftPadding = inputHorPadding;
        if (searchIcon != null) {
            leftPadding += searchIcon.getLeft() + searchIcon.getWidth();
        }
        int rightPadding = inputHorPadding;
        if (clearButton != null) {
            rightPadding += getWidth() - clearButton.getLeft();
        }
        edtSearchText.setPadding(leftPadding, 0, rightPadding, 0);
    }
}
