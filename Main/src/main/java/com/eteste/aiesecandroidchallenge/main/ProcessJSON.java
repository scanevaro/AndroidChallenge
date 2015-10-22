package com.eteste.aiesecandroidchallenge.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;
import org.json.JSONArray;

/**
 * Created by scanevaro on 22/10/2015.
 */
public class ProcessJSON extends AsyncTask<String, Void, JSONArray> {
    ProgressDialog pDialog;
    Context context;
    ExpandableListView expandableListView;

    public ProcessJSON(Context context, ExpandableListView expandableListView) {
        this.context = context;
        this.expandableListView = expandableListView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading List");
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.show();
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        return new JSONParser().getJSONFromUrl(strings[0]);
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        ListAdapter adapter = new ListAdapter(context);
        adapter.setJSONArray(jsonArray);
        expandableListView.setAdapter(adapter);
        pDialog.dismiss();
    }
}
