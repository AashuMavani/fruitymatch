package com.gamenative.fruitymatch.Fruit_Network;

import com.gamenative.fruitymatch.fruit_ad.Models.FruitModels;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Fruit_Interface {

    @FormUrlEncoded
    @POST("HomeData")
    Call<FruitModels> callMain(@Field("adMobAppOpen")String adMobAppOpen,
                               @Field("adMobBanner")String adMobBanner,
                               @Field("adMobInterstitial")String adMobInterstitial,
                               @Field("adMobRewarded")String adMobRewarded,
                               @Field("adMobANative")String adMobANative);
}
