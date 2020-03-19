package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("CUPEC5kpGMBerIl5wziyeLcVrTlTD9agUgx0vct3 ")
                //if defined
                .clientKey("JkosngASfZvu3E8quMQNjlXcDAhpZJOX52JkVM8H")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
