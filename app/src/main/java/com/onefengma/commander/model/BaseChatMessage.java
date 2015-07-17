package com.onefengma.commander.model;

public abstract class BaseChatMessage {

    protected long timeStamp;
    protected  int type;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
