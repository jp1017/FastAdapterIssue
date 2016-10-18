package com.jp.fastadapterissue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jp.fastadapterissue.entity.TradeItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String TAG = "MAINACTIVITYFRAGMENTLOG";

    private View mViewContent;
    private Button mButton;

    private FastItemAdapter<TradeItem> mItemAdapter;
    private RecyclerView mRecyclerView;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewContent = inflater.inflate(R.layout.fragment_main, container, false);

        mButton = (Button) mViewContent.findViewById(R.id.button);
        mRecyclerView = (RecyclerView) mViewContent.findViewById(R.id.recycler_view);

        return mViewContent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mItemAdapter = new FastItemAdapter<>();

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setSpanSizeLookup(new GridLayoutManager.DefaultSpanSizeLookup());

        List<TradeItem> mItems = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            TradeItem item = new TradeItem();
            item.item = "item_rv " + i;
            mItems.add(item);
        }

        mItemAdapter.add(mItems);
        mRecyclerView.setAdapter(mItemAdapter);
        mRecyclerView.setLayoutManager(manager);
    }


    @Subscribe
    public void addItem(String string) {
        Log.d(TAG, "add item_rv " + string + ", thread: " + Thread.currentThread().getName());
        TradeItem item = new TradeItem();
        item.item = string;
    
        //we do not want to manually alter the list or anything. everything is handled from the adapter for you
        //mItems.add(0, item_rv);
        
        //simply add the item_rv
        mItemAdapter.add(0, item);
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


}
