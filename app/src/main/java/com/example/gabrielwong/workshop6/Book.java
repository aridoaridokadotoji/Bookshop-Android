package com.example.gabrielwong.workshop6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Book extends HashMap<String, String> {

    final static String baseURL = "http://172.17.253.129/GetFreshBooks/Inventory/";
    final static String imageURL = "http://172.17.253.129/GetFreshBooks/images";

    public Book(String bookId, String title, String catId, String isbn,
                String author, String stock, String price) {
        put("BookID", bookId);
        put("Title", title);
        put("CategoryID", catId);
        put("ISBN", isbn);
        put("Author", author);
        put("Stock", stock);
        put("Price", price);
    }

    public static List<String> list() {
        List<String> list = new ArrayList<String>();

        try {
            JSONArray b = JSONParser.getJSONArrayFromUrl(baseURL+"LoadData/");
            for (int i=0; i<b.length(); i++)
                list.add(b.getJSONObject(i).getString("BookID"));
        } catch (Exception e) {
            Log.e("Book.list()", "JSONArray error");
        }
        return(list);
    }

    public static String getTitle(String eid) {
        try {
            JSONArray b = JSONParser.getJSONArrayFromUrl(baseURL+"LoadSingle/"+eid);
            JSONObject c= b.getJSONObject(0);
            return c.getString("Title");
        } catch (Exception e) {
            Log.e("Book.getEmp()", "JSONArray or JSONObject error");
        }
        return(null);
    }

    public static Book getBook(String bid) {
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL+"LoadSingle/"+bid);
            JSONObject b = a.getJSONObject(0);
            return new Book(
                    b.getString("BookID"),
                    b.getString("Title"),
                    b.getString("CategoryID"),
                    b.getString("ISBN"),
                    b.getString("Author"),
                    b.getString("Stock"),
                    b.getString("Price"));
        } catch (Exception e) {
            Log.e("Book.getEmp()", "JSONArray error");
        }
        return(null);
    }

   public static Bitmap getPhoto(String ISBN) {
        try {
            URL url = new URL(String.format("%s/%s.jpg",imageURL, ISBN));
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Book.getPhoto()", "Bitmap error");
        }
        return(null);
    }
}