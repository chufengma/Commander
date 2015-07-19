package com.onefengma.commander.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.onefengma.commander.R;

public class MemberProfileActivity extends BaseActivity {

    public static void startFrom(Activity activity) {
        activity.startActivity(new Intent(activity, MemberProfileActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);
    }

}
