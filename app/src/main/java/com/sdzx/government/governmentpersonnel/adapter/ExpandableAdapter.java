package com.sdzx.government.governmentpersonnel.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Mark
 * Date：2017/10/25 0025
 * Tell：15006330640
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private List<String> GroupData=new ArrayList<>();//第一级数据
    private List<List<String>> ChildrenData;//定义组中的子数据
    private Context context;

    public ExpandableAdapter(List<String> groupData, List<List<String>> childrenData, Context context) {
        GroupData = groupData;
        ChildrenData = childrenData;
        this.context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ChildrenData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView myText = null;
        if (convertView != null) {
            myText = (TextView)convertView;
            myText.setText(ChildrenData.get(groupPosition).get(childPosition));
        } else {
            myText = createView(ChildrenData.get(groupPosition).get(childPosition));
        }
        return myText;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ChildrenData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return GroupData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return GroupData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        TextView myText = null;
        if (convertView != null) {
            myText = (TextView)convertView;
            myText.setText(GroupData.get(groupPosition));
        } else {
            myText = createView(GroupData.get(groupPosition));
        }
        return myText;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    private TextView createView(String str) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 80);
        TextView myText = new TextView(context);
        myText.setLayoutParams(layoutParams);
        myText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        myText.setPadding(80, 0, 0, 0);
        myText.setText(str);
        return myText;
    }
}

