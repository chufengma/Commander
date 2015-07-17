package com.onefengma.commander.ui;

import android.view.View;
import android.view.ViewGroup;

import com.onefengma.commander.model.BaseChatMessage;

public interface IChatMessageBuilder {

    View getView(BaseChatMessage chatMessage, View convertView, ViewGroup parent);

}
