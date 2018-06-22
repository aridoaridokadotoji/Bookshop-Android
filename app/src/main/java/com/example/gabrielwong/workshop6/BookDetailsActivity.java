package com.example.gabrielwong.workshop6;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;

public class BookDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        Intent i = getIntent();
        String eid = i.getStringExtra("eid");

//        new AsyncTask<String, Void, Book>() {
//            @Override
//            protected Book doInBackground(String... params) {
//                return Book.getEmp(params[0]);
//            }
//
//            @Override
//            protected void onPostExecute(Book result) {
//                show(result);
//            }
//        }.execute(eid);
    }

//    void show(Employee emp) {
//        int []ids = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4};
//        String []keys = {"Name", "Id", "Salary", "Address"};
//        for (int i=0; i<keys.length; i++) {
//            EditText e = (EditText) findViewById(ids[i]);
//            e.setText(emp.get(keys[i]));
//        }
//
//        String eid = emp.get("Id");
//
//        new AsyncTask<String, Void, Bitmap>() {
//            @Override
//            protected Bitmap doInBackground(String... params) {
//                return Employee.getPhoto(false, params[0]);
//            }
//            @Override
//            protected void onPostExecute(Bitmap result) {
//                ImageView image = (ImageView) findViewById(R.id.imageView);
//                image.setImageBitmap(result);
//            }
//        }.execute(eid);*/
//    }
}
