package com.example.ioc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inject.InjectUtils;
import com.example.inject.annotation.ContentView;
import com.example.inject.annotation.InjectClick;
import com.example.inject.annotation.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_01)
    Button btn_01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        InjectUtils.init(this);

    }

    @InjectClick({R.id.btn_01})
    private void onTestClick(View view){
        Toast.makeText(MainActivity.this, "xsssssss", Toast.LENGTH_LONG).show();
    }
}
