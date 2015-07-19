package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Member;
import java.util.ArrayList;
import java.util.List;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

public class CreateGroupActivity extends BaseActivity implements MemberListAdapter.OnCheckedStatusChangeListener{

    private PinnedHeaderListView memberList;
    private MemberListAdapter memberListAdapter;

    private MenuItem conformMenu;

    private SearchView searchView;

    public static void startFrom(Activity activity) {
        activity.startActivity(new Intent(activity, CreateGroupActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        memberList = (PinnedHeaderListView) findViewById(R.id.member_list);
        memberListAdapter = new MemberListAdapter(this);
        memberListAdapter.setMembers(getMemebers());
        memberList.setAdapter(memberListAdapter);

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setQueryHint(getString(R.string.search_member));
        searchView.onActionViewExpanded();

        memberListAdapter.setOnCheckedStatusChangeListener(this);
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
