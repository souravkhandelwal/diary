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

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hackers.diary.views.EventView;

public class MainActivity extends Activity {

    private static final int ADD_EVENT = 0;
    private static final int EDIT_EVENT = 1;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();

        setContentView(R.layout.main);
        
    }

    public void addEvent(View view) {
        Intent i = new Intent(this, AddEvent.class);
        startActivityForResult(i, ADD_EVENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle extras = intent.getExtras();
        switch(requestCode) {
            case ADD_EVENT:
                String title = extras.getString(AddEvent.EVENT_TYPE);
                LinearLayout mainView = (LinearLayout) findViewById(R.id.MainScroll);
                EventView eventView = new EventView(this);
                eventView.setEventData(title);
                eventView.setEventType(title);
                mainView.addView(eventView);
                break;
        }
    }
}

