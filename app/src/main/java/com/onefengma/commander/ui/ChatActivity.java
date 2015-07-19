package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.onefengma.commander.R;
import com.onefengma.commander.model.BaseChatMessage;
import com.onefengma.commander.model.Group;
import com.onefengma.commander.model.TextChatMessage;
import com.onefengma.commander.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private static String TAG = "----" + ChatActivity.class;

    private static String EXTRA_GROUP = "extra_group";

    private ListView chatListView;
    private ChatListAdapter chatListAdapter;
    private View addView;
    private EditText editView;
    private View panelView;
    private Button sendButton;

    private Group group;

    private Runnable scrollToBottomRunnable = new Runnable() {
        @Override
        public void run() {
            chatListView.smoothScrollToPosition(chatListAdapter.getCount());
        }
    };

    private Runnable setSelectionRunnable = new Runnable() {
        @Override
        public void run() {
            chatListView.setSelection(chatListAdapter.getCount());
        }
    };

    public static void startFrom(Activity activity, Group group) {
        Intent intent = new Intent(activity, ChatActivity.class);
        intent.putExtra(EXTRA_GROUP, group);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        group = (Group) getIntent().getSerializableExtra(EXTRA_GROUP);
        initActionBar();
        initViews();
    }

    public void initActionBar() {
        // FIXME mock
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle(group.getGroupName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initViews() {
        chatListView = (ListView) findViewById(R.id.chat_list);
        chatListAdapter = new ChatListAdapter(this);
        chatListAdapter.setChatMessages(getChatMessages());
        chatListView.setAdapter(chatListAdapter);

        chatListView.post(setSelectionRunnable);

        addView = findViewById(R.id.add);
        addView.setOnClickListener(this);

        sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(this);

        editView = (EditText) findViewById(R.id.edit);
        editView.setOnClickListener(this);

        editView.setOnFocusChangeListener(this);

        panelView = findViewById(R.id.panel);
    }

    public List<BaseChatMessage> getChatMessages() {
        // FIXME mock
        List<BaseChatMessage> messages = new ArrayList<BaseChatMessage>();
        for (int i = 0; i < 54; i++) {
            TextChatMessage textChatMessage = new TextChatMessage();
            textChatMessage.setMessage("这是一条自动生成的消息：" + i);
            if (i % 5 == 0) {
                textChatMessage.setIsLoginUser(true);
                textChatMessage.setUserName("金刚狼");
            } else {
                textChatMessage.setIsLoginUser(false);
                textChatMessage.setUserName("诸葛亮");
            }
            messages.add(textChatMessage);
        }
        return messages;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            ChatSettingActivity.startFrom(this, group);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Log.i(TAG, "add Button clicked");
                onAddClick();
                break;
            case R.id.edit:
                Log.i(TAG, "edit clicked");
                onEditClick();
                break;
            case R.id.send:
                Log.i(TAG, "send clicked");
                onSendClick();
                break;
        }
    }

    private void onEditClick() {
        panelView.setVisibility(View.GONE);
        panelView.postDelayed(setSelectionRunnable, 150);
    }

    private void onAddClick() {
        updatePanle(panelView.getVisibility() == View.VISIBLE);
    }

    private void updatePanle(boolean needGone) {
        if (!needGone) {
            InputUtils.hideSoftKeyboard(panelView);
            panelView.setVisibility(View.VISIBLE);
        } else {
            InputUtils.showSoftKeyboard(panelView);
            panelView.setVisibility(View.GONE);
        }
        panelView.postDelayed(setSelectionRunnable, 150);
    }

    private void onSendClick() {
        //FIXME mock
        TextChatMessage textChatMessage = new TextChatMessage();
        textChatMessage.setIsLoginUser(true);
        textChatMessage.setUserName("金刚狼");
        textChatMessage.setMessage(editView.getText().toString());
        sendMessage(textChatMessage);

        TextChatMessage textChatMessageResp = new TextChatMessage();
        textChatMessageResp.setUserName("诸葛亮");
        textChatMessageResp.setMessage(editView.getText().toString() + ", 说得对！");
        sendMessage(textChatMessageResp);

        editView.setText("");
    }

    private void sendMessage(BaseChatMessage message) {
        chatListAdapter.addChatMessage(message);
        chatListView.post(scrollToBottomRunnable);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.edit) {
            panelView.setVisibility(View.GONE);
            editView.postDelayed(setSelectionRunnable, 150);
        }
    }
}
