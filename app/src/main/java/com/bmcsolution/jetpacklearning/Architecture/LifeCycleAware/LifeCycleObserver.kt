package com.bmcsolution.jetpacklearning.Architecture.LifeCycleAware

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class LifeCycleObserver: LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event)
    {
        when(event)
        {
            Lifecycle.Event.ON_CREATE-> Log.d("tag","Observer ON_CREATE")
            Lifecycle.Event.ON_DESTROY-> Log.d("tag","Observer ON_DESTROY")
            Lifecycle.Event.ON_PAUSE-> Log.d("tag","Observer ON_PAUSE")
            Lifecycle.Event.ON_RESUME-> Log.d("tag","Observer ON_RESUME")
            Lifecycle.Event.ON_START ->  Log.d("tag","Observer ON_START")
            Lifecycle.Event.ON_STOP ->  Log.d("tag","Observer ON_STOP")
            Lifecycle.Event.ON_ANY ->  Log.d("tag","Observer ON_ANY")
        }
    }
}