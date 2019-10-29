package com.example.aditya.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aditya.dbms.Retrofit.INodeJs;
import com.example.aditya.dbms.Retrofit.RetroClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class signup1_activity extends AppCompatActivity {

    TextView t;
    EditText e1,e2,e3,e4,e5,e6;
    String namep,phnump,hnop,streetp,cityp,pincodep,emailp,passp;
    Button b1;
    Bundle b;
    INodeJs myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void onStop(){
        super.onStop();
    }

    protected void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1_activity);

        Retrofit retrofit = RetroClient.getInstance();
        myAPI = retrofit.create(INodeJs.class);

        t = findViewById(R.id.lnkLogin);

        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.phnum);
        e3 = findViewById(R.id.hno);
        e4 = findViewById(R.id.street);
        e5 = findViewById(R.id.city);
        e6 = findViewById(R.id.pincode);




        b1 = findViewById(R.id.signupf);

         b = getIntent().getExtras();

       // Intent intent= getIntent();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("Email","something");
                namep = e1.getText().toString();
                phnump = e2.getText().toString();
                hnop = e3.getText().toString();
                streetp = e4.getText().toString();
                cityp = e5.getText().toString();
                pincodep = e6.getText().toString();
                emailp=b.getString("email");
                passp=b.getString("pass");
                Log.i("Email",emailp + namep + phnump + hnop + streetp +cityp + pincodep);
               signupUser(namep,emailp,passp,phnump,streetp,cityp,pincodep);
               // signupUser(e1.getText().toString(),emailp,passp,e2.getText().toString(), e4.getText().toString(), e5.getText().toString(), e6.getText().toString());


            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup1_activity.this,login_activity.class);
                startActivity(intent);

            }
        });

    }

    private void signupUser(String name,String email,String password,String phone,String street,String city,String zipcode){
        compositeDisposable.add(myAPI.registerUser(name,email,password,phone,street,city,zipcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                            Toast.makeText( signup1_activity.this,""+s,Toast.LENGTH_SHORT).show();
                    }
                })

        );
    }


}

