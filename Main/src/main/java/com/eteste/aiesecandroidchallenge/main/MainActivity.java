package com.eteste.aiesecandroidchallenge.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;


public class MainActivity extends Activity {
    private final String url = "http://www.ckl.io/challenge/";
    private ExpandableListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ExpandableListView) findViewById(R.id.lvExp);
        new ProcessJSON(MainActivity.this, list).execute(url);
    }
}