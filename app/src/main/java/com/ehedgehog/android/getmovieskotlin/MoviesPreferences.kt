package com.ehedgehog.android.getmovieskotlin

import android.content.Context

class MoviesPreferences {
    companion object {

        private const val FILE_NAME = "prefs"
        private const val PREF_QUERY = "query"

        fun getStoredQuery(context: Context): String? {
            return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                .getString(PREF_QUERY, "")
        }

        fun setStoredQuery(context: Context, query: String) {
            context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_QUERY, query)
                .apply()
        }
    }
}