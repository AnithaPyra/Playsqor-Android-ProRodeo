package com.sport.playsqorr.dynamic;
import com.google.gson.annotations.SerializedName;

public class Leagues {

    @SerializedName("leagueId")
    public Integer leagueId;

    @SerializedName("sportId")
    public Integer sportId;

    @SerializedName("sportName")
    public Object sportName;

    @SerializedName("leagueName")
    public String leagueName;

    @SerializedName("leagueAbbreviation")
    public String leagueAbbreviation;

    @SerializedName("current_season_type")
    public Object currentSeasonType;

    @SerializedName("current_season_year")
    public Integer currentSeasonYear;

    @SerializedName("athlete_contest")
    public Boolean athleteContest;

    @SerializedName("displayOrder")
    public Integer displayOrder;

    @SerializedName("image_provider")
    public String imageProvider;

    @SerializedName("imageAPIkey")
    public String imageAPIkey;

    @SerializedName("enabled")
    public Boolean enabled;

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public Object getSportName() {
        return sportName;
    }

    public void setSportName(Object sportName) {
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

    public Object getCurrentSeasonType() {
        return currentSeasonType;
    }

    public void setCurrentSeasonType(Object currentSeasonType) {
        this.currentSeasonType = currentSeasonType;
    }

    public Integer getCurrentSeasonYear() {
        return currentSeasonYear;
    }

    public void setCurrentSeasonYear(Integer currentSeasonYear) {
        this.currentSeasonYear = currentSeasonYear;
    }

    public Boolean getAthleteContest() {
        return athleteContest;
    }

    public void setAthleteContest(Boolean athleteContest) {
        this.athleteContest = athleteContest;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getImageProvider() {
        return imageProvider;
    }

    public void setImageProvider(String imageProvider) {
        this.imageProvider = imageProvider;
    }

    public String getImageAPIkey() {
        return imageAPIkey;
    }

    public void setImageAPIkey(String imageAPIkey) {
        this.imageAPIkey = imageAPIkey;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
