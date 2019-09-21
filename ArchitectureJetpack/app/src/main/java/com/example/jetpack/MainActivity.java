package com.example.jetpack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jetpack.lifecycle.PageLifecycleObserver;
import com.example.jetpack.livedata.LiveDataBus;
import com.example.jetpack.livedata.Person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLifecycle().addObserver(new PageLifecycleObserver());

        LiveDataBus.getInstance().with("person", Person.class)
                .observe(this, new Observer<Person>() {
                    @Override
                    public void onChanged(Person person) {
                        Log.d(TAG, "onChanged  " + person.getName());
                    }
                });
    }

    public void send(View view) {
        LiveDataBus.getInstance().with("person", Person.class)
                .postValue(new Person("jack"));
    }

    public void startPage(View view) {


        startActivity(new Intent(this, Main2Activity.class));
    }
}
