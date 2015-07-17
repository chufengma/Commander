package com.onefengma.commander.utils;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputUtils {

    public static void hideSoftKeyboard(InputMethodManager manager, IBinder binder) {
        manager.hideSoftInputFromWindow(binder, 0);
    }

    public static void showSoftKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, 0);
    }

}
