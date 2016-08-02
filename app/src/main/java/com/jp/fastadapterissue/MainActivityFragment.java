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

    private FastItemAdapter<StringItem> mItemAdapter;
    private RecyclerView mRecyclerView;

    private List<StringItem> mItems = new ArrayList<>();


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

        for (int i = 0; i < 8; i++) {
            StringItem item = new StringItem();
            item.item = "item " + i;
            mItems.add(item);
        }

        mItemAdapter.add(mItems);
        mRecyclerView.setAdapter(mItemAdapter);
        mRecyclerView.setLayoutManager(manager);
    }

    class StringItem extends AbstractItem<StringItem, ViewHolder> {
        private String item;

        @Override
        public int getType() {
            return R.id.rl_item;
        }

        @Override
        public int getLayoutRes() {
            return R.layout.item;
        }

        @Override
        public void bindView(ViewHolder holder) {
            super.bindView(holder);
            holder.mTextView.setText(item);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected final View view;
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            mTextView = (TextView) view.findViewById(R.id.tv_item);
        }
    }

    @Subscribe
    public void addItem(String string) {
        Log.d(TAG, "add item " + string + ", thread: " + Thread.currentThread().getName());
        StringItem item = new StringItem();
        item.item = string;
        mItems.add(0, item);

        Log.w(TAG, "list size: " + mItems.size());

        mItemAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


}
