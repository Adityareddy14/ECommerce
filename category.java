package com.example.aditya.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class category extends AppCompatActivity {

    Button b1,b2,b3,b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        b1=findViewById(R.id.elec);
        b2=findViewById(R.id.pan);
        b3=findViewById(R.id.men);
        b4=findViewById(R.id.women);
        b5=findViewById(R.id.kid);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,productsdisplay.class);
                intent.putExtra("val","1");
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,productsdisplay.class);
                intent.putExtra("val","2");
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,productsdisplay.class);
                intent.putExtra("val","3");
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,productsdisplay.class);
                intent.putExtra("val","4");
                startActivity(intent);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,productsdisplay.class);
                intent.putExtra("val","5");
                startActivity(intent);
            }
        });
    }
}
