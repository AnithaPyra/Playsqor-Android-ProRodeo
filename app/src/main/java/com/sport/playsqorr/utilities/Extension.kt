package com.educonnect.libraries.utils


import android.animation.ValueAnimator
import android.app.Activity
import android.app.NotificationManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.URLSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.educonnect.libraries.listeners.SafeClickListener
import com.google.android.material.snackbar.Snackbar
import com.sport.playsqorr.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


var toast: Toast? = null


fun <T> Context.openActivityWithAnimation(it: Class<T>, extras: Bundle) {
    var intent = Intent(this, it)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, extras)
    } else {
        startActivity(intent)
    }
}

fun <T> Context.openActivity(it: Class<T>) {
    var intent = Intent(this, it)
    startActivity(intent)
}

fun <T> Context.openActivityWithBundle(it: Class<T>, bundle: Bundle) {
    var intent = Intent(this, it)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun <T> Context.openFreshActivity(it: Class<T>) {
    var intent = Intent(this, it)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}

fun <T> Context.openActivityWithClearTop(it: Class<T>) {
    var intent = Intent(this, it)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}

fun <T> Context.openActivityWithSingleTop(it: Class<T>) {
    var intent = Intent(this, it)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    startActivity(intent)
}

fun Context.openLogin() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_SINGLE_TOP)
    intent.component = ComponentName(
        "com.educonnect.suras",
        "com.educonnect.suras.usertype.ui.activity.SelectUserTypeActivity"
    )
    startActivity(intent)
}




//fun Context.openNoInternetConnection() {
//    val intent = Intent(Intent.ACTION_VIEW)
//    intent.component = ComponentName(
//        "com.app.europet.shopowner",
//        "com.app.europet.shopowner.modules.home.ui.activity.home.NoInternetConnectionActivity"
//    )
//    startActivity(intent)
//}

fun Any.clearAllNotifiation(context: Context) {
    val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notifManager.cancelAll()
}

fun Context.openUserNotification() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.component = ComponentName(
        "com.app.bazzifer",
        "com.app.user.mvvm.view.activity.UserNotificationsListActivity"
    )
    var bundle = Bundle()
    bundle.putString("action", "reload")
    intent.putExtras(bundle)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}


fun Context.openProviderNotification() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.component = ComponentName(
        "com.educonnect.suras",
        "com.app.provider.mvvm.view.activities.ProviderNotificationsListActivity"
    )
    var bundle = Bundle()
    bundle.putString("action", "reload")
    intent.putExtras(bundle)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}

fun Context.openTermsAndConditions() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.component = ComponentName(
        "com.app.bazzifer",
        "com.app.user.mvvm.view.activity.UserTermsAndConditionsActivity"
    )
    startActivity(intent)
}

fun Context.openContactUs(bundle: Bundle) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.component = ComponentName(
        "com.app.bazzifer",
        "com.app.user.mvvm.view.activity.ContactUsActivity"
    )
    intent.putExtras(bundle)
    startActivity(intent)
}

fun Context.openUserHomeActivity() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.component = ComponentName(
        "com.app.bazzifer",
        "com.app.user.mvvm.view.activity.UserHomeActivity"
    )
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}

fun Context.openProviderHomeActivity() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.component = ComponentName(
        "com.app.bazzifer",
        "com.app.provider.mvvm.view.activities.ProviderHomeActivity"
    )
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}


fun Context.openEmailIntent(emailId: String) {

    var mailto = "mailto:$emailId" //+ "?cc=" + "alice@example.com"

    var email = Intent(Intent.ACTION_SENDTO)
    email.data = Uri.parse(mailto)
    startActivity(Intent.createChooser(email, "Choose an Email client :"))

}

fun Context.openCallDailIntent(mobileNo: String) {

    var callIntent = Intent(Intent.ACTION_DIAL)
    callIntent.data = Uri.parse("tel:$mobileNo")
    startActivity(callIntent)

}

fun Context.openwebSiteIntent(website: String) {

    var browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$website"))
    startActivity(browserIntent)

}


fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    if (toast != null) {
        toast!!.cancel()
    }
    toast = Toast.makeText(context, this.toString(), duration).apply { show() }
    return toast!!
}

fun Any.cancelToast() {
    if (toast != null) {
        toast!!.cancel()
    }
}

fun Any.logd(tag: String, content: String) {
    Log.d(tag, content)
}

fun Any.showSnack(text: String, view: View){
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
}
fun Any.removeTitleBar(supportActionBar: ActionBar, window: Window) {

    if (supportActionBar != null)
        supportActionBar.hide()

}

