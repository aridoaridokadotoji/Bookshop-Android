package com.example.gabrielwong.getfreshbooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayAdapter<Book> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        ArrayList<Book> books = Book.list();

        ListView list = (ListView) findViewById(R.id.listview1);
        EditText filter = (EditText) findViewById(R.id.filter);



        adapter = new MyAdapter(this, R.layout.row, books);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Book b = (Book) adapterView.getItemAtPosition(i);

                    Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
                    intent.putExtra("bookId", (String) b.get("BookID"));
                    startActivity(intent);
                }
            });

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (MainActivity.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
