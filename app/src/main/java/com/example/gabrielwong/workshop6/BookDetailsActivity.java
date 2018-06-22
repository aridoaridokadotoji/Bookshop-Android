package com.example.gabrielwong.workshop6;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.ImageView;

public class BookDetailsActivity extends Activity {

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
    }

    void show(Book book) {
        int []ids = {R.id.editText1, R.id.editText2, R.id.editText3,
                R.id.editText4, R.id.editText5, R.id.editText6, R.id.editText7};
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
