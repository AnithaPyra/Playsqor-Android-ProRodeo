package com.sport.playsqorr.utilities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.text.InputFilter
import android.util.Patterns
import java.net.URL
import java.util.regex.Pattern


fun isNetworkConnected(context: Context?): Boolean {
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw      = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {

            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}

fun isNetworkConnectedOld(context: Context?): Boolean {
    val isConnected: Boolean
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetInfo = connectivityManager
        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    val activeWIFIInfo = connectivityManager
        .getNetworkInfo(ConnectivityManager.TYPE_WIFI)

    isConnected = activeWIFIInfo!!.isConnected || activeNetInfo!!.isConnected
    return isConnected
}


fun isValidMobile(phone: String): Boolean {
    var check = false
    if (phone.isNullOrBlank() && Patterns.PHONE.matcher(phone.trim()).matches()) {
       check = true
    }
    return check

}

fun isValidName(text: String): Boolean {
    var check = false
    if (text != null && text.isNotBlank() && Pattern.matches("[a-zA-Z ]+", text)) {
        check = true
    }
    return check
}

var EMOJI_FILTER =
    InputFilter { source, start, end, dest, dstart, dend ->
        for (index in start until end) {
            val type = Character.getType(source[index])
            if (type == Character.SURROGATE.toInt()) {
                return@InputFilter ""
            }
        }
        null
    }


fun isValidCardName(text: String): Boolean {
    var check = false
    if (text != null && text.isNotBlank() && Pattern.matches("[a-zA-Z ]+", text)) {
        check = true
    }
    return check
}


fun isValidEmail(text: String): Boolean {
    var check = false
    if (text != null && text.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(text.trim()).matches()) {
       check = true
    }
    return check
}


fun isValidURL(text: String): Boolean {
    if (text != null && text.isNotBlank()) {
        return try {
            URL(text).toURI()
            true
        }
        catch (e: Exception) {
            false
        }
    }
    return false
}


fun Any.checkTextLengthEquals(text: String, maxLength: Int): Boolean {
    if (text != null && text.isNotBlank() && text.trim().length == maxLength) {
        return true
    }
    return false
}

fun Any.checkTextLengthMatchesRange(text: String, minLength: Int = 1, maxLength: Int): Boolean {
    if (text != null && text.isNotBlank() && (text.length <= maxLength && text.length >= minLength)) {
        return true
    }
    return false
}

fun checkPasswordMatches(currentPassword: String, confirmPassword: String): Boolean {
    if (currentPassword.equals(confirmPassword, false)) {
        return true
    }
    return false
}



//fun isValidPassword(password: String): String {
//    var check = "true"
//    if (password.length==0)
//    {
//        return AppController.getInstance()
//            .applicationContext.resources.getString(R.string.password_length_should_be_six_characters)
//    } else
//        if (password.length < 6) {
//            return AppController.getInstance()
//                .applicationContext.resources.getString(R.string.password_length_should_be_six_characters)
//        } else if (password.length > 15) {
//            return AppController.getInstance()
//                .applicationContext.resources.getString(R.string.password_length_should_not_exceed_max_characters)
//
//        } else{
//            return check
//        }
//
//

fun openVideoIntent(uri: Uri) : Intent? {
    //Uri uri = Uri.parse(uripath);
    if(uri != null) {
        val intent = Intent(Intent.ACTION_VIEW)
        val url = uri.toString()
        // Video files
        intent.setDataAndType(uri, "video/*")
        return intent
    }
    return null
}

fun isYoutubeUrl(url: String): Boolean {
    val success: Boolean
    val pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+"
    success = !url.isEmpty() && Pattern.matches(pattern, url)
    return success
}
