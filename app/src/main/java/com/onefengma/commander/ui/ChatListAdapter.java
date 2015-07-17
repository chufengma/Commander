package com.onefengma.commander.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.onefengma.commander.model.BaseChatMessage;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {

    private List<BaseChatMessage> messageLists;
    private ChatMessageBuilderFactory factory;
    private Context context;

    public ChatListAdapter(Context context) {
        this.context = context;
        factory = new ChatMessageBuilderFactory(context);
    }

    public void setChatMessages(List<BaseChatMessage> lists) {
        this.messageLists = lists;
        notifyDataSetChanged();
    }

    public void addChatMessage(BaseChatMessage message) {
        messageLists.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messageLists.size();
    }

    @Override
    public BaseChatMessage getItem(int position) {
        return messageLists.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IChatMessageBuilder builder = factory.getMessageBuilder(getItemViewType(position));
        return builder.getView(getItem(position), convertView, parent);
    }

}
