package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.onefengma.commander.R;
import com.onefengma.commander.model.Group;

import java.lang.reflect.AccessibleObject;

public class ChangeGroupNameActivity extends BaseActivity implements TextWatcher {

    private static final String EXTRA_GROUP = "group";

    private EditText editText;
    private MenuItem saveMenuItem;

    private Group group;

    public static void startFrom(Activity activity, Group group) {
        Intent intent = new Intent(activity, ChangeGroupNameActivity.class);
        intent.putExtra(EXTRA_GROUP, group);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_group_name);
        group = (Group) getIntent().getSerializableExtra(EXTRA_GROUP);

        editText = (EditText) findViewById(R.id.group_name);
        editText.addTextChangedListener(this);
        editText.setText(group.getGroupName());
        editText.setSelection(group.getGroupName().length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change_group_name, menu);
        saveMenuItem = menu.findItem(R.id.action_save);
        updateSaveMenuItem();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        updateSaveMenuItem();
    }

    private void updateSaveMenuItem() {
        if (saveMenuItem == null) {
            return;
        }
        saveMenuItem.setEnabled(editText.getText().length() != 0);
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
