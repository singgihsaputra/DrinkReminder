package id.singgih.engineer.drinkreminder.feature.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by singgihrs on 9/28/17.
 */

class DataPreferences(context: Context) {

    private val PREFS_DRINK_REMINDER = DataPreferences::class.java.canonicalName as String

    private val DRINKING_TIMES = "DRINKING_TIMES"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_DRINK_REMINDER, 0)

    var drinkingTimes: Int
        get() = prefs.getInt(DRINKING_TIMES, 0)
        set(drinkingTimes) = prefs.edit().putInt(DRINKING_TIMES, drinkingTimes).apply()
}