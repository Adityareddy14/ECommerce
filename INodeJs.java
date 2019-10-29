package com.example.aditya.dbms.Retrofit;


import com.example.aditya.dbms.commerce;
import com.example.aditya.dbms.Retrofit.RetroClient;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by aditya on 07-08-2019.
 */

public interface INodeJs {
    @POST("cregister")
    @FormUrlEncoded
    Observable<String> registerUser(
                                                 @Field("name") String name,
                                                 @Field("email") String email,
                                                 @Field("password") String password,
                                                 @Field("phone") String phone,
                                                 @Field("street") String street,
                                                 @Field("city") String city,
                                                 @Field("zipcode") String zipcode);


    @POST("clogin")
    @FormUrlEncoded
    Observable<String> loginUser(
                                           @Field("email") String email,
                                           @Field("password") String password);


    @POST("slogin")
    @FormUrlEncoded
    Observable<String> loginSeller(
                                           @Field("email") String email,
                                           @Field("password") String password);


    @POST("sregister")
    @FormUrlEncoded
    Observable<String> registerSeller(
                                            @Field("name") String name,
                                            @Field("email") String email,
                                            @Field("password") String password,
                                            @Field("phone") String phone,
                                            @Field("bankacc") String bankacc,
                                            @Field("street") String street,
                                            @Field("city") String city,
                                            @Field("zipcode") String zipcode);



    @POST("electronics")
    @FormUrlEncoded
    Observable<String> electronics(
            @Field("val") String val
     );

    @POST("pantry")
    @FormUrlEncoded
    Observable<String> pantry(
            @Field("val") String val
    );

    @POST("men")
    @FormUrlEncoded
    Observable<String> men(
            @Field("val") String val
    );

    @POST("women")
    @FormUrlEncoded
    Observable<String> women(
            @Field("val") String val
    );

    @POST("kids")
    @FormUrlEncoded
    Observable<String> kids(
            @Field("val") String val
    );


}
