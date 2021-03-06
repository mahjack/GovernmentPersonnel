package com.sdzx.government.governmentpersonnel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.activity.MyApp;
import com.sdzx.government.governmentpersonnel.activity.SlectManDialog;
import com.sdzx.government.governmentpersonnel.bean.FileBean;
import com.sdzx.government.governmentpersonnel.fragment.DaFragment;
import com.zhy.tree.bean.Node;
import com.zhy.tree.bean.TreeListViewAdapter;

import java.util.List;

public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {
    List<FileBean> datas;
    Context context;
    public SimpleTreeAdapter(ListView mTree, Context context, List<FileBean> datas,
                             int defaultExpandLevel) throws IllegalArgumentException,
            IllegalAccessException {
        super(mTree, context, (List<T>) datas, defaultExpandLevel);
        this.datas = datas;
        this.context = context;
    }

    @Override
    public View getConvertView(final Node node, final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView
                    .findViewById(R.id.id_treenode_icon);
            viewHolder.label = (TextView) convertView
                    .findViewById(R.id.id_treenode_label);
            viewHolder.xuanz = (TextView) convertView
                    .findViewById(R.id.tv_xz);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (node.getIcon() == -1) {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }
        viewHolder.label.setText(node.getName());
        viewHolder.xuanz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.bmname=node.getName();
                MyApp.bmid=node.getId()+"";
                DaFragment.spinner_bm.setText( MyApp.bmname);
                Log.v("bmid",MyApp.bmid);
                SlectManDialog.dialog.dismiss();

            }
        });
        return convertView;
    }

    private final class ViewHolder {
        ImageView icon;
        TextView label;
        TextView xuanz;
    }

}
