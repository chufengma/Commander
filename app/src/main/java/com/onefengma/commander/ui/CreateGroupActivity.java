package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Member;
import java.util.ArrayList;
import java.util.List;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

public class CreateGroupActivity extends BaseActivity implements MemberListAdapter.OnCheckedStatusChangeListener{

    public static final String EXTRA_TYPE = "TYPE";

    public static final int TYPE_ADD_MEMBER = 1;
    public static final int TYPE_CREATE_GROUP = 2;

    private PinnedHeaderListView memberList;
    private MemberListAdapter memberListAdapter;

    private MenuItem conformMenu;
    private SearchView searchView;
    private int type;

    public static void startFrom(Activity activity) {
        Intent intent = new Intent(activity, CreateGroupActivity.class);
        intent.putExtra(EXTRA_TYPE, TYPE_CREATE_GROUP);
        activity.startActivity(intent);
    }

    public  static void startForAddMemberFrom(Activity activity) {
        Intent intent = new Intent(activity, CreateGroupActivity.class);
        intent.putExtra(EXTRA_TYPE, TYPE_ADD_MEMBER);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        type = getIntent().getIntExtra(EXTRA_TYPE, TYPE_CREATE_GROUP);

        memberList = (PinnedHeaderListView) findViewById(R.id.member_list);
        memberListAdapter = new MemberListAdapter(this);
        memberListAdapter.setMembers(getMemebers());
        memberList.setAdapter(memberListAdapter);

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setQueryHint(getString(R.string.search_member));
        searchView.onActionViewExpanded();

        memberListAdapter.setOnCheckedStatusChangeListener(this);
        searchView.post(new Runnable() {
            @Override
            public void run() {
                searchView.clearFocus();
            }
        });
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
        getMenuInflater().inflate(R.menu.menu_create_group, menu);
        conformMenu = menu.findItem(R.id.action_comform);
        updateMenuItems();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_comform) {
            if (type == TYPE_CREATE_GROUP) {
                // TODO 创建群聊
                Toast.makeText(this, "创建群聊", Toast.LENGTH_SHORT).show();
            } if (type == TYPE_ADD_MEMBER) {
                // TODO 添加成员
                Toast.makeText(this, "添加成员", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateMenuItems() {
        if (memberListAdapter == null) {
            return;
        }
        conformMenu.setEnabled(memberListAdapter.getCheckedMembers().size() > 0);
    }

    @Override
    public void onCheckStatusChange(List<Member> members) {
        updateMenuItems();
    }
}
