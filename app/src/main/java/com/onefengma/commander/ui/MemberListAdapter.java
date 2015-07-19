package com.onefengma.commander.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Member;

import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

public class MemberListAdapter extends SectionedBaseAdapter implements CompoundButton.OnCheckedChangeListener {

    private List<Member> members;
    private LayoutInflater inflater;
    private List<Member> checkedMembers;

    private OnCheckedStatusChangeListener onCheckedStatusChangeListener;

    public interface OnCheckedStatusChangeListener {
        void onCheckStatusChange(List<Member> members);
    }

    public MemberListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        checkedMembers = new ArrayList<>();
    }

    public void setOnCheckedStatusChangeListener(OnCheckedStatusChangeListener onCheckedStatusChangeListener) {
        this.onCheckedStatusChangeListener = onCheckedStatusChangeListener;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
        notifyDataSetChanged();
    }

    @Override
    public Member getItem(int section, int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return 1;
    }

    @Override
    public int getCountForSection(int section) {
        return members.size();
    }

    public List<Member> getCheckedMembers() {
        return checkedMembers;
    }

    @Override
    public View getItemView(int section, int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.member_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
            holder.checkBox.setOnCheckedChangeListener(this);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Member member = getItem(section, position);
        holder.checkBox.setTag(member);
        holder.nameView.setText(member.getMemberName());
        holder.checkBox.setChecked(checkedMembers.contains(member));
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Member member = (Member) compoundButton.getTag();
        if (checkedMembersContains(member) && !b) {
            checkedMembers.remove(member);
        } else if(b && !checkedMembersContains(member)) {
            checkedMembers.add(member);
        }
        if (onCheckedStatusChangeListener != null) {
            onCheckedStatusChangeListener.onCheckStatusChange(checkedMembers);
        }
    }

    private boolean checkedMembersContains(Member member) {
        for (Member m : checkedMembers) {
            if (m.getId().equals(member.getId())) {
                return true;
            }
        }
        return false;
    }

    public static class ViewHolder {
        ImageView avatorView;
        TextView nameView;
        View lineView;
        CheckBox checkBox;

        ViewHolder(View view) {
            avatorView = (ImageView) view.findViewById(R.id.avator);
            nameView = (TextView) view.findViewById(R.id.name);
            lineView = view.findViewById(R.id.line);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
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
        String title = "成员人";
        titleView.setText(title);
        return convertView;
    }
}
