package com.onefengma.commander.model;

public abstract class BaseChatMessage {

    protected long timeStamp;
    protected  int type;
    protected String userName;
    protected boolean isLoginUser;

    public boolean isLoginUser() {
        return isLoginUser;
    }

    public void setIsLoginUser(boolean isLoginUser) {
        this.isLoginUser = isLoginUser;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
