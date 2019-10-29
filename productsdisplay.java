package com.example.aditya.dbms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aditya.dbms.Retrofit.INodeJs;
import com.example.aditya.dbms.Retrofit.RetroClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class productsdisplay extends AppCompatActivity {
    TextView tv;
    ListView listView;
    String dataParsed = "";
    String singleParsed = "";
    INodeJs myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
   GridView androidGridView;
    GridView gridview;

    public static int[] osImages  = {
            R.drawable.e12345501,
            R.drawable.e12345502,R.drawable.e12345503,R.drawable.e12345504,R.drawable.e12345505,
            R.drawable.e12345506,R.drawable.e12345507,R.drawable.e12345508,R.drawable.e12345509,
            R.drawable.e12345510,R.drawable.e12345511,R.drawable.e12345512,R.drawable.e12345513,R.drawable.e12345514,
            R.drawable.e12345515,R.drawable.e12345516,R.drawable.e12345517,R.drawable.e12345518,R.drawable.e12345519,
            R.drawable.e12345520,R.drawable.e12345521,R.drawable.e12345522,R.drawable.e12345523,R.drawable.e12345524,
            R.drawable.e12345525,
    };

    public static int[] pantryimg  = {
            R.drawable.p1134511,
            R.drawable.p1134512,R.drawable.p1134513,R.drawable.p1134514,R.drawable.p1134515,
            R.drawable.p1134516,R.drawable.p1134517,R.drawable.p1134518,R.drawable.p1134519,
            R.drawable.p1134520,R.drawable.p1134521,R.drawable.p1134522,R.drawable.p1134523,R.drawable.p1134524,
            R.drawable.p1134525,R.drawable.p1134526,R.drawable.p1134527,R.drawable.p1134528,R.drawable.p1134529,
            R.drawable.p1134530,R.drawable.p1134531,R.drawable.p1134532,R.drawable.p1134533,R.drawable.p1134534,
            R.drawable.p1134535,R.drawable.p1134540,R.drawable.p1134541,R.drawable.p1134542,R.drawable.p1134543,
            R.drawable.p1134544,R.drawable.p1134545,R.drawable.p1134546,R.drawable.p1134547,R.drawable.p1134548,
            R.drawable.p1134549,
    };

    public static int[] menimg  = {
            R.drawable.m3456101,
            R.drawable.m3456102,R.drawable.m3456103,R.drawable.m3456104,R.drawable.m3456105,
            R.drawable.m3456106,R.drawable.m3456107,R.drawable.m3456108,R.drawable.m3456109,
            R.drawable.m3456110,R.drawable.m3456111,R.drawable.m3456112,R.drawable.m3456113,R.drawable.m3456114,
            R.drawable.m3456115,R.drawable.m3456116,R.drawable.m3456117,R.drawable.m3456118,R.drawable.m3456119,
            R.drawable.m3456120,R.drawable.m3456121,R.drawable.m3456122,R.drawable.m3456123,R.drawable.m3456124,
            R.drawable.m3456125,
    };


    public static int[] womenimg  = {
            R.drawable.w1567101,
            R.drawable.w1567102,R.drawable.w1567103,R.drawable.w1567104,R.drawable.w1567105,
            R.drawable.w1567106,R.drawable.w1567107,R.drawable.w1567108,R.drawable.w1567109,
            R.drawable.w1567110,
    };

    public static int[] kidsimg  = {
            R.drawable.k2189101,
            R.drawable.k2189102,R.drawable.k2189103,R.drawable.k2189104,R.drawable.k2189105,
            R.drawable.k2189106,R.drawable.k2189107,R.drawable.k2189108,R.drawable.k2189109,
            R.drawable.k2189110,
    };

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
        setContentView(R.layout.activity_productsdisplay);

      //  tv=findViewById(R.id.textView6);
      //  listView=findViewById(R.id.lv);




        Intent in = getIntent();
        Bundle bundle= in.getExtras();
        String s=bundle.getString("val");

        Retrofit retrofit = RetroClient.getInstance();
        myAPI = retrofit.create(INodeJs.class);

        switch (s){
            case "1": //tv.setText("Electronics");
                     electronics("1");
            break;
            case "2"://tv.setText("Pantry");
                     pantry("2");
            break;
            case "3"://tv.setText("Pantry");
                    men("3");
            break;
            case "4"://tv.setText("Pantry");
                    women("4");
            break;
            case "5"://tv.setText("Pantry");
                    kids("5");
            break;

            default:tv.setText("hello");
            break;
        }
    }


    private void electronics(String val){
        compositeDisposable.add(myAPI.electronics(val)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                     //   Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();
                        json(s);
                    }
                })

        );
    }

   public ArrayList<String> json(String s) throws JSONException{

      // Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();

        ArrayList<String> stringArray = new ArrayList<String>();

        JSONArray jsonArray = new JSONArray(s);

        for(int i=0;i<jsonArray.length();i++){
            stringArray.add(jsonArray.getString(i));
            JSONObject JO = (JSONObject) jsonArray.get(i);
            singleParsed = "Name:" + JO.get("productName");
            dataParsed = dataParsed + singleParsed;

        }

       Log.e("fucku",dataParsed);

//       JSONObject read = new JSONObject(s);
//       JSONObject sys = read.getJSONObject("sys");
//       String name=read.getString("productName");



//       ArrayList<HashMap<String,String>> elcinfo = new ArrayList<>();
//       JSONObject jsonObject = new JSONObject(s);
//       JSONArray jsonArray1 = jsonObject.getJSONArray("electronics");
//
//       for(int i=0;i<jsonArray1.length();i++){
//           JSONObject c = jsonArray1.getJSONObject(i);
//           String name = c.getString("productName");
//           String price =c.getString("price");
//
//           HashMap<String,String> hashMap = new HashMap<>();
//           hashMap.put("name",name);
//           hashMap.put("price",price);
//
//           elcinfo.add(hashMap);
//       }


       gridview = (GridView) findViewById(R.id.customgrid);
       gridview.setAdapter(new CustomAdapter(this, stringArray, osImages));

       return stringArray;

    }

    private void pantry(String val){
        compositeDisposable.add(myAPI.pantry(val)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                      //  Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();
                        json1(s);
                    }
                })

        );
    }


    public ArrayList<String> json1(String s) throws JSONException{

        // Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();

        ArrayList<String> stringArray = new ArrayList<String>();

        JSONArray jsonArray = new JSONArray(s);

        for(int i=0;i<jsonArray.length();i++){
            stringArray.add(jsonArray.getString(i));
            JSONObject JO = (JSONObject) jsonArray.get(i);
            singleParsed = "Name:" + JO.get("productName");
            dataParsed = dataParsed + singleParsed;

        }

        gridview = (GridView) findViewById(R.id.customgrid);
        gridview.setAdapter(new CustomAdapter(this, stringArray, pantryimg));

        return stringArray;

    }


    private void men(String val){
        compositeDisposable.add(myAPI.men(val)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //  Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();
                        json2(s);
                    }
                })

        );
    }

    public ArrayList<String> json2(String s) throws JSONException{

        // Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();

        ArrayList<String> stringArray = new ArrayList<String>();

        JSONArray jsonArray = new JSONArray(s);

        for(int i=0;i<jsonArray.length();i++){
            stringArray.add(jsonArray.getString(i));
            JSONObject JO = (JSONObject) jsonArray.get(i);
            singleParsed = "Name:" + JO.get("productName");
            dataParsed = dataParsed + singleParsed;

        }

        gridview = (GridView) findViewById(R.id.customgrid);
        gridview.setAdapter(new CustomAdapter(this, stringArray, menimg));

        return stringArray;

    }

    private void women(String val){
        compositeDisposable.add(myAPI.women(val)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //  Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();
                        json3(s);
                    }
                })

        );
    }

    public ArrayList<String> json3(String s) throws JSONException{

        // Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();

        ArrayList<String> stringArray = new ArrayList<String>();

        JSONArray jsonArray = new JSONArray(s);

        for(int i=0;i<jsonArray.length();i++){
            stringArray.add(jsonArray.getString(i));
            JSONObject JO = (JSONObject) jsonArray.get(i);
            singleParsed = "Name:" + JO.get("productName");
            dataParsed = dataParsed + singleParsed;

        }

        gridview = (GridView) findViewById(R.id.customgrid);
        gridview.setAdapter(new CustomAdapter(this, stringArray, womenimg));

        return stringArray;

    }

    private void kids(String val){
        compositeDisposable.add(myAPI.kids(val)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //  Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();
                        json4(s);
                    }
                })

        );
    }


    public ArrayList<String> json4(String s) throws JSONException{

        // Toast.makeText(productsdisplay.this, s, Toast.LENGTH_SHORT).show();

        ArrayList<String> stringArray = new ArrayList<String>();

        JSONArray jsonArray = new JSONArray(s);

        for(int i=0;i<jsonArray.length();i++){
            stringArray.add(jsonArray.getString(i));
            JSONObject JO = (JSONObject) jsonArray.get(i);
            singleParsed = "Name:" + JO.get("productName");
            dataParsed = dataParsed + singleParsed;

        }

        gridview = (GridView) findViewById(R.id.customgrid);
        gridview.setAdapter(new CustomAdapter(this, stringArray, kidsimg));

        return stringArray;

    }


}
