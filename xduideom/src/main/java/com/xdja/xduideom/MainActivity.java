package com.xdja.xduideom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.xdja.xdui.XDUIGroupListView;
import com.xdja.xdui.XDUITopBar;
import com.xdja.xduideom.basecomponent.DialogActivity;
import com.xdja.xduideom.basecomponent.SearchActivity;
import com.xdja.xduideom.basecomponent.TopBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    XDUITopBar topbar;
    @BindView(R.id.groupList_main)
    XDUIGroupListView groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        topbar.setTitle("信大捷安组件库");

        QMUIGroupListView.newSection(this)
                .setTitle("基础组件")
                .addItemView(groupList.createChevronItemView("标题栏"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, TopBarActivity.class));
                    }
                })
                .addItemView(groupList.createChevronItemView("搜索栏"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    }
                })
                .addItemView(groupList.createChevronItemView("标签栏"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addItemView(groupList.createChevronItemView("导航栏"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addItemView(groupList.createChevronItemView("对话框"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, DialogActivity.class));
                    }
                })
                .addItemView(groupList.createChevronItemView("Toast"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addItemView(groupList.createChevronItemView("加载指示器"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addTo(groupList);
    }
}
