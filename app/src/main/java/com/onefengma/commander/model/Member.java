package com.onefengma.commander.model;

import java.io.Serializable;

public class Member implements Serializable {
    private String memeberAvator;
    private String memberName;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemeberAvator() {
        return memeberAvator;
    }

    public void setMemeberAvator(String memeberAvator) {
        this.memeberAvator = memeberAvator;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
