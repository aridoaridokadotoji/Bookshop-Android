package com.example.gabrielwong.workshop6;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Book> {

    private ArrayList<Book> books;
    int resource;

    public MyAdapter(Context context, int resource, ArrayList<Book> books) {
        super(context, resource, books);
        this.resource = resource;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);

        Book b = getItem(position);

        if (b != null) {
            TextView tv = (TextView) v.findViewById(R.id.TextView);
            tv.setText(b.get("Title"));
        }
        return v;
    }
}