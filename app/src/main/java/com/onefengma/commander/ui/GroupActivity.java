package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Group;

import java.util.ArrayList;
import java.util.List;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;


public class GroupActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private PinnedHeaderListView createdGroup;

    private GroupAdapter createdGroupAdapter;

    public static void startFrom(Activity activity) {
        activity.startActivity(new Intent(activity, GroupActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        initViews();
    }

    private void initViews() {
        searchView = (SearchView) findViewById(R.id.search);
        createdGroup = (PinnedHeaderListView) findViewById(R.id.group_list);
        createdGroupAdapter = new GroupAdapter(this);
        createdGroupAdapter.setGroupLists(getGroups());
        createdGroup.setAdapter(createdGroupAdapter);

        createdGroup.setOnItemClickListener(new OnItemClickListener());

        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.group_search));
        searchView.post(new Runnable() {
            @Override
            public void run() {
                searchView.clearFocus();
            }
        });

    }

    private List<Group>[] getGroups() {
        // FIXME mock
        List<Group> groups = new ArrayList<Group>();
        for(int i=0;i<6;i++) {
            Group g = new Group();
            g.setGroupName("南京夫子庙特战队：" + i);
            groups.add(g);
        }

        List<Group> groupList[] = new List[2];
        groupList[0] = groups;
        groupList[1] = groups;
        return groupList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create) {
            CreateGroupActivity.startFrom(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    class OnItemClickListener extends PinnedHeaderListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
            ChatActivity.startFrom(GroupActivity.this, createdGroupAdapter.getItem(section, position));
        }

        @Override
        public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {}
    }

}
