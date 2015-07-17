package com.onefengma.commander.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onefengma.commander.R;
import com.onefengma.commander.model.BaseChatMessage;
import com.onefengma.commander.model.TextChatMessage;

public class TextChatMessageBuilder extends BaseChatMessageBuilder {


    public TextChatMessageBuilder(Context context) {
        super(context);
    }

    @Override
    protected View getViewItem(BaseChatMessage chatMessage, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.text_chat_message_layout, parent, false);
        }
        ((TextView)convertView).setText(((TextChatMessage)chatMessage).getMessage());
        return convertView;
    }


}
