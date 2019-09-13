package com.example.connecttofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    Button saveBtn, rBtn;
    TextView textV;
    DatabaseReference db;
    Person person;
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "FireBase connection success", Toast.LENGTH_SHORT).show();

        editName = (EditText)findViewById(R.id.editName);
        textV = (TextView)findViewById(R.id.textV);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        rBtn = (Button)findViewById(R.id.rBtn);
        word = "";


        // passing real data to our database
        db = FirebaseDatabase.getInstance().getReference().child("Person");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person = new Person();

                person.setName(editName.getText().toString());
                db.push().setValue(person);
            }
        });

        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               db = FirebaseDatabase.getInstance().getReference().child("Person").child("1");
               db.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       String fName = dataSnapshot.child("Person").getValue().toString();
                       textV.setText(fName);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
            }
        });
    }
}
