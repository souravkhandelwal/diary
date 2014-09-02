package com.hackers.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AddEvent extends Activity {

    private EditText mEventText;
    public static final String EVENT_TYPE = "eventType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        setTitle(R.string.title_activity_add_event);

        mEventText = (EditText) findViewById(R.id.EventType);

        Button confirmButton = (Button) findViewById(R.id.confirm);

        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString(EVENT_TYPE, mEventText.getText().toString());

                Intent mIntent = new Intent();
                mIntent.putExtras(bundle);
                setResult(RESULT_OK, mIntent);
                finish();
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
