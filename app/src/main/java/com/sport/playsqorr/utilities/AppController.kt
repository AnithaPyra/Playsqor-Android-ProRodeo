package com.sport.playsqorr.utilities

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.app.applibrary.volley.apicall.InputForAPI
import com.app.applibrary.volley.getAuthorizationHeaderTwo
import com.educonnect.libraries.utils.logd
import com.google.gson.Gson
import org.json.JSONObject

/*class AppController : Application() {
    val TAG = "AppControler"
    var activityReferences = 0
    var startActivity = 0
    var isActivityChangingConfigurations = false
    var loginTime = -1L
    var logoutTime = -1L
    private  val BASE_URL = "https://www.suras.app/api/"
    private  val BASE_RECOMMEND = BASE_URL + "recommend/"
    private val student_Activehours = BASE_RECOMMEND +"activeHours"

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        instance = this
    }


    companion object {
        private val TAG: String = AppController::class.java.simpleName
        private var instance: AppController? = null
        private var requestQueue: RequestQueue? = null
        private var customRequestQueue: RequestQueue? = null

        @Synchronized
        fun getInstance(): AppController {
            return instance as AppController
        }
    }


    override fun onCreate() {
        super.onCreate()
        val metrics: DisplayMetrics = resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        ZohoSalesIQ.init(
            this, getString(com.educonnect.libraries.R.string.zoho_appkey), getString(
                com.educonnect.libraries.R.string.zoho_accesskey
            )
        )
        enableZohoChat(false)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity?) {

                if (AppSetttings.getInstanceOf(this@AppController).isLoggedIn()
                    && AppSetttings.getInstanceOf(this@AppController).getUserType().equals("Student",true)
                    && ++activityReferences >= 1 && !isActivityChangingConfigurations) {

                    // App enters foreground
                    logd(TAG, "App Enters Foreground ${activityReferences}" )
//                    updateOnline(inputApiForUpdateOnline(1))
                }

                if(AppSetttings.getInstanceOf(this@AppController).isLoggedIn){
                    ++startActivity;
                }
                if(startActivity == 1){
                    loginTime = System.currentTimeMillis()
                }
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityStopped(activity: Activity?) {
                --startActivity;
                logd(TAG, "App Enters Foreground ${startActivity}" )
                isActivityChangingConfigurations = activity?.isChangingConfigurations!!
                if ( AppSetttings.getInstanceOf(this@AppController).isLoggedIn()
                    && AppSetttings.getInstanceOf(this@AppController).getUserType().equals("Student",true)
                    && --activityReferences == 0 && !isActivityChangingConfigurations) {
                    logoutTime = System.currentTimeMillis()
                }
                if(startActivity == 0
                    && AppSetttings.getInstanceOf(this@AppController).getUserType().equals("Student",true)
                    && !isActivityChangingConfigurations ){
                    updateOnline(inputApiForUpdateOnline())
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityDestroyed(activity: Activity?) {

            }

        })
    }

    fun getRequestQueue(): RequestQueue? {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(
                instance
            )
        return requestQueue
    }

    fun getCustomRequestQueue(): RequestQueue? {
        var customHurlStack = CustomHurlStack()
        if (customRequestQueue == null)
            customRequestQueue = Volley.newRequestQueue(
                instance, customHurlStack
            )
        return customRequestQueue
    }

    fun <T> addrequestToQueue(request: Request<T>) {
        request.tag = TAG
        getRequestQueue()?.add(request)
    }

    fun <T> addCustomrequestToQueue(request: Request<T>) {
        request.tag = TAG
        getCustomRequestQueue()?.add(request)
    }

    fun inputApiForUpdateOnline(): InputForAPI {
        val inputForApi = InputForAPI(this)
        inputForApi.headers = getAuthorizationHeaderTwo(this)
        inputForApi.url = student_Activehours
        var jsonObject = JSONObject()
        if(logoutTime > loginTime) {
            var time = (logoutTime - loginTime)/ (1000*60)
            jsonObject.put("activeTime", time)
        }
        inputForApi.jsonObject = jsonObject
        return inputForApi
    }

    fun updateOnline(inputApi: InputForAPI)   {
        logd(TAG, "Input_Request - URL" + (inputApi.url.toString()))
        logd(TAG, "Input_Request" + (inputApi.jsonObject.toString()))
        val gson = Gson()

        ApiCall.PostMethod(inputApi, object : ApiCall.ResponseHandler {
            override fun setDataResponse(response: JSONObject) {
                logd(TAG, " updateOnline Success $response")
            }

            override fun setResponseError(error: String) {
                logd(TAG, " updateOnline Error $error")
            }

        })
    }
}*/
