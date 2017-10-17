package com.xdja.xdui;

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

/**
 * Created by ldy on 2017/10/17.
 */

public class XDUIGroupListView extends QMUIGroupListView {
    public XDUIGroupListView(Context context) {
        super(context);
    }

    public XDUIGroupListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XDUIGroupListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public QMUICommonListItemView createChevronItemView(CharSequence titleText) {
        return createItemView(null,titleText,null,QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
    }
}
