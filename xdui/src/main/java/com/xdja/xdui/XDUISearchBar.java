package com.xdja.xdui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * Created by ldy on 2017/9/29.
 *
 * 拓展自{@link XDUITopBar}，相当于将其标题替换为搜索输入区域，所有和标题相关的设置操作均会报出异常
 */

public class XDUISearchBar extends XDUITopBar {

    private final XDUISearchBarInput searchBarInput;

    public XDUISearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.QMUITopBarStyle);
    }

    public XDUISearchBar(Context context) {
        this(context, null);
    }

    public XDUISearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        super.setTitleGravity(Gravity.LEFT);

        searchBarInput = new XDUISearchBarInput(context);
        searchBarInput.setClearButton();
        searchBarInput.setSearchIcon();

        makeSureTitleContainerView().addView(searchBarInput
                , new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , QMUIDisplayHelper.dp2px(getContext(), 36)));
//                        , ViewGroup.LayoutParams.WRAP_CONTENT));    }


    }

    @Override
    public TextView setTitle(int resId) {
        throw new UnsupportedOperationException("不支持此操作");
    }

    @Override
    public TextView setTitle(String title) {
        throw new UnsupportedOperationException("不支持此操作");
    }

    @Override
    public TextView setEmojiTitle(String title) {
        throw new UnsupportedOperationException("不支持此操作");
    }

    @Override
    public void showTitleView(boolean toShow) {
        throw new UnsupportedOperationException("不支持此操作");
    }

    @Override
    public void setSubTitle(String subTitle) {
        throw new UnsupportedOperationException("不支持此操作");
    }

    @Override
    public void setSubTitle(int resId) {
        throw new UnsupportedOperationException("不支持此操作");
    }

    @Override
    public void setTitleGravity(int gravity) {
        throw new UnsupportedOperationException("不支持此操作");
    }
}
