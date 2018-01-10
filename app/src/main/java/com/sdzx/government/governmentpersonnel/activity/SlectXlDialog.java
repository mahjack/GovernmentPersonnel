package com.sdzx.government.governmentpersonnel.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.adapter.SimpleTreeXlAdapter;
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

public class SlectXlDialog {
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
    public static Dialog dialog;
    private Display display;
    private TreeListViewAdapter mAdapter;
    private List<FileBean> mDatas = new ArrayList<FileBean>();


    public SlectXlDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SlectXlDialog builder() {
        // 获取布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_test, null);
        // 自定义Dialog布局参数
        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        ButterKnife.bind(this, view);
        initData();
        try {
            mAdapter = new SimpleTreeXlAdapter(lvTree, mContext, mDatas, 10);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
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
//        for (int i = 0; i < MyApp.group_infoList.size(); i++) {
//            mDatas.add(new FileBean(MyApp.group_infoList.get(i).getId(), MyApp.group_infoList.get(i).getPid(), MyApp.group_infoList.get(i).getName()));
//
//        }
        mDatas.add(new FileBean(0,0,"学历不限"));
        mDatas.add(new FileBean(1,0,"研究生教育"));
        mDatas.add(new FileBean(11,1,"研究生"));
        mDatas.add(new FileBean(12,1,"研究生班"));
        mDatas.add(new FileBean(13,1,"中央党校研究生"));
        mDatas.add(new FileBean(14,1,"省委党校研究生"));
        mDatas.add(new FileBean(2,0,"本科教育"));
        mDatas.add(new FileBean(21,2,"大学"));
        mDatas.add(new FileBean(22,2,"中央党校大学"));
        mDatas.add(new FileBean(23,2,"省委党校大学"));
        mDatas.add(new FileBean(3,0,"专科教育"));
        mDatas.add(new FileBean(31,3,"大专"));
        mDatas.add(new FileBean(32,3,"中央党校大专"));
        mDatas.add(new FileBean(33,3,"省委党校大专"));
        mDatas.add(new FileBean(34,3,"大学普通班"));
        mDatas.add(new FileBean(4,0,"中专"));
        mDatas.add(new FileBean(5,0,"中技"));
        mDatas.add(new FileBean(6,0,"高中"));
        mDatas.add(new FileBean(7,0,"初中"));
        mDatas.add(new FileBean(8,0,"小学"));
        mDatas.add(new FileBean(9,0,"无"));

    }
    /**
     * 设置Title
     *
     * @param title
     * @return
     */
    public SlectXlDialog setTitle(String title) {
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
    public SlectXlDialog setCancelable(boolean cancel) {
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
    public SlectXlDialog setNegativeButton(String text, View.OnClickListener listener) {
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
    public SlectXlDialog setPositiveButton(String text, final View.OnClickListener listener) {
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
