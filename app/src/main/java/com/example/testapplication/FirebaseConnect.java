package com.example.testapplication;

import android.util.Log;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


@IgnoreExtraProperties
public class FirebaseConnect {
    public String title;
    public String text;
    public Date date;

    public FirebaseConnect(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebaseConnect(String title, String text) {
        this.title = title;
        this.text = text;
        this.date = new Date(System.currentTimeMillis());
    }

    @Exclude
    public Map<String, Object> toMap() {
        Log.d("CREATION","Firebase Connect is Mapped.");
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("text", text);
        result.put("date", date);
        return result;
    }
}