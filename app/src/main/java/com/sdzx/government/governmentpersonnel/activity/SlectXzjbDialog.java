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
import com.sdzx.government.governmentpersonnel.adapter.SimpleTreeAdapter1;
import com.sdzx.government.governmentpersonnel.bean.FileBean;
import com.sdzx.government.governmentpersonnel.fragment.DaFragment;
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

public class SlectXzjbDialog {
    @Bind(R.id.ll_task_finish)
    LinearLayout linearLayoutTaskFinish;
    @Bind(R.id.tv_task_finish_title)
    TextView taskFinishTitle;
    @Bind(R.id.jbbx)
    TextView jbbx;
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


    public SlectXzjbDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public SlectXzjbDialog builder() {
        // 获取布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_test, null);
        // 自定义Dialog布局参数
        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        ButterKnife.bind(this, view);
        initData();
        jbbx.setVisibility(View.VISIBLE);
        jbbx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.jbname="级别不限";
                MyApp.jbid="0";
                DaFragment.spinner_jb.setText(MyApp.jbname);
                dialog.dismiss();
            }
        });
        try {
            mAdapter = new SimpleTreeAdapter1(lvTree, mContext, mDatas, 1);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf()) {
                        MyApp.jbname=node.getName();
                        MyApp.jbid=node.getId()+"";
                        DaFragment.spinner_jb.setText( MyApp.jbname);
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
        mDatas.add(new FileBean(0,0,"级别不限"));

        mDatas.add(new FileBean(1,0,"管理岗位"));
        mDatas.add(new FileBean(5,1,"厅局级正职"));
        mDatas.add(new FileBean(6,1,"厅局级副职"));
        mDatas.add(new FileBean(7,1,"县处级正职"));
        mDatas.add(new FileBean(8,1,"县处级副职"));
        mDatas.add(new FileBean(9,1,"乡科级正职"));
        mDatas.add(new FileBean(10,1,"乡科级副职"));
        mDatas.add(new FileBean(11,1,"巡视员"));
        mDatas.add(new FileBean(12,1,"副巡视员"));
        mDatas.add(new FileBean(13,1,"调研员"));
        mDatas.add(new FileBean(14,1,"副调研员"));
        mDatas.add(new FileBean(15,1,"主任科员"));
        mDatas.add(new FileBean(16,1,"副主任科员"));
        mDatas.add(new FileBean(17,1,"科员"));
        mDatas.add(new FileBean(18,1,"办事员"));
        mDatas.add(new FileBean(19,1,"试用期人员"));

        mDatas.add(new FileBean(2,0,"专业技术岗位"));
        mDatas.add(new FileBean(21,2,"专业一级（正高级）"));
        mDatas.add(new FileBean(22,2,"专业二级（正高级）"));
        mDatas.add(new FileBean(23,2,"专业三级（正高级）"));
        mDatas.add(new FileBean(24,2,"专业四级（正高级）"));
        mDatas.add(new FileBean(25,2,"专业五级（副高级）"));
        mDatas.add(new FileBean(26,2,"专业六级（副高级）"));
        mDatas.add(new FileBean(27,2,"专业七级（副高级）"));
        mDatas.add(new FileBean(28,2,"专业八级（中级）"));
        mDatas.add(new FileBean(29,2,"专业九级（中级）"));
        mDatas.add(new FileBean(30,2,"专业十级（中级）"));
        mDatas.add(new FileBean(31,2,"专业十一级（初级）"));
        mDatas.add(new FileBean(32,2,"专业十二级（初级）"));
        mDatas.add(new FileBean(33,2,"专业十三级（初级）"));
        mDatas.add(new FileBean(34,2,"试用期人员"));

        mDatas.add(new FileBean(3,0,"工勤技能岗"));
        mDatas.add(new FileBean(41,3,"技术一级（高级技师）"));
        mDatas.add(new FileBean(41,3,"技术二级（技师）"));
        mDatas.add(new FileBean(41,3,"技术三级（高级工）"));
        mDatas.add(new FileBean(41,3,"技术四级（中级工）"));
        mDatas.add(new FileBean(41,3,"技术五级（初级工）"));
        mDatas.add(new FileBean(41,3,"普通岗位"));

        mDatas.add(new FileBean(4,0,"事业单位管理级别"));
        mDatas.add(new FileBean(51,4,"一级职员"));
        mDatas.add(new FileBean(52,4,"二级职员"));
        mDatas.add(new FileBean(53,4,"三级职员"));
        mDatas.add(new FileBean(54,4,"四级职员"));
        mDatas.add(new FileBean(55,4,"五级职员"));
        mDatas.add(new FileBean(56,4,"六级职员"));
        mDatas.add(new FileBean(57,4,"七级职员"));
        mDatas.add(new FileBean(58,4,"八级职员"));
        mDatas.add(new FileBean(59,4,"九级职员"));
        mDatas.add(new FileBean(60,4,"十级职员"));
        mDatas.add(new FileBean(61,4,"试用期人员"));
        mDatas.add(new FileBean(62,4,"其他"));

    }
    /**
     * 设置Title
     *
     * @param title
     * @return
     */
    public SlectXzjbDialog setTitle(String title) {
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
    public SlectXzjbDialog setCancelable(boolean cancel) {
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
    public SlectXzjbDialog setNegativeButton(String text, View.OnClickListener listener) {
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
    public SlectXzjbDialog setPositiveButton(String text, final View.OnClickListener listener) {
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
