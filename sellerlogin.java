package com.example.aditya.dbms;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aditya.dbms.Retrofit.INodeJs;
import com.example.aditya.dbms.Retrofit.RetroClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class sellerlogin extends AppCompatActivity {

    Button b,b1;
    EditText t1,t2;
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
        setContentView(R.layout.activity_sellerlogin);

        t1 = (EditText)findViewById(R.id.email1);
        t2 = (EditText)findViewById(R.id.pass1);

        Retrofit retrofit = RetroClient.getInstance();
        myAPI = retrofit.create(INodeJs.class);

        b = findViewById(R.id.login11);
        b1 = findViewById(R.id.signup1);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(login_activity.this,signup_activity.class);
//                startActivity(intent);
                loginSeller(t1.getText().toString(),t2.getText().toString());
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sellerlogin.this,sellersignup.class);
                startActivity(intent);
            }
        });

    }

    private void loginSeller(String email, String password){
        compositeDisposable.add(myAPI.loginSeller(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.contains("encrypted_password"))
                            Toast.makeText(sellerlogin.this,"Login Success",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(sellerlogin.this,""+s,Toast.LENGTH_SHORT).show();
                    }
                })

        );
    }
}
