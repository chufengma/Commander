package com.onefengma.commander.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.onefengma.commander.R;
import com.onefengma.commander.model.BaseChatMessage;
import com.onefengma.commander.model.TextChatMessage;
import com.onefengma.commander.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends BaseActivity implements View.OnClickListener{

    private static String TAG = "----" + ChatActivity.class;

    private InputMethodManager inputMethodManager;

    private ListView chatListView;
    private ChatListAdapter chatListAdapter;
    private View addView;
    private EditText editView;

    private Runnable scrollToBottomRunnable = new Runnable() {
        @Override
        public void run() {
            chatListView.smoothScrollToPosition(chatListAdapter.getCount());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initActionBar();
        initViews();
    }

    public void initActionBar() {
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("南京海关指挥组（21人）");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initViews() {
        chatListView = (ListView) findViewById(R.id.chat_list);
        chatListAdapter = new ChatListAdapter(this);
        chatListAdapter.setChatMessages(getChatMessages());
        chatListView.setAdapter(chatListAdapter);

        chatListView.post(new Runnable() {
            @Override
            public void run() {
                chatListView.setSelection(chatListAdapter.getCount());
            }
        });

        addView = findViewById(R.id.add);
        addView.setOnClickListener(this);

        editView = (EditText) findViewById(R.id.edit);
        editView.setOnClickListener(this);

        editView.requestFocus();
        editView.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputUtils.hideSoftKeyboard(inputMethodManager, editView.getWindowToken());
            }
        }, 100);
    }

    public List<BaseChatMessage> getChatMessages() {
        List<BaseChatMessage> messages = new ArrayList<BaseChatMessage>();
        for(int i = 0 ; i < 110 ;i++) {
            TextChatMessage textChatMessage = new TextChatMessage();
            textChatMessage.setMessage("哈哈哈哈苏德发射点发：" + i);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Log.i(TAG,"add Button clicked");
                onAddClick();
                break;
            case R.id.edit:
                Log.i(TAG,"edit clicked");
                editView.postDelayed(scrollToBottomRunnable, 150);
                break;
        }
    }

    private void onAddClick() {
        addView.postDelayed(scrollToBottomRunnable, 150);
    }
}
