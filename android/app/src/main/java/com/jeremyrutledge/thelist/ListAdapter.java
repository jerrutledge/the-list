package com.jeremyrutledge.thelist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremy on 2015-05-17.
 */
public class ListAdapter extends BaseAdapter {

    Context currentContext;
    ArrayList<ListItem> data;
    int layoutResourceId;

    public ListAdapter(Context context, int resource, ArrayList<ListItem> itemArray) {
        currentContext = context;
        data = itemArray;
        layoutResourceId = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) currentContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layoutResourceId, parent, false);

        TextView nameView = (TextView) rowView.findViewById(R.id.list_item_name);

        ListItem item = data.get(position);
        nameView.setText(item.name);

        return rowView;
    }
}
