package com.rewallution.utility

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 *  Change the status bar style
 *  @param activity : Pass the activity here.
 *  @param shouldChange : Pass true if your wants to change otherwise false.
 */
fun setUIDesign(activity: Activity, shouldChange: Boolean = false) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val decor = activity.window.decorView
        if (shouldChange) {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility = 0
        }
    }
}

/**
 * Check the string type variable whether it is null or not
 */
fun String?.checkString(): String {
    return when {
        this.isNullOrBlank() -> ""
        this.equals("Null", true) -> ""
        else -> this
    }
}

/**
 * Check the int type variable whether it is 0 or not
 */
fun Int?.checkInt(): Int {
    return when {
        (this == null) -> 0
        else -> this
    }
}