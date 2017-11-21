package com.xdja.xdui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
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
        final QMUIListPopup listPopup = new MorePop(getContext(), QMUIPopup.DIRECTION_BOTTOM, adapter);
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

    @Override
    public Button addLeftTextButton(String buttonText, int viewId) {
        boolean leftEmpty = mLeftLastViewId == DEFAULT_VIEW_ID;
        Button leftTextButton = super.addLeftTextButton(buttonText, viewId);
        if (leftEmpty) {
            ((LayoutParams) leftTextButton.getLayoutParams()).leftMargin += QMUIResHelper.getAttrDimen(getContext(), R.attr.xdui_topbar_text_button_edge_margin_horizontal);
        }
        return leftTextButton;
    }

    @Override
    public Button addRightTextButton(String buttonText, int viewId) {
        boolean rightEmpty = mLeftLastViewId == DEFAULT_VIEW_ID;
        Button rightTextButton = super.addRightTextButton(buttonText, viewId);
        if (rightEmpty) {
            ((LayoutParams) rightTextButton.getLayoutParams()).rightMargin += QMUIResHelper.getAttrDimen(getContext(), R.attr.xdui_topbar_text_button_edge_margin_horizontal);
        }
        return rightTextButton;
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

    private static class MorePop extends QMUIListPopup {

        private final Window window;
        private float originAlpha = 1f;
        private final float shadeAlpha;
        private ObjectAnimator animator;

        /**
         * {@inheritDoc}
         */
        public MorePop(Context context, int direction, BaseAdapter adapter) {
            super(context, direction, adapter);

            shadeAlpha = QMUIResHelper.getAttrFloatValue(context, R.attr.xdui_topbar_pop_background_shade_alpha);
            if (shadeAlpha >= 0 && shadeAlpha < 1 && (context instanceof Activity)) {
                //透明度在合法范围即启用背景变化策略
                window = ((Activity) context).getWindow();
                //记录原始的的透明度
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                originAlpha = layoutParams.alpha;
            } else {
                window = null;
            }
        }

        @Override
        public void setContentView(View root) {
            super.setContentView(root);
//            mRootView.findViewById(R.id.box).setBackgroundResource(0);
        }

        @Override
        protected void onDismiss() {
            super.onDismiss();
            if (window != null) {
                showShade(originAlpha);
            }
        }

        @Override
        protected void onPreShow() {
            super.onPreShow();
            if (window != null) {
                if (animator == null || !animator.isRunning()) {
                    //如果动画此时没有运行，重新获取原始的透明度，避免影响外界的修改
                    WindowManager.LayoutParams layoutParams = window.getAttributes();
                    originAlpha = layoutParams.alpha;
                }
                showShade(shadeAlpha);
            }
        }

        private void showShade(final float endValue) {
            if (animator != null && animator.isRunning()) {
                animator.cancel();
            }
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            float startAlpha = layoutParams.alpha;

            animator = ObjectAnimator.ofFloat(this, "backgroundAlpha", startAlpha, endValue);
            animator.start();
        }

        /**
         * 供属性动画使用
         */
        void setBackgroundAlpha(float backgroundAlpha) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.alpha = backgroundAlpha;
            window.setAttributes(layoutParams);
        }
    }

}
