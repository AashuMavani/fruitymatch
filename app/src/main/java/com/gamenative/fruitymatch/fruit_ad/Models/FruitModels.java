
package com.gamenative.fruitymatch.fruit_ad.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FruitModels {

    @SerializedName("adMobAppOpen")
    @Expose
    private String adMobAppOpen;
    @SerializedName("adMobBanner")
    @Expose
    private String adMobBanner;
    @SerializedName("adMobInterstitial")
    @Expose
    private String adMobInterstitial;
    @SerializedName("adMobRewarded")
    @Expose
    private String adMobRewarded;
    @SerializedName("adMobANative")
    @Expose
    private String adMobANative;
    @SerializedName("lovinBannerID")
    @Expose
    private List<String> lovinBannerID;
    @SerializedName("lovinInterstitialID")
    @Expose
    private List<String> lovinInterstitialID;
    @SerializedName("lovinRewardID")
    @Expose
    private List<String> lovinRewardID;
    @SerializedName("lovinAppOpenID")
    @Expose
    private List<String> lovinAppOpenID;
    @SerializedName("lovinNativeID")
    @Expose
    private List<String> lovinNativeID;
    @SerializedName("lovin_smallNativeID")
    @Expose
    private List<String> lovinSmallNativeID;
    @SerializedName("isAppAdShow")
    @Expose
    private String isAppAdShow;
    @SerializedName("WhichOne")
    @Expose
    private String whichOne;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public String getAdMobAppOpen() {
        return adMobAppOpen;
    }

    public void setAdMobAppOpen(String adMobAppOpen) {
        this.adMobAppOpen = adMobAppOpen;
    }

    public String getAdMobBanner() {
        return adMobBanner;
    }

    public void setAdMobBanner(String adMobBanner) {
        this.adMobBanner = adMobBanner;
    }

    public String getAdMobInterstitial() {
        return adMobInterstitial;
    }

    public void setAdMobInterstitial(String adMobInterstitial) {
        this.adMobInterstitial = adMobInterstitial;
    }

    public String getAdMobRewarded() {
        return adMobRewarded;
    }

    public void setAdMobRewarded(String adMobRewarded) {
        this.adMobRewarded = adMobRewarded;
    }

    public String getAdMobANative() {
        return adMobANative;
    }

    public void setAdMobANative(String adMobANative) {
        this.adMobANative = adMobANative;
    }



    public List<String> getLovinInterstitialID() {
        return lovinInterstitialID;
    }

    public List<String> getLovinRewardID() {
        return lovinRewardID;
    }

    public List<String> getLovinAppOpenID() {
        return lovinAppOpenID;
    }

    public List<String> getLovinBannerID() {
        return lovinBannerID;
    }

    public void setLovinBannerID(List<String> lovinBannerID) {
        this.lovinBannerID = lovinBannerID;
    }


    public void setLovinInterstitialID(List<String> lovinInterstitialID) {
        this.lovinInterstitialID = lovinInterstitialID;
    }


    public void setLovinRewardID(List<String> lovinRewardID) {
        this.lovinRewardID = lovinRewardID;
    }


    public void setLovinAppOpenID(List<String> lovinAppOpenID) {
        this.lovinAppOpenID = lovinAppOpenID;
    }

    public List<String> getLovinNativeID() {
        return lovinNativeID;
    }

    public void setLovinNativeID(List<String> lovinNativeID) {
        this.lovinNativeID = lovinNativeID;
    }

    public List<String> getLovinSmallNativeID() {
        return lovinSmallNativeID;
    }

    public void setLovinSmallNativeID(List<String> lovinSmallNativeID) {
        this.lovinSmallNativeID = lovinSmallNativeID;
    }

    public String getIsAppAdShow() {
        return isAppAdShow;
    }

    public void setIsAppAdShow(String isAppAdShow) {
        this.isAppAdShow = isAppAdShow;
    }

    public String getWhichOne() {
        return whichOne;
    }

    public void setWhichOne(String whichOne) {
        this.whichOne = whichOne;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