fun Any.setPhoneStatusBarColor(color: Int, window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = color
    }
}

fun Any.setPhoneStatusBarIconColor(window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

    }
}


fun setStatusBarColor(activity: Activity, color: Int) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    activity.window.statusBarColor = color
//    }
}

fun setSystemBarTheme(pActivity: Activity, pIsDark: Boolean) {
    // Fetch the current flags.
    val lFlags = pActivity.window.decorView.systemUiVisibility
    // Update the SystemUiVisibility dependening on whether we want a Light or Dark theme.
//    pActivity.window.decorView.systemUiVisibility =
//        if (pIsDark) lFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() else lFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    if (pIsDark) {
        pActivity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pActivity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    } else {
        pActivity.window.decorView.systemUiVisibility = 0
    }
}


fun transperentStatusBar(activity: Activity) {
//    activity.window.decorView.systemUiVisibility =
//        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    activity.window.statusBarColor = Color.TRANSPARENT

}

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }


fun Any.setWindowFullScreen(window: Window) {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}


// Extension function to replace fragment
fun replaceFragment(
    supportFragmentManager: FragmentManager,
    fragment: Fragment,
    viewID: Int,
    tag: String,
    shouldAddToBackStack: Boolean
) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
//    transaction.setCustomAnimations(
//        R.anim.enter_from_right,
//        R.anim.exit_to_left,
//        R.anim.enter_from_left,
//        R.anim.exit_to_right
//    )
    transaction.replace(viewID, fragment, tag)
    transaction.addToBackStack(tag)
    transaction.commit()
}

// Extension function to replace fragment
fun AppCompatActivity.replaceFragmentWithEnterAnimation(
    fragment: Fragment,
    viewID: Int,
    tag: String,
    shouldAddToBackStack: Boolean
) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
//    transaction.setCustomAnimations(
//        R.anim.enter_from_right,
//        R.anim.exit_to_left,
//        R.anim.enter_from_right,
//        R.anim.exit_to_left
//    )
    transaction.replace(viewID, fragment, tag)
//    if(shouldAddToBackStack){
    transaction.addToBackStack(tag)
//    }else{
//        transaction.addToBackStack(null)
//    }
    transaction.commit()
}

// Extension function to replace fragment
fun addFragment(
    supportFragmentManager: FragmentManager,
    fragment: Fragment,
    viewID: Int,
    tag: String,
    needAnimation: Boolean
) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    if (needAnimation) {
//        transaction.setCustomAnimations(
//            R.anim.enter_from_right,
//            R.anim.exit_to_left,
//            R.anim.enter_from_right,
//            R.anim.exit_to_left
//        )
    }

    transaction.replace(viewID, fragment, tag)
//    transaction.addToBackStack(tag)
    transaction.commit()
}

// Extension function to replace fragment
fun removeFragment(supportFragmentManager: FragmentManager, fragment: Fragment): Int {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.remove(fragment)
    return transaction.commit()
}

fun getCurrentFragment(supportFragmentManager: FragmentManager, viewID: Int): Fragment? {
    var currentFragment = supportFragmentManager
        .findFragmentById(viewID)
    return currentFragment
}

fun AppCompatActivity.getCurrentVisibleFragmentID(): Fragment? {
    for (fragment in supportFragmentManager.fragments) {
        if (fragment.isVisible) {
            logd("Extension", "getCurrentVisibleFragmentID" + fragment.tag)
            return fragment
        }
    }
    return null
}

fun AppCompatActivity.getFragmentByTag(tag: String): Fragment? {
    for (fragment in supportFragmentManager.fragments) {
        if (fragment.tag?.equals(tag)!!) {
            logd("Extension", "getFragmentByTag" + fragment.tag)
            return fragment
        }
    }
    return null
}

fun clearPopBackstack(supportFragmentManager: FragmentManager) {
    var fm = supportFragmentManager
    for (i in 0..supportFragmentManager.backStackEntryCount - 1) {
        fm.popBackStackImmediate()
    }
}


fun Any.enableViews(isEnable: Boolean, viewGroup: ViewGroup) {
    for (i in 0 until viewGroup.childCount) {
        val child = viewGroup.getChildAt(i)
        child.isEnabled = isEnable
        if (child is ViewGroup) {
            enableViews(isEnable, child)
        }
    }
}

/*fun Any.hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}*/
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
fun Any.getDateFromMilli(milliSeconds: Long, dateFormat: String): String {
    val formatter = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}



fun Any.openDialPad(content: Activity, mobileNum: String) {
    val callIntent = Intent(Intent.ACTION_DIAL)
    callIntent.data = Uri.parse("tel:" + mobileNum)
    content.startActivity(callIntent)

}

