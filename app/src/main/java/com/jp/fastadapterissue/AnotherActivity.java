package com.jp.fastadapterissue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class AnotherActivity extends AppCompatActivity {
    public static int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

    }

    /**
     * add an item to main fragment
     * @param view
     */
    public void addClick(View view) {
        /**
         * {@link MainActivityFragment#addItem(String)}
         */
        i++;
        EventBus.getDefault().post(i + "");

    }
}
