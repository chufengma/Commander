package com.onefengma.commander.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Member;
import com.onefengma.commander.utils.ViewUtils;

import java.util.List;

public class ChatMemberLayout extends LinearLayout implements View.OnClickListener{

    private LayoutParams memberLayoutParams;
    private OnMemberClickListener onMemberClickListener;

    public interface OnMemberClickListener {
        void onMemberClick(Member member);
        void onAddMemberClick();
    }

    public void setOnMemberClickListener(OnMemberClickListener onMemberClickListener) {
        this.onMemberClickListener = onMemberClickListener;
    }

    public ChatMemberLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    public void addMembers(List<Member> members) {
        LinearLayout rowLayout = null;
        for (int i = 0; i < members.size(); i++) {
            if (i % 4 == 0) {
                rowLayout = createRowLayout();
                addView(rowLayout);
            }
            rowLayout.addView(createMemberView(members.get(i)));
        }
        int restCount = 4 - rowLayout.getChildCount();
        if (restCount == 0) {
            rowLayout = createRowLayout();
            rowLayout.addView(createAddView());
            restCount = 3;
            addView(rowLayout);
        }

        for (int i = 0; i < restCount; i++) {
            View view = new View(getContext());
            view.setLayoutParams(getMemberLayoutParams());
            view.setVisibility(View.INVISIBLE);
            rowLayout.addView(view);
        }


    }

    private LinearLayout createRowLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(HORIZONTAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        int margin = ViewUtils.dipToPix(getResources().getDisplayMetrics(), 10);
        lp.setMargins(0, margin, 0, margin);
        linearLayout.setLayoutParams(lp);
        return linearLayout;
    }

    private ImageView createMemberView(Member member) {
        ImageView memberView = new ImageView(getContext());
        memberView.setLayoutParams(getMemberLayoutParams());
        memberView.setImageResource(R.mipmap.demo_avator_login_user);
        memberView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        memberView.setOnClickListener(this);
        memberView.setTag(member);
        return memberView;
    }

    private LayoutParams getMemberLayoutParams() {
        if (memberLayoutParams == null) {
            int lenght = ViewUtils.dipToPix(getResources().getDisplayMetrics(), 40);
            memberLayoutParams = new LayoutParams(lenght, lenght);
            memberLayoutParams.weight = 1;
            int margin = ViewUtils.dipToPix(getResources().getDisplayMetrics(), 10);
            memberLayoutParams.setMargins(margin, margin, margin, margin);
        }
        return memberLayoutParams;
    }

    private ImageView createAddView() {
        ImageView addView = new ImageView(getContext());
        addView.setLayoutParams(getMemberLayoutParams());
        addView.setImageResource(R.mipmap.ic_add);
        addView.setScaleType(ImageView.ScaleType.CENTER);
        addView.setOnClickListener(this);
        addView.setId(R.id.add_member);
        return addView;
    }

    @Override
    public void onClick(View view) {
        if (onMemberClickListener == null) {
            return;
        }
        if (view.getId() == R.id.add_member) {
            onMemberClickListener.onAddMemberClick();
        } else {
            onMemberClickListener.onMemberClick((Member)view.getTag());
        }
    }

}
