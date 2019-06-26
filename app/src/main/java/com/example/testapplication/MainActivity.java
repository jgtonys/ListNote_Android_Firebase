package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference firebaseDatabase;

    EditText title;
    EditText textarea;
    Button registerBtn;

    String title_text;
    String textarea_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.title);
        textarea = (EditText) findViewById(R.id.textarea);
        registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener(btnClick);
    }

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            title_text = title.getText().toString();
            textarea_text = textarea.getText().toString();
            postFirebaseDatabase(true);
            //databaseReference.child("message").push().setValue("2");
        }
    };

    public void postFirebaseDatabase(boolean add){
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        String key = firebaseDatabase.child("note_list").push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            FirebaseConnect post = new FirebaseConnect(title_text, textarea_text);
            postValues = post.toMap();
        }
        childUpdates.put("/note_list/" + key, postValues);
        firebaseDatabase.updateChildren(childUpdates);
    }
}
