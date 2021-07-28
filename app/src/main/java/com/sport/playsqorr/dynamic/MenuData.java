package com.sport.playsqorr.dynamic;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MenuData {
    @SerializedName("id")
    private String strId;
    @SerializedName("name")
    private String strName;
    @SerializedName("display_order")
    private String strDisplayOrder;
    @SerializedName("leagues")
    private ArrayList<Leagues> arrLeague;

    public MenuData(String strId, String strName, String strDisplayOrder, ArrayList<Leagues> arrLeague) {
        this.strId = strId;
        this.strName = strName;
        this.strDisplayOrder = strDisplayOrder;
        this.arrLeague = arrLeague;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrDisplayOrder() {
        return strDisplayOrder;
    }

    public void setStrDisplayOrder(String strDisplayOrder) {
        this.strDisplayOrder = strDisplayOrder;
    }

    public ArrayList<Leagues> getArrLeague() {
        return arrLeague;
    }

    public void setArrLeague(ArrayList<Leagues> arrLeague) {
        this.arrLeague = arrLeague;
    }

}
