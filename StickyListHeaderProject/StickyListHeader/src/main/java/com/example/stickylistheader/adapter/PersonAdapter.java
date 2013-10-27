package com.example.stickylistheader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.stickylistheader.R;

import java.util.List;

/**
 * Created by marcin on 27.10.13.
 */
public class PersonAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;

    public PersonAdapter(Context context, List<String> objects) {
        super(context, R.layout.list_item, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.header = (TextView) convertView.findViewById(R.id.tv_header_title);
            viewHolder.label = (TextView) convertView.findViewById(R.id.tv_item_label);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HeaderViewHolder) convertView.getTag();
        }

        String label = getItem(position);
        if(position == 0 || getItem(position - 1).charAt(0) != label.charAt(0)) {
            viewHolder.header.setText(label);
            viewHolder.header.setVisibility(View.VISIBLE);
        } else {
            viewHolder.header.setVisibility(View.GONE);
        }
        viewHolder.label.setText(label);
        return convertView;
    }

    private static class HeaderViewHolder {
        private TextView header;
        private TextView label;
    }


}
