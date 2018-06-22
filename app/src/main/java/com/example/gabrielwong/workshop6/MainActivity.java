package com.example.gabrielwong.workshop6;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        ArrayList<Book> books = Book.list();
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, books);
        setListAdapter(adapter);*/
        MyAdapter adapter = new MyAdapter(this, R.layout.row, books);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        Book b = (Book) getListAdapter().getItem(position);
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("bookId", (String) b.get("BookID"));
        startActivity(intent);
    }
}
