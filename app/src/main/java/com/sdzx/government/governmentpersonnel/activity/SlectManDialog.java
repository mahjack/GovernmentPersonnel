package com.sdzx.government.governmentpersonnel.activity;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.adapter.SimpleTreeAdapter;
import com.sdzx.government.governmentpersonnel.bean.FileBean;
import com.zhy.tree.bean.Node;
import com.zhy.tree.bean.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: Joe
 * Date: 2017/9/19
 * Time: 8:13
 */

public class SlectManDialog {
    @Bind(R.id.ll_task_finish)
    LinearLayout linearLayoutTaskFinish;
    @Bind(R.id.tv_task_finish_title)
    TextView taskFinishTitle;
    @Bind(R.id.bt_task_finish_cancel)
    Button taskFinishCancel;
    @Bind(R.id.bt_task_finish_submit)
    Button taskFinishSubmit;
    @Bind(R.id.lv_tree)
    ListView lvTree;

    private Context mContext;
    private Dialog dialog;
    private Display display;
    private TreeListViewAdapter mAdapter;
    private List<FileBean> mDatas = new ArrayList<FileBean>();


    public SlectManDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SlectManDialog builder() {
        // 获取布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_test, null);
        // 自定义Dialog布局参数
        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        ButterKnife.bind(this, view);
        initData();
        try {
            mAdapter = new SimpleTreeAdapter<FileBean>(lvTree, mContext, mDatas, 10);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()) {
                        MyApp.bmname=node.getName();
                        MyApp.bmid=node.getId()+"";
                        Log.v("bmid",MyApp.bmid);
                        Toast.makeText(mContext, node.getName(),
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

            });

            lvTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 调整Dialog大小
        linearLayoutTaskFinish.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85), (int) (display.getHeight() * 0.85)));
        return this;
    }

    private void initData(){
        // id , pid , label , 其他属性
        for (int i = 0; i < MyApp.group_infoList.size(); i++) {
            mDatas.add(new FileBean(MyApp.group_infoList.get(i).getId(), MyApp.group_infoList.get(i).getPid(), MyApp.group_infoList.get(i).getName()));

        }

    }
    /**
     * 设置Title
     *
     * @param title
     * @return
     */
    public SlectManDialog setTitle(String title) {
        if ("".equals(title)) {
            taskFinishTitle.setText("标题");
        } else {
            taskFinishTitle.setText(title);
        }
        return this;
    }

//    public SlectManDialog setCont(String contstr) {
//        if ("".equals(cont)) {
//            cont.setText("标题");
//        } else {
//            cont.setText(contstr);
//        }
//        return this;
//    }

    /**
     * 设置不可取消
     *
     * @param cancel
     * @return
     */
    public SlectManDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public SlectManDialog setNegativeButton(String text, View.OnClickListener listener) {
        if ("".equals(text)) {
            taskFinishCancel.setText("取消");
        } else {
            taskFinishCancel.setText(text);
        }
        taskFinishCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public SlectManDialog setPositiveButton(String text, final View.OnClickListener listener) {
        if ("".equals(text)) {
            taskFinishSubmit.setText("确定");
        } else {
            taskFinishSubmit.setText(text);
        }
        taskFinishSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });

        return this;
    }


    public void show() {
//        ToolsUtils.taskFinishContent="";
        // 初始化控件
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
