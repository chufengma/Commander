package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Group;
import com.onefengma.commander.model.Member;

import java.util.ArrayList;
import java.util.List;

public class ChatSettingActivity extends BaseActivity implements ChatMemberLayout.OnMemberClickListener, View.OnClickListener{

    private static final String EXTRA_GROUP = "group";

    private ChatMemberLayout chatMemberLayout;
    private Group group;
    private View changeGroupName;

    public static void startFrom(Activity activity, Group group) {
        Intent intent = new Intent(activity, ChatSettingActivity.class);
        intent.putExtra(EXTRA_GROUP, group);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_setting);
        group = (Group) getIntent().getSerializableExtra(EXTRA_GROUP);

        chatMemberLayout = (ChatMemberLayout) findViewById(R.id.chat_member_layout);
        chatMemberLayout.addMembers(getMemebers());
        chatMemberLayout.setOnMemberClickListener(this);

        changeGroupName = findViewById(R.id.set_group_name);
        changeGroupName.setOnClickListener(this);
    }

    private List<Member> getMemebers() {
        // FIXME mock
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Member m = new Member();
            m.setId(i + "");
            m.setMemberName("张三丰" + i);
            members.add(m);
        }
        return members;
    }

    @Override
    public void onMemberClick(Member member) {
        MemberProfileActivity.startFrom(this);
    }

    @Override
    public void onAddMemberClick() {
        CreateGroupActivity.startForAddMemberFrom(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_group_name:
                ChangeGroupNameActivity.startFrom(this, group);
                break;
        }
    }
}
