package com.app.applibrary.volley.apicall

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.util.*

class InputForAPI(var context: Context) {
    var jsonObject: JSONObject ?= null
    var url: String ?= null

    var headers: HashMap<String, String>? = HashMap()
    var file: File? = null



}
