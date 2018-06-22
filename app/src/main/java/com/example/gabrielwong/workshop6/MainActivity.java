package com.example.gabrielwong.workshop6;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity{

    ArrayAdapter<Book> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        ArrayList<Book> books = Book.list();

        ListView list = (ListView) findViewById(R.id.listview1);
        EditText filter = (EditText) findViewById(R.id.filter);

        ArrayList<String> people = new ArrayList<>();
        people.add("The Trials of Apollo Book Two The Dark Prophecy");
        people.add("The Wonderful Things You Will Be");
        people.add("A Court of Wings and Ruin");
        people.add("Lord of Shadows (The Dark Artifices)");
        people.add("The Day the Crayons Quit");
        people.add("If Animals Kissed Good Night");


        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, books);
        setListAdapter(adapter);
        MyAdapter adapter = new MyAdapter(this, R.layout.row, books);*/

        adapter = new MyAdapter(this, R.layout.row, books);
        list.setAdapter(adapter);

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

//    @Override
//    protected void onListItemClick(ListView l, View v,
//                                   int position, long id) {
//        Book b = (Book) getListAdapter().getItem(position);
//        Intent intent = new Intent(this, BookDetailsActivity.class);
//        intent.putExtra("bookId", (String) b.get("BookID"));
//        startActivity(intent);
//    }
}
