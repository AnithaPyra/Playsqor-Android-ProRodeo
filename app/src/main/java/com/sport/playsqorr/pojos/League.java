package com.sport.playsqorr.pojos;

/**
 * Created by Kalyan on 22-07-2019.
 */

public class League{
    public int leagueId;
    public int sportId;
    public String sportName;
    public String leagueName;
    public String leagueAbbreviation;
    public String current_season_type;
    public int current_season_year;
    public boolean athlete_contest;
    public int displayOrder;
    public String image_provider;
    public String imageAPIkey;
    public boolean enabled;


    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueAbbreviation() {
        return leagueAbbreviation;
    }

    public void setLeagueAbbreviation(String leagueAbbreviation) {
        this.leagueAbbreviation = leagueAbbreviation;
    }

    public String getCurrent_season_type() {
        return current_season_type;
    }

    public void setCurrent_season_type(String current_season_type) {
        this.current_season_type = current_season_type;
    }

    public int getCurrent_season_year() {
        return current_season_year;
    }

    public void setCurrent_season_year(int current_season_year) {
        this.current_season_year = current_season_year;
    }

    public boolean isAthlete_contest() {
        return athlete_contest;
    }

    public void setAthlete_contest(boolean athlete_contest) {
        this.athlete_contest = athlete_contest;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getImage_provider() {
        return image_provider;
    }

    public void setImage_provider(String image_provider) {
        this.image_provider = image_provider;
    }

    public String getImageAPIkey() {
        return imageAPIkey;
    }

    public void setImageAPIkey(String imageAPIkey) {
        this.imageAPIkey = imageAPIkey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}