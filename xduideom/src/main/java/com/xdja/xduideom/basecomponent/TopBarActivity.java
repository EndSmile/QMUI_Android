package com.xdja.xduideom.basecomponent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.xdja.xdui.XDUITopBar;
import com.xdja.xduideom.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopBarActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    XDUITopBar topbar;
    @BindView(R.id.topbar_button)
    XDUITopBar topbarButton;
    @BindView(R.id.topbar_title)
    XDUITopBar topbarTitle;
    boolean isLeft = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar);
        ButterKnife.bind(this);

        topbar.setTitle("标题栏");
        QMUIAlphaImageButton leftBackImageButton = topbar.addLeftBackImageButton();
        leftBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ArrayList<XDUITopBar.MenuItem> menuItems = new ArrayList<XDUITopBar.MenuItem>(3) {
            {
                add(new XDUITopBar.MenuItem(R.drawable.ic_message, "发起群聊"));
                add(new XDUITopBar.MenuItem(R.drawable.ic_contact, "添加好友"));
                add(new XDUITopBar.MenuItem(R.drawable.ic_share, "分享"));
            }
        };
        topbar.addRightMoreImageButton(menuItems, new XDUITopBar.MenuItemClickListener() {
            @Override
            public void onItemClick(QMUIListPopup qmuiListPopup, AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TopBarActivity.this, "点击了 " + menuItems.get(position), Toast.LENGTH_SHORT).show();
//                qmuiListPopup.dismiss();
                finish();
            }
        });
        topbar.addRightImageButton(R.drawable.ic_switcher_net, R.id.switcher)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isLeft) {
                            topbar.setTitleGravity(Gravity.CENTER);
                            topbarButton.setTitleGravity(Gravity.CENTER);
                            topbarTitle.setTitleGravity(Gravity.CENTER);
                        } else {
                            topbar.setTitleGravity(Gravity.LEFT);
                            topbarButton.setTitleGravity(Gravity.LEFT);
                            topbarTitle.setTitleGravity(Gravity.LEFT);
                        }
                        isLeft = !isLeft;
                    }
                });

        topbarButton.setTitle("按钮标题栏++++++++++++++超长");
        topbarButton.addRightTextButton("完成", R.id.complete)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TopBarActivity.this, "完成", Toast.LENGTH_SHORT).show();
                    }
                });

        topbarButton.addLeftTextButton("取消", R.id.cancel)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TopBarActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
        topbarButton.addLeftTextButton("关闭", R.id.close);

        topbarTitle.setTitle("标题栏++++++++++++++++++++++++++++超长");

    }
}
