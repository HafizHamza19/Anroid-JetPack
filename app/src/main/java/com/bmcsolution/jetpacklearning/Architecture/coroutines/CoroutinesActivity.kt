package com.bmcsolution.jetpacklearning.Architecture.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bmcsolution.jetpacklearning.R
import com.bmcsolution.jetpacklearning.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class CoroutinesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoroutinesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutines)
        //setContentView(R.layout.activity_coroutines)
        var cout: Int = 0
        binding.updateCounter.setOnClickListener {
            cout++
            binding.counter.text = cout.toString()
        }
        binding.longTask.setOnClickListener {

            //this thread is OS level thread or ye limited hote hai ye space bhi ziyada lete hai approx 2Mb ek thread leta hai
            //this is background thread-- depends on system how many threads can make according to space
            thread(start = true) {
                executeLongTask()
            }
            //Dispatchers Coroutine ko dispatch krte hai thread pr
            //1. for IO operation Dispatchers.IO
            //2. for main Dispatchers.Main
            //3. for default Dispatchers.Default

            //CoroutineScope define lifetime of coroutines
            //CoroutineContext define k konse thread pr work krna hai e.g IO/Main/Default
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("Thread Name", Thread.currentThread().name)
            }
            //full application k sath join hota hai
            GlobalScope.launch(Dispatchers.Main) {
                Log.d("Thread Name", Thread.currentThread().name)
            }
            //ye hamari activity k sath join hota hai
            MainScope().launch(Dispatchers.Default) {
                Log.d("Thread Name", Thread.currentThread().name)
            }

        }

        CoroutineScope(Dispatchers.Main).launch { task1() }
        CoroutineScope(Dispatchers.Main).launch { task2() }
        CoroutineScope(Dispatchers.IO).launch { printThroughLaunch() }
        CoroutineScope(Dispatchers.Main).launch { coroutineJobsCancellation() }

        CoroutineScope(Dispatchers.Main).launch { coroutinesWithContext() }
        coroutinesRunBlocking()
    }

    private fun executeLongTask() {
        for (i in 0..100000L) {
            Log.d("Tag", i.toString())
        }
    }


    //suspend function coroutines ko suspend krne mai help krte hai koi particular point pr
    //jese hi wo suspend wale point pr pohchega tw hamare thread ko 2sre work pr lagadega and
    // then next will run on same thread
    suspend fun task1() {
        Log.d("Tag", "Task 1 Starting Point")
        yield()
        //or
        //delay(1000)
        Log.d("Tag", "Task 1 Ending Point")
    }

    suspend fun task2() {
        Log.d("Tag", "Task 2 Starting Point")
        yield()
        //or
        //delay(1000)
        Log.d("Tag", "Task 2 Ending Point")
    }


    suspend fun printThroughLaunch() {
        var follower = 0;

        //TODO Launch -> when you do not care about result. (Fire and Forget)
        CoroutineScope(Dispatchers.IO).launch {
            follower = getFollower()
        }
        Log.d("Tag before job.join", follower.toString())


        //TODO this will also work like async await but this return type always job
        val job = CoroutineScope(Dispatchers.IO).launch {
            follower = getFollower()
        }
        job.join()
        Log.d("Tag after job.join", follower.toString())


        //TODO When you care about result/output from your coroutines
        //TODO this will return with respect to your return type ( Deferred object return jo future mai ja kr resolve hoga)
        val sync = CoroutineScope(Dispatchers.IO).async {
            getFollower()
        }
        Log.d("Tag Sync", sync.await().toString())


        //Parent coroutines
        CoroutineScope(Dispatchers.IO).launch {
            //Child Coroutines
            val fb = async { getFollower() }
            val insta = async { getInstaFollower() }
            Log.d("Tag", "Fb - ${fb.await()} Insta - ${insta.await()}")
        }


    }


    private suspend fun getFollower(): Int {
        delay(1000)
        return 54
    }

    private suspend fun getInstaFollower(): Int {
        delay(1000)
        return 100
    }


    private suspend fun coroutineJobsCancellation() {

        //Job Cancellation
        var parentJob = CoroutineScope(Dispatchers.Main).launch {

            Log.d("TagJob", "$coroutineContext")
            Log.d("TagJob", "Parent job Start")

            var childjob = launch(Dispatchers.IO) {


                try {
                    Log.d("TagJob", "$coroutineContext")
                    Log.d("TagJob", "Child job Start")
                    delay(5000)
                    Log.d("TagJob", "Child job End")
                } catch (e: CancellationException) {
                    Log.d("TagJob", "Child job Canceled ${e.message}")

                }
            }

            delay(3000)
            Log.d("TagJob", "Child job Canceled")
            childjob.cancel()
            Log.d("TagJob", "Parent job End")
        }
        parentJob.join()
        Log.d("TagJob", "Parent job Completed")


        val job = CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..1000) {
                //TODO job cancel k abd bhi ye run ho rha tha q k ye loop busy tha already thats why we check it Active state
                if(isActive) {
                    Log.d("TagJob", i.toString())
                    executingLongTask()
                }
            }
        }
        delay(100)
        Log.d("TagJob", "job Cancelling")
        job.cancel()
        job.join()
        Log.d("TagJob", "job Completed")


    }


    private fun executingLongTask() {
        for (i in 0..100000) {
        }
    }



    private suspend fun coroutinesWithContext()
    {
        //this coroutine is blocking nature first complete the task inside the coroutines then run next task
       //Coroutines mai context switching k liye e.g IO opertaion se API hit ki or data fetch kiya ab UI pr set krwana hai tw wo
        //Main thread pr hota hai phir hum IO se Main thread pr shift ho kr kaam krege
        withContext(Dispatchers.IO){
            Log.d("Tag CoroutinesContext","Before")
            delay(1000)
            Log.d("Tag CoroutinesContext","Inside")
        }
        Log.d("Tag CoroutinesContext","After")
    }



    private fun  coroutinesRunBlocking()= runBlocking {
        //jb tk hamare coroutines complete nhi hote run blocking hamare thread ko rok kr rkhta hai
        launch {
            delay(1000)
            Log.d("Tag runBlocking","Run")
        }
        Log.d("Tag runBlocking","Finish")

    }


}