package com.jp.fastadapterissue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

public class AnotherActivity extends AppCompatActivity {
    public int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
    }

    /**
     * add an item_rv to main fragment
     * @param view
     */
    public void addClick(View view) {
        /**
         * {@link MainActivityFragment#addItem(String)}
         */
        i++;
        EventBus.getDefault().post("item_add_" + i);

    }
}
