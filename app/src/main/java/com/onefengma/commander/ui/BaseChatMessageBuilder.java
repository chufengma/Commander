package com.onefengma.commander.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.onefengma.commander.R;
import com.onefengma.commander.model.BaseChatMessage;
import com.onefengma.commander.model.TextChatMessage;

public abstract class BaseChatMessageBuilder implements IChatMessageBuilder {

    protected LayoutInflater inflater;

    public BaseChatMessageBuilder(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(BaseChatMessage chatMessage, View convertView, ViewGroup parent) {
        TextChatMessage message = (TextChatMessage) chatMessage;
        ViewHolder holder;
        System.out.println("--------:" + chatMessage.isLoginUser() + ":" + chatMessage.getUserName() + ":" + ((TextChatMessage) chatMessage).getMessage());
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.base_chat_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.display(message);
        resetViewPosition(chatMessage, holder);
        View oldChildView = holder.messageItem.getChildAt(0);
        View newChildView = getViewItem(chatMessage, oldChildView, holder.messageItem);
        if (oldChildView == null) {
            holder.messageItem.addView(newChildView);
        }
        return convertView;
    }

    private void resetViewPosition(BaseChatMessage chatMessage, ViewHolder holder) {

        LayoutParams messageLp = (LayoutParams) holder.messageItem.getLayoutParams();
        LayoutParams avatorViewLp = (LayoutParams) holder.avatorView.getLayoutParams();
        LayoutParams containerLp = (LayoutParams) holder.containerLayout.getLayoutParams();
        LayoutParams nameLp = (LayoutParams) holder.nameView.getLayoutParams();

        if (chatMessage.isLoginUser()) {
            avatorViewLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            containerLp.addRule(RelativeLayout.LEFT_OF, R.id.avator);
            nameLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            messageLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

            avatorViewLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            containerLp.addRule(RelativeLayout.RIGHT_OF, 0);
            nameLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            messageLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);

            holder.messageItem.setBackgroundResource(R.mipmap.chat_item_bg_login);
            holder.avatorView.setImageResource(R.mipmap.demo_avator_login_user);
        } else {
            avatorViewLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            containerLp.addRule(RelativeLayout.RIGHT_OF, R.id.avator);
            nameLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            messageLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

            avatorViewLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            containerLp.addRule(RelativeLayout.LEFT_OF, 0);
            nameLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            messageLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            holder.messageItem.setBackgroundResource(R.mipmap.chat_item_bg_there);
            holder.avatorView.setImageResource(R.mipmap.demo_avator_there);
        }

        holder.avatorView.setLayoutParams(avatorViewLp);
        holder.containerLayout.setLayoutParams(containerLp);
        holder.nameView.setLayoutParams(nameLp);
        holder.messageItem.setLayoutParams(messageLp);

    }

    protected abstract View getViewItem(BaseChatMessage chatMessage, View convertView, ViewGroup parent);

    private static class ViewHolder {

        TextView nameView;
        ImageView avatorView;
        RelativeLayout containerLayout;
        ViewGroup messageItem;

        public ViewHolder(View view) {
            nameView = (TextView) view.findViewById(R.id.name);
            avatorView = (ImageView) view.findViewById(R.id.avator);
            messageItem = (ViewGroup) view.findViewById(R.id.message);
            containerLayout = (RelativeLayout) view.findViewById(R.id.content_view);
        }

        public void display(TextChatMessage message) {
            nameView.setText(message.getUserName());
        }

    }
}
