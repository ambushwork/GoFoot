package com.netatmo.ylu.gofoot

import android.app.ActivityManager
import android.app.Application
import android.util.Log
import com.netatmo.ylu.gofoot.repository.TeamRepository
import com.netatmo.ylu.gofoot.room.GoFootRoomDatabase
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso


class GoFootApplication : Application() {

    //FIXME: use hilt injection
    val database by lazy { GoFootRoomDatabase.getDatabase(this) }
    val repository by lazy { TeamRepository(database.teamDao()) }

    override fun onCreate() {
        super.onCreate()
        Picasso.setSingletonInstance(getCustomPicasso())
    }

    private fun getCustomPicasso(): Picasso {
        return Picasso.Builder(this).apply {
            memoryCache(LruCache(getBytesForMemCache(12)))
            val transformer = Picasso.RequestTransformer { request ->
                Log.d("image request", request.toString())
                request
            }
            requestTransformer(transformer)
        }.build()
    }

    //returns the given percentage of available memory in bytes
    private fun getBytesForMemCache(percent: Int): Int {
        val mi = ActivityManager.MemoryInfo()
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(mi)
        val availableMemory = mi.availMem.toDouble()
        return (percent * availableMemory / 100).toInt()
    }

}