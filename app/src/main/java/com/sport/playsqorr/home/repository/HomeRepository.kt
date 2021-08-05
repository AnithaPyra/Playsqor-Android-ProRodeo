package com.sport.playsqorr.home.repository

import android.content.ContentValues
import androidx.lifecycle.MutableLiveData
import com.app.applibrary.volley.apicall.InputForAPI
import com.educonnect.libraries.utils.logd
import com.google.gson.Gson
import com.sport.playsqorr.home.model.HomeCardsModel
import com.sport.playsqorr.volley.apicall.Coroutines
import org.json.JSONObject

class HomeRepository {

    val TAG: String = "CommonRepo"

/*
    fun callCardsApi(inputApi: InputForAPI): MutableLiveData<HomeCardsModel>? {
        logd(TAG, "URL" + (inputApi.url?.toString()))
        logd(TAG, "Params" + (inputApi.jsonObject?.toString()))
        var response: MutableLiveData<HomeCardsModel>? = MutableLiveData()
        val gson = Gson()
        Coroutines.ioWorker {
            ApiCall.PostMethodStatusCode(inputApi, object : ApiCall.ResponseHandler {
                override fun setDataResponse(data: JSONObject) {
                    logd(ContentValues.TAG, " ApiResponse $response")
                    val apiResponse = gson.fromJson<HomeCardsModel>(
                        data.toString(),
                        HomeCardsModel::class.java
                    )
                    Coroutines.mainWorker { response?.value = apiResponse }
                }

                override fun setResponseError(error: String) {
                    var errorResponse = HomeCardsModel()
//                    errorResponse.error = true
//                    errorResponse.message = error
                    Coroutines.mainWorker { response?.value = errorResponse }
                }
            })
        }
        return response
    }
*/

}