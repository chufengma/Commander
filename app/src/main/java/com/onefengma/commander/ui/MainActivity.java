package com.onefengma.commander.ui;

import android.os.Bundle;
import android.view.View;

import com.onefengma.commander.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private View groupView;
    private View createGroupView;
    private View messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        groupView = findViewById(R.id.group);
        createGroupView = findViewById(R.id.create_group);
        messageView = findViewById(R.id.system_message);

        groupView.setOnClickListener(this);
        createGroupView.setOnClickListener(this);
        messageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.group:
                GroupActivity.startFrom(this);
                break;
            case R.id.create_group:
                CreateGroupActivity.startFrom(this);
                break;
            case R.id.system_message:
                break;
        }
    }
}
