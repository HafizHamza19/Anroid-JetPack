package com.bmcsolution.jetpacklearning

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.bmcsolution.jetpacklearning.Architecture.workManager.NotificationWorker
import java.util.concurrent.TimeUnit

class ApplicationClass: Application(){
    override fun onCreate() {
        super.onCreate()
        setupNoficationManger()

    }

    private fun setupNoficationManger() {
        val constraint=Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
       val workerRequest= PeriodicWorkRequest.Builder(NotificationWorker::class.java,1, TimeUnit.SECONDS).setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }
}