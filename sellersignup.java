package com.example.aditya.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sellersignup extends AppCompatActivity {

    Button b;
    TextView t;
    EditText e1,e2,e3;
    String emailp,passp,passconp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellersignup);

        b = findViewById(R.id.signup1);
        t = findViewById(R.id.lnkLogin1);
        e1 = findViewById(R.id.email1);
        e2 = findViewById(R.id.pass1);
        e3 = findViewById(R.id.passcon1);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailp = e1.getText().toString();
                passp = e2.getText().toString();
                passconp = e3.getText().toString();
                Intent intent = new Intent(sellersignup.this,sellersignup1.class);
                intent.putExtra("email",emailp);
                intent.putExtra("pass",passp);
                startActivity(intent);
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sellersignup.this,sellerlogin.class);
                startActivity(intent);
            }
        });


    }
}
