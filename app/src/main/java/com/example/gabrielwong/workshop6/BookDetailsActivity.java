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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

 public class BookDetailsActivity extends Activity {

     int []ids = {R.id.editText1, R.id.editText2, R.id.editText3,
             R.id.editText4, R.id.editText5, R.id.editText6};
     Book book = null;

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
                book = result;
            }
        }.execute(bookid);

        Button button3 = (Button) findViewById(R.id.updatebutton);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText stock = (EditText) findViewById(R.id.editText5);
                String stockVal = stock.getText().toString();

                EditText price = (EditText) findViewById(R.id.editText6);
                String priceVal = price.getText().toString();

                book.put("Stock", stockVal);
                book.put("Price", priceVal);

                Book.updateBook(book);
                Toast.makeText(BookDetailsActivity.this,"Updated", Toast.LENGTH_SHORT).show();


            }
        });
    }


    void show(Book book) {

        TextView e1 = (TextView) findViewById(R.id.editText1);
        e1.setText(book.get("BookID"));
        TextView e2 = (TextView) findViewById(R.id.editText2);
        e2.setText(book.get("Title"));
        TextView e3 = (TextView) findViewById(R.id.editText3);
        e3.setText(book.get("ISBN"));
        TextView e4 = (TextView) findViewById(R.id.editText4);
        e4.setText(book.get("Author"));
        EditText e5 = (EditText) findViewById(R.id.editText5);
        e5.setText(book.get("Stock"));
        EditText e6 = (EditText) findViewById(R.id.editText6);
        e6.setText(book.get("Price"));

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
