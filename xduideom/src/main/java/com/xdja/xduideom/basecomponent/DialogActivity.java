package com.xdja.xduideom.basecomponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.xdja.xdui.XDUIGroupListView;
import com.xdja.xdui.XDUITopBar;
import com.xdja.xduideom.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    XDUITopBar topbar;
    @BindView(R.id.groupList_dialog)
    XDUIGroupListView groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        topbar.setTitle("对话框");
        topbar.addLeftBackImageButton()
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        QMUIGroupListView.newSection(this)
                .setTitle("告警对话框")
                .addItemView(groupList.createChevronItemView("不带标题的告警对话框"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addItemView(groupList.createChevronItemView("带标题的告警对话框"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessageNegativeDialog();
                    }
                })
                .addTo(groupList);
        QMUIGroupListView.newSection(this)
                .setTitle("确认对话框")
                .addItemView(groupList.createChevronItemView("不带标题的确认对话框"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessagePositiveDialog();
                    }
                })
                .addItemView(groupList.createChevronItemView("带标题的确认对话框"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addTo(groupList);
        QMUIGroupListView.newSection(this)
                .setTitle("带输入框的对话框")
                .addItemView(groupList.createChevronItemView("不带标题的对话框"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addItemView(groupList.createChevronItemView("带标题的对话框"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .addTo(groupList);
    }

    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("确定要发送吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        Toast.makeText(DialogActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showMessageNegativeDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("确定要删除吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        Toast.makeText(DialogActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
