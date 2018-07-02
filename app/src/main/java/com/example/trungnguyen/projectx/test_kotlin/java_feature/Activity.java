package com.example.trungnguyen.projectx.test_kotlin.java_feature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.trungnguyen.projectx.R;

import java.lang.reflect.Array;

import timber.log.Timber;

/**
 * Created by Trung Nguyen on 15-Jun-18.
 */

public class Activity extends AppCompatActivity{
    public static void main(String[] args) {
        String a = null;
        System.out.println(a.length());

        String b = " hhhhh";
        b = null;

        int arr[] = {-42, 17, 13, -9, 12};
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_kotlin);

        TextView textView =findViewById(R.id.tv_test);
        textView.setText("aaaaaaa");

        System.out.println(textView.getText());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        for(int i =0; i<10;){
            i=i++;
            Log.e("key",(i++)+"" ); //1
            i=++i;
            Log.e("key",(++i)+"" ); //1
            System.out.println("iii");
        }

        Boolean a = true;

        boolean aa = true;

        a = aa;

        v(2, "s");
    }

    void v(int i, String s){
        Timber.e(i + s);
    }
}
