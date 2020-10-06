package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HandlerActivity extends AppCompatActivity {

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        List<Entity> list = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            Entity entity = new Entity(1, i + 1 + "");
            list.add(entity);
        }

        myHandler = new MyHandler(this);
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 5000000);
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
    class MyHandler extends Handler {
        public MyHandler(Activity activity) {
        }

        @Override
        public void handleMessage(Message msg) {

        }
    }

    static class Entity {
        private int id;
        private String title;

        public Entity(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }
}
