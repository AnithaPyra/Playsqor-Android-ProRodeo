package com.sport.playsqorr.home.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.applibrary.volley.apicall.InputForAPI
import com.app.applibrary.volley.getAuthorizationHeaderTwo
import com.sport.playsqorr.home.model.HomeCardsModel
import com.sport.playsqorr.home.repository.HomeRepository
import com.sport.playsqorr.utilities.APIs
import org.json.JSONObject

class HomeViewmodel(var mContext: Context) : ViewModel() {

    var repositoryHome = HomeRepository()
    var liveHomeCards = MutableLiveData<HomeCardsModel>()
    var listSportId = ArrayList<Int>()
    var listLeaguetId = ArrayList<Int>()


/*
    fun callGetCardsApi() {
        var jsonObject = JSONObject()
        repositoryHome.callCardsApi(
            getApiParamsAuthorization(
                jsonObject,
                APIs.GET_CARDS
            )
        )
            ?.observeForever {
                liveHomeCards.value = it
            }
    }
*/



    /**************************** Params Authorization *********************/
    private fun getApiParamsAuthorization(
        jsonObject: JSONObject,
        url: String
    ): InputForAPI {

        val header = getAuthorizationHeaderTwo(mContext)
        val apiInputs = InputForAPI(mContext)
        apiInputs.jsonObject = jsonObject
        apiInputs.url = url
        apiInputs.headers = header

        return apiInputs
    }


}


