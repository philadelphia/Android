package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class HandlerActivity : AppCompatActivity() {
    private var myHandler: MyHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        val list: MutableList<Entity> = ArrayList()
        for (i in 0..399) {
            val entity = Entity(1,  "$(i+1)")
            list.add(entity)
        }
        myHandler = MyHandler(this)
        myHandler!!.postDelayed({ }, 5000000)
    }

    //    class MyHandler extends Handler {
    //        private WeakReference<Activity> activityWeakReference;
    //
    //        public MyHandler(Activity activity) {
    //            activityWeakReference = new WeakReference<>(activity);
    //        }
    //
    //        @Override
    //        public void handleMessage(Message msg) {
    //            if (activityWeakReference.get() != null) {
    //
    //            }
    //
    //        }
    //    }
    internal inner class MyHandler(activity: Activity?) : Handler() {
        override fun handleMessage(msg: Message) {}
    }

    internal class Entity(private val id: Int, private val title: String)

    override fun onDestroy() {
        super.onDestroy()
        myHandler!!.removeCallbacksAndMessages(null)
    }
}