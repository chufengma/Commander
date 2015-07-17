package com.onefengma.commander.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onefengma.commander.model.BaseChatMessage;
import com.onefengma.commander.model.TextChatMessage;

public class TextChatMessageBuilder implements IChatMessageBuilder {

    private Context context;

    public TextChatMessageBuilder(Context context) {
        this.context = context;
    }

    @Override
    public View getView(BaseChatMessage chatMessage, View convertView, ViewGroup parent) {
        TextChatMessage message = (TextChatMessage) chatMessage;
        if (convertView == null) {
            convertView = new TextView(context);
        }
        ((TextView)convertView).setText(message.getMessage());
        return convertView;
    }

}
