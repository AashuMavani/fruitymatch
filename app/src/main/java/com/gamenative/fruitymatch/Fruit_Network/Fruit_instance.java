package com.gamenative.fruitymatch.Fruit_Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fruit_instance {
    public static Fruit_Interface CallAPI(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://judoapps.com/Apps/FruityMatch/api100/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Fruit_Interface.class);
    }
}
