package com.example.gabrielwong.workshop6;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Book> implements Filterable {

    private ArrayList<Book> books;
    int resource;

    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    private ArrayList<Book> filteredData = null;

    public MyAdapter(Context context, int resource, ArrayList<Book> books) {
        super(context, resource, books);
        this.resource = resource;
        this.filteredData = books;
        this.books = books;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Book getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
            TextView tv9= (TextView) v.findViewById(R.id.TextView9);
            tv9.setText("$ "+b.get("Price"));
            ImageView image = (ImageView) v.findViewById(R.id.imageView2);
          image.setImageBitmap(Book.getPhoto(b.get("ISBN")));

        }
        return v;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final ArrayList<Book> list = books;
            int count = list.size();

            final ArrayList<Book> nlist = new ArrayList<Book>(count);

            Book filterableBook;

            for (int i = 0; i < count; i++) {
                filterableBook = list.get(i);
                if (filterableBook.get("Title").toLowerCase().contains(filterString)) {
                    nlist.add(filterableBook);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Book>) results.values;
            notifyDataSetChanged();
        }

    }
}