package com.example.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dagger.di.DaggerMyDataComponent;
import com.example.dagger.di.DataBaseObjectModule;
import com.example.dagger.di.HttpModule;
import com.example.dagger.entity.DataBaseObject;
import com.example.dagger.entity.HttpObject;
import com.example.dagger.presenter.DaggerPresenterComponent;
import com.example.dagger.presenter.Presenter;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    HttpObject mHttpObject1;

    @Inject
    Presenter mPresenter;

    @Inject
    DataBaseObject mDataBaseObject;

    /*@BindView(R.id.txt_hello)
        TextView txt_hello;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMyDataComponent.builder().httpModule(new HttpModule("nnnnn"))
                .dataBaseObjectModule(new DataBaseObjectModule())
                .presenterComponent(DaggerPresenterComponent.create())
                .build()
                .injectMainActivity(this);
        Log.d(TAG, "MainActivity == " + mHttpObject1.hashCode());
        Log.d(TAG, "MainActivity == " + mPresenter.hashCode());
        Log.d(TAG, "MainActivity == " + mDataBaseObject.hashCode());
    }

}
