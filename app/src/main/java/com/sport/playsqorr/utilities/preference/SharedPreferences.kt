package com.app.applibrary.utilities.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    fun putString(context: Context, Key: String, value: String) {
        sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        sharedPreferences.edit { putString(Key, value) }


    }

    fun getString(contextGetKey: Context, Key: String): String? {
        sharedPreferences = contextGetKey.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        return sharedPreferences.getString(Key, "")

    }

    fun putInt(context: Context, Key: String, value: Int) {
        sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        sharedPreferences.edit { putInt(Key, value) }


    }

    fun getInt(contextGetKey: Context, Key: String): Int {
        sharedPreferences = contextGetKey.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Key, 0)

    }

    fun putBoolean(context: Context, Key: String, value: Boolean) {
        sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        sharedPreferences.edit { putBoolean(Key, value) }

    }

    fun getBoolean(contextGetKey: Context, Key: String): Boolean {
        sharedPreferences = contextGetKey.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(Key, false)

    }

