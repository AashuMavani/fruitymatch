package com.gamenative.fruitymatch.Ads_Models;

public class Model {
    String isAppAdShow;
    String whichOne;
    String message;
    String status;

    public Model(String isAppAdShow, String whichOne, String message, String status) {
        this.isAppAdShow = isAppAdShow;
        this.whichOne = whichOne;
        this.message = message;
        this.status = status;
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

    @Override
    public String toString() {
        return "Model{" +
                "isAppAdShow='" + isAppAdShow + '\'' +
                ", whichOne='" + whichOne + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
