package com.eteste.aiesecandroidchallenge.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by scanevaro on 22/10/2015.
 */
public class ListAdapter extends BaseExpandableListAdapter {
    JSONArray jsonArray;
    Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    public void setJSONArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row, null);
        }
        try {
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            tvTitle.setText("Title: " + jsonArray.getJSONObject(groupPosition).getString("title"));
            TextView tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            tvAuthor.setText("Author: " + jsonArray.getJSONObject(groupPosition).getString("authors"));
            TextView tvWebsite = (TextView) convertView.findViewById(R.id.tvWebsite);
            tvWebsite.setText("Website: " + jsonArray.getJSONObject(groupPosition).getString("website"));
            TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            tvDate.setText("Date: " + jsonArray.getJSONObject(groupPosition).getString("date"));
            new FetchImage((ImageView) convertView.findViewById(R.id.iv)).execute(jsonArray.getJSONObject(groupPosition).getString("image"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.contentrow, null);
        }
        TextView tvContent = (TextView) convertView.findViewById(R.id.tvContent);
        try {
            tvContent.setText(jsonArray.getJSONObject(groupPosition).getString("content"));
            new FetchImage((ImageView) convertView.findViewById(R.id.ivContent)).execute(jsonArray.getJSONObject(groupPosition).getString("image"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return jsonArray.length();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}