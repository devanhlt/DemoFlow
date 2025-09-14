package net.fastboypay.demoflow.data.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "demo_flow_prefs"
        private const val KEY_IS_FULL = "is_full"
    }
    
    var isFull: Boolean
        get() = prefs.getBoolean(KEY_IS_FULL, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_FULL, value).apply()
}
