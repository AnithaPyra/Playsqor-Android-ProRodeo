package com.app.applibrary.volley

import android.content.Context
import com.app.applibrary.utilities.preference.AppSettings
import com.app.libraries.volley.constants.ConstantKeys
import java.util.*


fun getContentTypeHeader(context: Context): HashMap<String, String> {

    val headers = HashMap<String, String>()
//   headers.put(NetworkCallConstants.kContentType, NetworkCallConstants.kContentTypeValFormData)
    headers.put(ConstantKeys.kContentType, ConstantKeys.kContentTypeValJSON)

    return headers
}



fun getAuthorizationHeaderTwo(context: Context): HashMap<String, String> {
    val header = HashMap<String, String>()
    header.put(ConstantKeys.kAuthorizationKey, AppSettings(context).getAccessToken())
    header.put(ConstantKeys.kContentType, ConstantKeys.kContentTypeValJSON)
    //header.put("accept","*/*")
    return header
}