fun isSDCARDMounted(): Boolean {
    val status = Environment.getExternalStorageState()
    return status == Environment.MEDIA_MOUNTED
}

fun Any.openYoutubeIntent(url: String, context: Context) {
    var intent = Intent()
    var url2 = url
    while (url2.contains("\\/")) {
        url2 = url2.replace("\\/", "/")
    }
    logd("YouTube", url2)
    try {
        intent = Intent(Intent.ACTION_VIEW)
        if (appInstalledOrNot("com.google.android.youtube", context)) {
            intent.setPackage("com.google.android.youtube")
        }

        intent.data = Uri.parse(url2)
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        "Invalid Url".toast(context, 5 * 1000)
    }
}

fun Any.openFacebookIntent(url: String, context: Context) {
    var url2 = url
    while (url2.contains("\\/")) {
        url2 = url2.replace("\\/", "/")
    }
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url2))
        context.startActivity(intent)
    } catch (e: Exception) {
        "Invalid Url".toast(context, 5 * 1000)
    }
}


fun Any.openContactUsFacebook(url: String, context: Context) {
    var url2 = url
    while (url2.contains("\\/")) {
        url2 = url2.replace("\\/", "/")
    }

    try {

        var intent = Intent(Intent.ACTION_VIEW)
        if (appInstalledOrNot("com.facebook.katana", context)) {
            intent.setPackage("com.facebook.katana")
            url2 = "fb://facewebmodal/f?href=" + url2
        }
        intent.data = Uri.parse(url2)
        context.startActivity(intent)
    } catch (e: Exception) {
        openFacebookIntent(url, context)
        "Invalid Url".toast(context, 5 * 1000)
    }
}


fun Any.openInstagramIntent(url: String, context: Context) {
    var url2 = url
    while (url2.contains("\\/")) {
        url2 = url2.replace("\\/", "/")
    }
    logd("Insta", url2)
    var intent = Intent()
    try {
        intent = Intent(Intent.ACTION_VIEW)
        if (appInstalledOrNot("com.instagram.android", context)) {
            intent.setPackage("com.instagram.android")
        }

        intent.data = Uri.parse(url2)
        context.startActivity(intent)
    } catch (e: java.lang.Exception) {
        "Invalid Url".toast(context, 5 * 1000)
    }
}

fun Any.openLinkedinIntent(url: String, context: Context) {
    var url2 = url
    while (url2.contains("\\/")) {
        url2 = url2.replace("\\/", "/")
    }
    var intent = Intent()
    try {
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url2)
        context.startActivity(intent)
    } catch (e: java.lang.Exception) {
        "Invalid Url".toast(context, 5 * 1000)
    }
}

fun Any.openWhatsappIntent(number: String, context: Context) {

    try {
        if (!number.isNullOrBlank()) {
            var whatsappNumber: String = number.trim()
            if (!whatsappNumber.startsWith("+65")) {
                whatsappNumber = "+65" + whatsappNumber
            }
            val url = "https://api.whatsapp.com/send?phone=" + whatsappNumber
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            context.startActivity(i)
        }
    } catch (e: java.lang.Exception) {
        "Invalid Url".toast(context, 5 * 1000)
    }

}

private fun appInstalledOrNot(uri: String, context: Context): Boolean {
    var packageManager: PackageManager = context.packageManager
    try {
        packageManager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
        return true
    } catch (exception: PackageManager.NameNotFoundException) {
    }

    return false
}


