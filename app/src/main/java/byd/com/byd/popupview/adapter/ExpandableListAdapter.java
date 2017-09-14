package byd.com.byd.popupview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import byd.com.byd.popupview.R;
import byd.com.byd.popupview.myinterface.NotChildGroupListener;

/**
 * Created by Administrator on 2017/9/14.
 */

public class ExpandableListAdapter  extends BaseExpandableListAdapter {
    //组列表的数据
    private List<String> groupArray;
    //子列表的数据
    private List<List<String>> childArray;
    private Context mContext;
    private NotChildGroupListener listener;

    public void setNotChildGroupListener(NotChildGroupListener listener) {
        this.listener=listener;
    }

    public ExpandableListAdapter(Context context, List<String> groupArray, List<List<String>> childArray) {
        mContext = context;
        this.groupArray = groupArray;
        this.childArray = childArray;
    }

    @Override
    public int getGroupCount() {
        return groupArray != null ? groupArray.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray != null ? childArray.get(groupPosition).size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandlist_group, null);
            holder.groupName = (TextView) convertView.findViewById(R.id.tv_group_name);
            holder.arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        //当父item没有子item是关闭

        if (groupPosition==0|| groupPosition==4 || groupPosition == 5 || groupPosition==8) {
            holder.arrow.setVisibility(View.GONE);
            holder.groupName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNotChildGroupClick(groupPosition);
                }
            });
        }
        //判断是否已经打开列表
        if (isExpanded) {
            holder.arrow.setVisibility(View.VISIBLE);
        } else {
            holder.arrow.setVisibility(View.GONE);
        }
        holder.groupName.setText(groupArray.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder = null;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandlist_item, null);
            holder.childName = (TextView) convertView.findViewById(R.id.tv_child_name);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.childName.setText(childArray.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView groupName;
        public ImageView arrow;
    }

    class ChildHolder {
        public TextView childName;
    }
}
