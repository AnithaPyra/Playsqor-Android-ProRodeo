package com.sport.playsqorr.pojos;

import java.util.ArrayList;

public class GetCardsResponse {
    public ArrayList<MyCardsPojo> getListMyCardsPojo() {
        return listMyCardsPojo;
    }

    public void setListMyCardsPojo(ArrayList<MyCardsPojo> listMyCardsPojo) {
        this.listMyCardsPojo = listMyCardsPojo;
    }

    ArrayList<MyCardsPojo>  listMyCardsPojo;
}
