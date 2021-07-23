package com.sport.playsqorr.pojos;

import java.util.List;

/**
 * Created by Kalyan on 22-07-2019.
 */

public class LeagueSports {
    public int id;
    public String name;
    public int display_order;
    public List<League> leagues;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }
}