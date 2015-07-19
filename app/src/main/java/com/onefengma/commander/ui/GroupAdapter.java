package com.onefengma.commander.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Group;

import java.util.List;

import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

public class GroupAdapter extends SectionedBaseAdapter {

    private List<Group> groupLists[];
    private LayoutInflater inflater;

    public GroupAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setGroupLists(List<Group> lists[]) {
        this.groupLists = lists;
        notifyDataSetChanged();
    }

    @Override
    public Group getItem(int section, int position) {
        return groupLists[section].get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return section + position;
    }

    @Override
    public int getSectionCount() {
        return groupLists.length;
    }

    @Override
    public int getCountForSection(int section) {
        return groupLists[section].size();
    }

    @Override
    public View getItemView(int section, int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.group_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.nameView.setText(getItem(section, position).getGroupName());
        // holder.lineView.setVisibility(position == getCountForSection(section) - 1 ? View.GONE : View.VISIBLE);
        return view;
    }

    public static class ViewHolder {
        ImageView avatorView;
        TextView nameView;
        View lineView;

        ViewHolder(View view) {
            avatorView = (ImageView) view.findViewById(R.id.group_avator);
            nameView = (TextView) view.findViewById(R.id.group_name);
            lineView = view.findViewById(R.id.line);
        }
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        TextView titleView;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_list_section_title, parent, false);
            titleView = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(titleView);
        } else {
            titleView = (TextView) convertView.getTag();
        }
        String title;
        if (section == 0) {
            title = "我创建的群聊";
        } else {
            title = "我加入的群聊";
        }
        titleView.setText(title);
        return convertView;
    }
}
