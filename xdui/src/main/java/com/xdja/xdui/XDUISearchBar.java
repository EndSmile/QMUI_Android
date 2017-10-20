package com.xdja.xdui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * Created by ldy on 2017/9/29.
 */

public class XDUISearchBar extends XDUITopBar {

    private XDUISearchBarInput searchBarInput;

    public XDUISearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XDUISearchBar(Context context) {
        super(context);
        init(context);
    }

    public XDUISearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        setTitleGravity(Gravity.LEFT);
        searchBarInput = new XDUISearchBarInput(context);
        searchBarInput.setClearButton();
        searchBarInput.setSearchIcon();

        makeSureTitleContainerView().addView(searchBarInput
                , new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , QMUIDisplayHelper.dp2px(getContext(), 38)));
//                        , ViewGroup.LayoutParams.WRAP_CONTENT));

    }

}
