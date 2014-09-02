package com.hackers.diary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.content.res.TypedArray;
import android.widget.TextView;

import com.hackers.diary.R;


/**
 * Created by souravkhandelwal on 9/1/14.
 */
public class EventView extends LinearLayout {

    private String eventType;
    private String eventData;

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.EventView, 0, 0);
        eventType = a.getString(R.styleable.EventView_eventType);
        eventData = a.getString(R.styleable.EventView_eventData);
        a.recycle();

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.event, this, true);

        TextView title = (TextView) getChildAt(0);
        title.setText(eventType);

        TextView description = (TextView) getChildAt(1);
        description.setText(eventData);
    }

    public EventView(Context context) {
        super(context);

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.event, this, true);
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;

        TextView title = (TextView) getChildAt(0);
        title.setText(eventType);
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;

        TextView description = (TextView) getChildAt(1);
        description.setText(eventData);
    }
}

