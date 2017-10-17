package com.xdja.xdui;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.List;

/**
 * Created by ldy on 2017/9/28.
 */

public class XDUITopBar extends QMUITopBar {
    public XDUITopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public XDUITopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XDUITopBar(Context context) {
        super(context);
    }

    public QMUIAlphaImageButton addRightMoreImageButton(final List<MenuItem> menuItems, final MenuItemClickListener onItemClickListener) {
        final QMUIAlphaImageButton imageButton = addRightImageButton(R.drawable.xdui_ic_more, R.id.xdui_topbar_item_right_more);
        MoreMenuAdapter adapter = new MoreMenuAdapter(getContext(), R.layout.xdui_simple_list_item, menuItems);
        final QMUIListPopup listPopup = new QMUIListPopup(getContext(), QMUIPopup.DIRECTION_BOTTOM, adapter);
        listPopup.create(QMUIDisplayHelper.dp2px(getContext(), 160), Integer.MAX_VALUE >> 2, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemClickListener.onItemClick(listPopup, parent, view, position, id);
            }
        });

        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopup.show(imageButton);
            }
        });
        return imageButton;
    }

    public static class MenuItem {
        public final @DrawableRes
        int icon;
        public final CharSequence text;


        public MenuItem(int icon, CharSequence text) {
            this.icon = icon;
            this.text = text;
        }

        @Override
        public String toString() {
            return text.toString();
        }
    }

    public interface MenuItemClickListener {
        void onItemClick(QMUIListPopup qmuiListPopup, AdapterView<?> parent, View view, int position, long id);
    }

    private static class MoreMenuAdapter extends ArrayAdapter<MenuItem> {

        private List<MenuItem> menuItems;

        MoreMenuAdapter(@NonNull Context context, int resource, @NonNull List<MenuItem> menuItems) {
            super(context, resource, menuItems);
            this.menuItems = menuItems;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            VectorDrawableCompat drawableCompat = VectorDrawableCompat.create(getContext().getResources(), menuItems.get(position).icon, getContext().getTheme());
            if (drawableCompat == null) {
                return view;
            }
            drawableCompat.setBounds(0, 0, drawableCompat.getMinimumWidth(), drawableCompat.getMinimumHeight());
            view.setCompoundDrawables(drawableCompat, null, null, null);
            return view;
        }

    }

}
