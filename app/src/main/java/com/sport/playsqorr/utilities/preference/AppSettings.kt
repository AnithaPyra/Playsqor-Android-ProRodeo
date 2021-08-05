package com.app.applibrary.utilities.preference

import android.content.Context
import com.app.europet.app_libraries.appsettings.SharedConstants
import com.sport.playsqorr.tokensecurity.EncryptionUtils

class AppSettings(var context: Context?) {

    internal var accessToken: String? = ""


    companion object {
        var appSetting: AppSettings? = null
        fun getInstanceOf(mContext: Context?): AppSettings {
            if (appSetting == null) {
                appSetting = AppSettings(mContext)
            }
            return appSetting!!
        }
    }


    fun getAccessToken(): String {
        accessToken = EncryptionUtils.decrypt(context, getString(context!!, SharedConstants.accessToken))
//        accessToken =
//            "jwt eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTAsIm1vYmlsZU51bWJlciI6Ijg4ODg4MDAwMDAiLCJ1c2VyVHlwZSI6IlNUVURFTlQiLCJpYXQiOjE2MTg5MTkxNTh9.6bvte_9Pqg4g5Yr133KJMoTtJ3qpB9JWOg8ygF1LXN8"
        return accessToken!!
    }

    fun setAccessToken(accessToken: String) {
        val encryptedValue = EncryptionUtils.encrypt(context, "jwt $accessToken")
        putString(context!!, SharedConstants.accessToken, encryptedValue)
        this.accessToken = "jwt $accessToken"
    }







}