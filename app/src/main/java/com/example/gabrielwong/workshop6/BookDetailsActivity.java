package com.example.gabrielwong.workshop6;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

 public class BookDetailsActivity extends Activity {

     int []ids = {R.id.editText1, R.id.editText2, R.id.editText3,
             R.id.editText4, R.id.editText5, R.id.editText6, R.id.editText7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        Intent i = getIntent();
        String bookid = i.getStringExtra("bookId");

        new AsyncTask<String, Void, Book>() {
            @Override
            protected Book doInBackground(String... params) {
                return Book.getBook(params[0]);
            }

            @Override
            protected void onPostExecute(Book result) {
                show(result);
            }
        }.execute(bookid);

        Button button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String []values = new String[ids.length];

                for (int i=0; i<ids.length; i++) {
                    EditText e = (EditText) findViewById(ids[i]);
                    values[i] = e.getText().toString();
                }

                Book newBook = new Book (
                        values[0],
                        values[1],
                        values[2],
                        values[3],
                        values[4],
                        values[5],
                        values[6]
                );

                Book.updateBook(newBook);
            }
        });
    }

    void show(Book book) {
        String []keys = {"BookID", "Title", "CategoryID", "ISBN", "Author", "Stock", "Price"};

        for (int i=0; i<keys.length; i++) {
            EditText e = (EditText) findViewById(ids[i]);
            e.setText(book.get(keys[i]));
        }

        String isbn = book.get("ISBN");

        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                return Book.getPhoto(params[0]);
            }
            @Override
            protected void onPostExecute(Bitmap result) {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                image.setImageBitmap(result);
            }
        }.execute(isbn);
    }
}
