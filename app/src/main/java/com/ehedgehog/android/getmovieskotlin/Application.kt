package com.ehedgehog.android.getmovieskotlin

import android.app.Application
import com.ehedgehog.android.getmovieskotlin.di.AppComponent
import com.ehedgehog.android.getmovieskotlin.di.DaggerAppComponent
import com.ehedgehog.android.getmovieskotlin.di.DataModule
import io.realm.Realm

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

    companion object {
        val appComponent: AppComponent =
            DaggerAppComponent.builder()
                .dataModule(DataModule())
                .build()
    }

}