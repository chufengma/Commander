package com.onefengma.commander.ui;

import android.content.Context;


import com.onefengma.commander.model.MessageType;

import static com.onefengma.commander.model.MessageType.*;

public class ChatMessageBuilderFactory {

    private TextChatMessageBuilder textChatMessageBuilder;
    private Context context;

    public ChatMessageBuilderFactory(Context context) {
        this.context = context;
    }

    public IChatMessageBuilder getMessageBuilder(int type) {
        MessageType messageType = values()[type];
        switch (messageType) {
            case TEXT:
                if(textChatMessageBuilder == null) {
                    textChatMessageBuilder = new TextChatMessageBuilder(context);
                }
                return textChatMessageBuilder;
            default:
                return null;
        }
    }

}
