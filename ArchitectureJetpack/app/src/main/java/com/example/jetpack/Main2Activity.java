package com.example.jetpack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jetpack.livedata.LiveDataBus;
import com.example.jetpack.livedata.Person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //当在执行跳转以后， 发生了一个很神奇的事情，就是 Main2Activity onChanged 被神奇的调用了
        // 原因是什么呢 ？  愿意在于observe 里面调用了 addObserver 这个方法是activity身上的 LifecycleRegistry addObserver
        // 他会造成 LifecycleBoundObserver onStateChanged 最后导致 activeStateChanged(shouldBeActive());
        // 默认mLastVersion == -1  但是 mVersion属于LiveData 所以它只要有一次postValue 它就不等于-1
        // 最后就会执行onChanged
        LiveDataBus.getInstance().with("person", Person.class)
                .observe(this, new Observer<Person>() {
                    @Override
                    public void onChanged(Person person) {
                        Log.d(TAG, "onChanged  " + person.getName());
                    }
                });
    }

    public void startPage(View view) {

    }

    public void send(View view) {
        LiveDataBus.getInstance().with("person", Person.class)
                .postValue(new Person("Demon"));
    }
}
