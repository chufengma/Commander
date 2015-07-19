package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Member;

import java.util.ArrayList;
import java.util.List;

public class ChatSettingActivity extends BaseActivity {

    private ChatMemberLayout chatMemberLayout;

    public static void startFrom(Activity activity) {
        activity.startActivity(new Intent(activity, ChatSettingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_setting);
        chatMemberLayout = (ChatMemberLayout) findViewById(R.id.chat_member_layout);
        chatMemberLayout.addMembers(getMemebers());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