fun saveIdCardImageToLocal(folderName: String, imageName: String, layout: RelativeLayout): String {
    var myIdCardPath = ""
    if (isSDCARDMounted()) {
        val mediaStorageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            folderName
        )
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
//                logd("Failed", "Failed to create directory")
            }
        }
        myIdCardPath = mediaStorageDir.path + File.separator + imageName

        val file = File(myIdCardPath)
        try {
            val out = FileOutputStream(file)
            if (layout != null) {
                layout.isDrawingCacheEnabled = true
                layout.drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    return myIdCardPath
}


fun Any.slashedTxtView(content: Activity, txtView: TextView) {
    txtView.paintFlags = txtView.paintFlags
    txtView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

}


fun Any.getDefaultDigit(): String {
    return "**** **** **** "
}

var lastClickTime = 0
fun singleButtonClick(): Boolean {
    if (SystemClock.elapsedRealtime() - lastClickTime < 400) {
        return false
    }

    lastClickTime = SystemClock.elapsedRealtime().toInt()
    return true
}


fun Context.openShareIntent(context: Context, data: String) {
    val whatsappIntent = Intent(Intent.ACTION_SEND)
    whatsappIntent.putExtra(Intent.EXTRA_TEXT, data)
    whatsappIntent.type = "text/plain"
    try {
        context.startActivity(whatsappIntent)
    } catch (e: java.lang.Exception) {
        //ToastHelper.MakeShortText("Whatsapp have not been installed.");
    }

}


fun replacebracket(str: String): String {
    var str = str
    str = str.replace("\\[|\\]".toRegex(), "")
    return str
}

fun Any.setSpan(view: TextView, text: String, textColor: Int) {
    val subscribeSpan = SpannableString(text)
    subscribeSpan.setSpan(
        ForegroundColorSpan(textColor),
        text.length - 1,
        text.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    view.text = subscribeSpan
}


fun Any.setHintSpan(view: EditText, text: String, textColor: Int) {
    val subscribeSpan = SpannableString(text)
    subscribeSpan.setSpan(
        ForegroundColorSpan(textColor),
        text.length - 1,
        text.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    view.hint = subscribeSpan
}

fun setFilters(length: Int): Array<InputFilter> {
    var filter: InputFilter = InputFilter.LengthFilter(length)
    var filterArray = arrayOf(filter)
    filterArray[0] = filter
    return filterArray
}

fun isGPSEnabled(mContext: Context): Boolean {
    val manager: LocationManager =
        mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        return true
    }
    return false
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)


fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Int.toPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val px = this * (metrics.densityDpi / 160f)
    return Math.round(px)
}


fun animateText(view: TextView, text: String) {
    val animator = ValueAnimator()
    animator.setObjectValues(0, 100)
    animator.addUpdateListener {
        view.setText(text)
    }
    animator.setDuration(10000) // here you set the duration of the anim

    animator.start()
}

fun inFromRightAnimation(): Animation? {
    val inFromRight: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, +1.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f
    )
    inFromRight.setDuration(500)
    inFromRight.setInterpolator(AccelerateInterpolator())
    return inFromRight
}

fun outToLeftAnimation(): Animation? {
    val outtoLeft: Animation = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, -1.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f
    )
    outtoLeft.duration = 500
    outtoLeft.interpolator = AccelerateInterpolator()
    return outtoLeft
}


fun showKeyboard(mContext: Context) {
    val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}


fun doubleRemovingTrailingZeroDecimal(number: Double, needDecimal: Boolean = false): String {
    val df = DecimalFormat("###.##")
    val value = df.format(number)
    if (needDecimal || value.contains(".")) {
        //to return number with only two decimal values
        return String.format("%.2f", number)
    }
    //Return whole number if the decimal places are 0
    return (df.format(number))
}

fun getOneTrailingZeroDecimal(number: Double, needDecimal: Boolean = false): String {
    val df = DecimalFormat("###.#")
    val value = df.format(number)
    if (needDecimal || value.contains(".")) {
        //to return number with only two decimal values
        return String.format("%.1f", number)
    }
    //Return whole number if the decimal places are 0
    return (df.format(number))
}

fun displayPriceAmountWithComma(value: Double, localUS: Boolean): String {
    if (localUS) {
        val formatter = DecimalFormat("#,###,###.##")
        return formatter.format(value)
    } else {
        val formatter = DecimalFormat("#,##,###.##")
        return formatter.format(value)
    }
}


fun appendStrings(vararg text: String) : String{
    var finalString = ""
    for(string in text){
        finalString.plus(string)
    }
    return finalString
}



fun checkIsNumber(text: String) : Boolean{
    if(TextUtils.isDigitsOnly(text))
    {
        return true
    }
    return false
}

fun getAlphabetValue(character: Char): Int {
    return character.toInt() - 'a'.toInt()+1
}

fun Drawable.tint(context: Context, @ColorRes color: Int) {
    DrawableCompat.setTint(this, context.resources.getColor(color, context.theme))
}

fun getMimeType(url: String?): String? {
    var type: String? = null
    val extension: String = MimeTypeMap.getFileExtensionFromUrl(url)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type
}




fun checkTextContainsURL(text: String) : Boolean{
    val URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$"

    val p: Pattern = Pattern.compile(
        URL_REGEX,
        Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL
    )
    val m: Matcher = p.matcher("example.com") //replace with string to compare

    if (m.find()) {
        return true
    }
    return false
}


fun getTempFile(context: Context, url: String): File? {
    var file: File ?= null
    try {
        val fileName = Uri.parse(url).lastPathSegment
        file = File.createTempFile(
            fileName, null,
            context.cacheDir
        )
    } catch (e: IOException) {
        // Error while creating file
    }
    return file
}