package com.example.aditya.dbms.Retrofit;

/**
 * Created by aditya on 07-08-2019.
 */
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroClient {
    private static  Retrofit instance;

    public static  Retrofit getInstance() {
        if(instance==null)
            instance = new Retrofit.Builder()
                     .baseUrl("http://192.168.43.184:8000")
                     .addConverterFactory(ScalarsConverterFactory.create())
                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                     .build();
        return instance;
    }

}
