
package com.app.applibrary.volley.apicall

/*
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.app.applibrary.utilities.*
import com.educonnect.libraries.utils.logd
import com.educonnect.libraries.utils.openLogin
import com.sport.playsqorr.utilities.AppController
import com.sport.playsqorr.utilities.isNetworkConnected
import org.json.JSONObject
import java.io.*
import kotlin.math.min


open class FileUpload(
    method: Int,
    url: String,
    listener: Response.Listener<NetworkResponse>,
    errorListener: Response.ErrorListener
) : Request<NetworkResponse>(method, url, errorListener) {
    private var responseListener: Response.Listener<NetworkResponse>? = null

    init {
        this.responseListener = listener
    }
    private var headers: Map<String, String>? = null
    private val divider: String = "--"
    private val ending = "\r\n"
    private val boundary = "imageRequest${System.currentTimeMillis()}"

    override fun getHeaders(): MutableMap<String, String> =
        when (headers) {
            null -> super.getHeaders()
            else -> headers!!.toMutableMap()
        }

    override fun getBodyContentType() = "multipart/form-data;boundary=$boundary"

    @Throws(AuthFailureError::class)
    override fun getBody(): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val dataOutputStream = DataOutputStream(byteArrayOutputStream)
        try {
            if (params != null && params!!.isNotEmpty()) {
                processParams(dataOutputStream, params!!, paramsEncoding)
            }
            val data = getByteData() as? Map<String, FileDataPart>?
            if (data != null && data.isNotEmpty()) {
                processData(dataOutputStream, data)
            }
            dataOutputStream.writeBytes(divider + boundary + divider + ending)
            return byteArrayOutputStream.toByteArray()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return super.getBody()
    }

    @Throws(AuthFailureError::class)
    open fun getByteData(): Map<String, Any>? {
        return null
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<NetworkResponse> {
        return try {
            Response.success(response, HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: NetworkResponse) {
        responseListener?.onResponse(response)
    }

    override fun deliverError(error: VolleyError) {
        errorListener?.onErrorResponse(error)
    }

    @Throws(IOException::class)
    private fun processParams(
        dataOutputStream: DataOutputStream,
        params: Map<String, String>,
        encoding: String
    ) {
        try {
            params.forEach {
                dataOutputStream.writeBytes(divider + boundary + ending)
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"${it.key}\"$ending")
                dataOutputStream.writeBytes(ending)
                dataOutputStream.writeBytes(it.value + ending)
            }
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException(
                "Unsupported encoding not supported: $encoding with error: ${e.message}",
                e
            )
        }
    }

    @Throws(IOException::class)
    private fun processData(dataOutputStream: DataOutputStream, data: Map<String, FileDataPart>) {
        data.forEach {
            val dataFile = it.value
            dataOutputStream.writeBytes("$divider$boundary$ending")
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"${it.key}\"; filename=\"${dataFile.fileName}\"$ending")
            if (dataFile.type != null && dataFile.type.trim().isNotEmpty()) {
                dataOutputStream.writeBytes("Content-Type: ${dataFile.type}$ending")
            }
            dataOutputStream.writeBytes(ending)
            val fileInputStream = ByteArrayInputStream(dataFile.data)
            var bytesAvailable = fileInputStream.available()
            val maxBufferSize = 1024 * 1024
            var bufferSize = min(bytesAvailable, maxBufferSize)
            val buffer = ByteArray(bufferSize)
            var bytesRead = fileInputStream.read(buffer, 0, bufferSize)
            while (bytesRead > 0) {
                dataOutputStream.write(buffer, 0, bufferSize)
                bytesAvailable = fileInputStream.available()
                bufferSize = min(bytesAvailable, maxBufferSize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize)
            }
            dataOutputStream.writeBytes(ending)
        }
    }
}

class FileDataPart(var fileName: String?, var data: ByteArray, var type: String)

object ApiCall {

    private val TAG = "ApiCall"
    private val MY_SOCKET_TIMEOUT_MS = 60 * 1000


    fun PutMethod(input: InputForAPI, volleyCallback: ResponseHandler) {
        //        AppDataBase database = AppDataBase.getAppDatabase(input.getContext());
        val url = input.url
        val context = input.context
        val params = input.jsonObject
        val headers = input.headers

        if (isNetworkConnected(context)) {

            logd(TAG, "url:$url--input: $params--headers: $headers")
            val jsonObjReq = object : JsonObjectRequest(
                Request.Method.PUT,
                url, params,
                Response.Listener { response ->
                    logd(TAG, "url:$url,response: $response")
                    if (response.optBoolean("error") && (
                                (response.optInt("statusCode") == 401)
                                        ||
                                        (response.optString("message").equals(
                                           "Unauthorized User"
                                        )))
                    ) {
                        input.context.openLogin()
                    } else {
                        volleyCallback.setDataResponse(response)
                    }
                }, Response.ErrorListener { error ->
                    logd(TAG, "url:$url, onErrorResponse: $error")
                    if (error is TimeoutError || error is NoConnectionError) {

                        volleyCallback.setResponseError("No Internet Connection")

                    } else if (error is AuthFailureError) {

                        volleyCallback.setResponseError("Authentication Error")

                    } else if (error is ServerError) {

                        volleyCallback.setResponseError("server Error")

                    } else if (error is NetworkError) {

                        volleyCallback.setResponseError("Network Error")

                    } else if (error is ParseError) {

                        volleyCallback.setResponseError("Parse Error")

                    }
                }) {
                override fun getHeaders(): HashMap<String, String>? {
                    return headers
                }

                override fun getBody(): ByteArray {
                    Log.i("json", params.toString())
                    return params.toString().toByteArray(charset("UTF-8"))
                }

                override fun getBodyContentType(): String? {
                    return "application/json"
                }
            }

            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addrequestToQueue(jsonObjReq)
        } else {
            volleyCallback.setResponseError("No Internet Connection")
        }
    }

    fun DeleteMethod(input: InputForAPI, volleyCallback: ResponseHandler) {
        //        AppDataBase database = AppDataBase.getAppDatabase(input.getContext());
        val url = input.url
        val context = input.context
        val params = input.jsonObject
        val headers = input.headers
        if (isNetworkConnected(context)) {
            logd(TAG, "url:$url--input: $params--headers: $headers")
            val jsonObjReq = object : JsonObjectRequest(
                Request.Method.DELETE,
                url, params,
                Response.Listener { response ->
                    logd(TAG, "url:$url,response: $response")
                    if (response.optBoolean("error") && (
                                (response.optInt("statusCode") == 401)
                                        ||
                                        (response.optString("message").equals(
                                            "Unauthorized User"
                                        )))
                    ) {
                        input.context.openLogin()
                    } else {
                        volleyCallback.setDataResponse(response)
                    }
                }, Response.ErrorListener { error ->
                    logd(TAG, "url:$url, onErrorResponse: $error")
                    if (error is TimeoutError || error is NoConnectionError) {
                        volleyCallback.setResponseError("No Internet Connection")
                    } else if (error is AuthFailureError) {
                        volleyCallback.setResponseError("Authentication Error")
                    } else if (error is ServerError) {
                        volleyCallback.setResponseError("server Error")
                    } else if (error is NetworkError) {
                        volleyCallback.setResponseError("Network Error")
                    } else if (error is ParseError) {
                        volleyCallback.setResponseError("Parse Error")
                    }
                }) {
                override fun getHeaders(): HashMap<String, String>? {
                    return headers
                }
//                override fun getBody(): ByteArray {
//                    Log.i("json", params.toString())
//                    return params.toString().toByteArray(charset("UTF-8"))
//                }
//                override fun getBodyContentType(): String? {
//                    return "application/json"
//                }
            }
            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addCustomrequestToQueue(jsonObjReq)
        } else {
            volleyCallback.setResponseError("No Internet Connection")
        }
    }

    fun PostMethod(input: InputForAPI, volleyCallback: ResponseHandler) {
        //        AppDataBase database = AppDataBase.getAppDatabase(input.getContext());
        val url = input.url
        val context = input.context
        val params = input.jsonObject
        val headers = input.headers


        if (isNetworkConnected(context)) {

            logd(TAG, "url:$url--input: $params--headers: $headers")
            val jsonObjReq = object : JsonObjectRequest(
                Request.Method.POST,
                url, params,
                Response.Listener { response ->
                    logd(TAG, "url:$url,response: $response")
                    if (response.optBoolean("error") && (
                                (response.optInt("statusCode") == 401)
                                        ||
                                        (response.optString("message").equals(
                                            "Unauthorized User"
                                        )))
                    ) {
                        input.context.openLogin()
                    } else {
                        volleyCallback.setDataResponse(response)
                    }
                }, Response.ErrorListener { error ->
                    logd(TAG, "url:$url, onErrorResponse: $error")
                    if (error is TimeoutError || error is NoConnectionError) {

                        volleyCallback.setResponseError("No Internet Connection")

                    } else if (error is AuthFailureError) {

                        volleyCallback.setResponseError("Authentication Error")

                    } else if (error is ServerError) {

                        volleyCallback.setResponseError("server Error")

                    } else if (error is NetworkError) {

                        volleyCallback.setResponseError("Network Error")

                    } else if (error is ParseError) {

                        volleyCallback.setResponseError("Parse Error")

                    }
                }) {
                override fun getHeaders(): HashMap<String, String>? {
                    return headers
                }
            }

            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addrequestToQueue(jsonObjReq)
        } else {
            volleyCallback.setResponseError("No Internet Connection")
        }
    }


    fun GetMethod(input: InputForAPI, volleyCallback: ResponseHandler) {

        val url = input.url
        val context = input.context
        val headers = input.headers
        if (isNetworkConnected(context)) {

            val jsonObjReq = object : JsonObjectRequest(Request.Method.GET,
                url, null,
                Response.Listener
                { response ->
                    logd(TAG, "GetMethod url:$url,response: $response")

                    if (response.optBoolean("error") && (
                                (response.optInt("statusCode") == 401)
                                        ||
                                        (response.optString("message").equals(
                                            "Unauthorized User"
                                        )))
                    ) {
                        input.context.openLogin()
                    } else {
                        volleyCallback.setDataResponse(response)
                    }
                }, Response.ErrorListener { error ->
                    logd(TAG, "url:$url, onErrorResponse: $error")
                    if (error is TimeoutError || error is NoConnectionError) {

                        volleyCallback.setResponseError("No Internet Connection")

                    } else if (error is AuthFailureError) {

                        volleyCallback.setResponseError("Authentication Error")

                    } else if (error is ServerError) {

                        volleyCallback.setResponseError("Server Error")

                    } else if (error is NetworkError) {

                        volleyCallback.setResponseError("Network Error")

                    } else if (error is ParseError) {

                        volleyCallback.setResponseError("Parse Error")

                    }
                }) {
                override fun getHeaders(): HashMap<String, String>? {
                    if (headers != null) {
                        return headers
                    }
                    return null
                }
            }

            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addrequestToQueue(jsonObjReq)

        } else {
            volleyCallback.setResponseError("No Internet Connection")
        }

    }

    fun PostMethodStatusCode(input: InputForAPI, volleyCallback: ResponseHandler) {
        //        AppDataBase database = AppDataBase.getAppDatabase(input.getContext());
        val url = input.url
        val context = input.context
        val params = input.jsonObject
        val headers = input.headers


        if (isNetworkConnected(context)) {

            logd(TAG, "url:$url--input: $params--headers: $headers")
            val jsonObjReq = object : JsonObjectRequest(
                Request.Method.POST,
                url, params,
                Response.Listener { response ->
                    logd(TAG, "url:$url,response: $response")
                    if (response.optBoolean("error") && (
                                (response.optInt("statusCode") == 401)
                                        ||
                                        (response.optString("message").equals(
                                            "Unauthorized User"
                                        )))
                    ) {
                        input.context.openLogin()
                    } else {
                        volleyCallback.setDataResponse(response)
                    }
                }, Response.ErrorListener { error ->

                    var jsonObject = JSONObject()
                    logd(TAG, "url:$url, onErrorResponse: $error")
                    logd(TAG, "url:$url, onErrorResponse: ${error.networkResponse}")
                    var response = error.networkResponse

                    Log.d("responsestatus", response.statusCode.toString())
                    var statusCode = response.statusCode
                    if (error is TimeoutError || error is NoConnectionError) {
                        jsonObject.put(
                            "message",
                            "No Internet Connection"
                        )

                    } else if (error is AuthFailureError) {
                        jsonObject.put(
                            "message",
                            "Authentication Error"
                        )

                    } else if (error is ServerError) {
                        jsonObject.put(
                            "message",
                            "Server Error"
                        )

                    } else if (error is NetworkError) {
                        jsonObject.put(
                            "message",
                            "Network Error"
                        )

                    } else if (error is ParseError) {
                        jsonObject.put("message","Parse Error")
                    }
                    jsonObject.put("error", true)
                    jsonObject.put("statusCode", statusCode)
                    volleyCallback.setDataResponse(jsonObject)
                }) {
                override fun getHeaders(): HashMap<String, String>? {
                    return headers
                }
            }

            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addrequestToQueue(jsonObjReq)
        } else {
            volleyCallback.setResponseError("No Internet Connection")
        }
    }

    fun PostStringMethod(input: InputForAPI, volleyCallback: ResponseHandler) {
        //        AppDataBase database = AppDataBase.getAppDatabase(input.getContext());
        val url = input.url
        val context = input.context
        val params = input.stringParams
        val headers = input.headers


        if (isNetworkConnected(context)) {

            logd(TAG, "url:$url--input: $params--headers: $headers")
            val jsonObjReq = object : StringRequest(
                Request.Method.POST,
                url,
                Response.Listener<String> { response ->
                    logd(TAG, "url:$url,response: $response")
                    var jsonObject = JSONObject()
                    jsonObject.put("content", response.toString())
                    jsonObject.put("error", false)
                    jsonObject.put("statusCode", 200)
                    volleyCallback.setDataResponse(jsonObject)
                }, Response.ErrorListener { error ->
                    var jsonObject = JSONObject()
                    logd(TAG, "url:$url, onErrorResponse: $error")
                    logd(TAG, "url:$url, onErrorResponse: ${error.networkResponse}  ${error.networkResponse.statusCode}")
                    logd(TAG, "url:$url, onErrorResponse: $error ${error.networkResponse.statusCode}")
                    logd(TAG, "url:$url, onErrorResponse: ${error.networkResponse}")
                    var response = error.networkResponse

                    Log.d("responsestatus", response.statusCode.toString())
                    var statusCode = response.statusCode
                    jsonObject.put("statusCode",error.networkResponse.statusCode)
                    if (error is TimeoutError || error is NoConnectionError) {
                        jsonObject.put(
                            "message",
                            "No Internet Connection"
                        )
//                        volleyCallback.setResponseError("No Internet Connection")

                    } else if (error is AuthFailureError) {
                        jsonObject.put(
                            "message",
                            "Authentication Error"
                        )
//                        volleyCallback.setResponseError("Authentication Error")

                    } else if (error is ServerError) {
                        jsonObject.put(
                            "message",
                            "server Error"
                        )
//                        volleyCallback.setResponseError(context.resources.getString(R.string.server_error))

                    } else if (error is NetworkError) {
                        jsonObject.put(
                            "message",
                            "Network Error"
                        )
//                        volleyCallback.setResponseError("Network Error")

                    } else if (error is ParseError) {
//                        volleyCallback.setResponseError("Parse Error")
                        jsonObject.put("message", "Parse Error")
                    }



                    jsonObject.put("error", true)
                    jsonObject.put("statusCode", statusCode)
//                    jsonObject.put("statusCode", 500)
                    volleyCallback.setDataResponse(jsonObject)
                }) {
                override fun getHeaders(): Map<String, String> {
                    return headers!!
                }

                override fun getParams(): Map<String, String> {
                    return params!!
                }
            }

            jsonObjReq.retryPolicy = DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
            AppController.getInstance().addrequestToQueue(jsonObjReq)
        } else {
            volleyCallback.setResponseError("No Internet Connection")
        }
    }

    interface ResponseHandler {

        fun setDataResponse(response: JSONObject)

        fun setResponseError(error: String)

    }


}*/

