package com.sport.playsqorr.home.model


import com.google.gson.annotations.SerializedName

data class HomeCardsModelItem(
    @SerializedName("cardId")
    val cardId: Int?,
    @SerializedName("cardTitle")
    val cardTitle: String?,
    @SerializedName("cardType")
    val cardType: String?,
    @SerializedName("company_ids")
    val companyIds: Any?,
    @SerializedName("leagueAbbreviation")
    val leagueAbbreviation: String?,
    @SerializedName("leagueId")
    val leagueId: Int?,
    @SerializedName("leagueSubTitle")
    val leagueSubTitle: String?,
    @SerializedName("matchupType")
    val matchupType: String?,
    @SerializedName("playerImages")
    val playerImages: List<String>?,
    @SerializedName("sport_id")
    val sportId: Int?,
    @SerializedName("sport_name")
    val sportName: String?,
    @SerializedName("startTime")
    val startTime: String?,
    @SerializedName("video_url")
    val videoUrl: Any?
)