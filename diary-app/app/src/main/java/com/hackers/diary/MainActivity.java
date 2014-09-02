/* Copyright (C) 2012 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.hackers.diary;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hackers.diary.database.DbManager;

import java.util.Date;

public class MainActivity extends Activity {

    private static final int CREATE_EVENT = 0;

    private DbManager dbManager;

    private Button addEvent;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        dbManager = new DbManager(this);
        dbManager.open();
        setContentView(R.layout.main);

        addEvent = (Button) findViewById(R.id.addEvent);
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, EditEventActivity.class);
        startActivityForResult(intent, CREATE_EVENT);
    }

    private TextView createNewTextView(String text) {
        final LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText("New text: " + text);
        return textView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case CREATE_EVENT:
                if (resultCode == RESULT_OK) {
                    Bundle extras = intent.getExtras();
                    long status = dbManager.createEvent(
                            new Date(extras.getLong(DbManager.KEY_START_TIME)),
                            extras.getInt(DbManager.KEY_EVENT_TYPE),
                            extras.getString(DbManager.KEY_EVENT_DATA));
                    Toast toast = Toast.makeText(this, "Created" + status, 10);
                    toast.show();
                } else {
                    Toast.makeText(this, "action cancelled", 10).show();
                }
                break;

        }
    }
}

