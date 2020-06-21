package com.example.languagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAppLanguage(Locale.SIMPLIFIED_CHINESE);
            }
        });

    }

    private void initData() {
        //调完这个之后，文字就会变成你设置的其他语言了
        ((TextView)findViewById(R.id.text1)).setText(getResources().getString(R.string.search_pathogens));
        ((TextView)findViewById(R.id.text2)).setText(getResources().getString(R.string.pathogens));
    }

    public void changeAppLanguage(Locale locale) {
        //得到显示指示
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //获取设置对象
        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        //更新显示的语言
        getResources().updateConfiguration(configuration, metrics);
        //重新启动Activity
        Intent intent = new Intent(this, MainActivity.class);
        //用于开始到达新的Activity之前移除之前的Activity。这样我们点击back键就会直接回桌面了
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



}
