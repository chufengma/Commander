package com.onefengma.commander.model;

public class TextChatMessage extends BaseChatMessage {

    protected String message;

    public TextChatMessage() {
        type = MessageType.TEXT.ordinal();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
