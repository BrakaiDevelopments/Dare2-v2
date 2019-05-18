/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.dare2

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.crashlytics.android.Crashlytics
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import gr.brakaidevelopments.data.dataRepositoryModule
import gr.brakaidevelopments.data.databaseModule
import gr.brakaidevelopments.data.retrofitModule
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())

        Logger.addLogAdapter(AndroidLogAdapter())

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@Application)
            modules(appModule, retrofitModule, databaseModule, dataRepositoryModule)
        }

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(getString(R.string.CHANNEL_ID), name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}