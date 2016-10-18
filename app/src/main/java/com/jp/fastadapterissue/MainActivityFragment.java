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

import com.jp.fastadapterissue.entity.TradeEntity;
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter;
import com.mikepenz.fastadapter.items.GenericAbstractItem;
import com.mikepenz.fastadapter.utils.Function;
import com.mikepenz.fastadapter.utils.ViewHolderFactory;

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

    private GenericFastItemAdapter<TradeEntity, TradeItem> mItemAdapter;
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
        mItemAdapter = new GenericFastItemAdapter<TradeEntity, TradeItem>(new Function<TradeEntity, TradeItem>() {
            @Override
            public TradeItem apply(TradeEntity tradeEntity) {
                return new TradeItem(tradeEntity);
            }
        });

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setSpanSizeLookup(new GridLayoutManager.DefaultSpanSizeLookup());

        List<TradeEntity> mItems = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            TradeEntity item = new TradeEntity();
            item.item = "item_rv " + i;
            mItems.add(item);
        }

        mItemAdapter.addModel(mItems);
        mRecyclerView.setAdapter(mItemAdapter);
        mRecyclerView.setLayoutManager(manager);
    }


    @Subscribe
    public void addItem(String string) {
        Log.d(TAG, "add item_rv " + string + ", thread: " + Thread.currentThread().getName());
        TradeEntity item = new TradeEntity();
        item.item = string;
    
        mItemAdapter.addModel(item);
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    public class TradeItem extends GenericAbstractItem<TradeEntity, TradeItem, TradeItem.ViewHolder> {
        //the static ViewHolderFactory which will be used to generate the ViewHolder for this Item
        private final ViewHolderFactory<? extends ViewHolder> FACTORY = new ItemFactory();

        public TradeItem(TradeEntity tradeEntity) {
            super(tradeEntity);
        }

        @Override
        public int getType() {
            return R.id.rl_item;
        }

        @Override
        public int getLayoutRes() {
            return R.layout.item_rv;
        }

        @Override
        public void bindView(ViewHolder holder, List payloads) {
            super.bindView(holder, payloads);
            holder.mTextView.setText(getModel().item);
        }


        /**
         * our ItemFactory implementation which creates the ViewHolder for our adapter.
         * It is highly recommended to implement a ViewHolderFactory as it is 0-1ms faster for ViewHolder creation,
         * and it is also many many times more efficient if you define custom listeners on views within your item.
         */
        protected class ItemFactory implements ViewHolderFactory<ViewHolder> {
            public ViewHolder create(View v) {
                return new ViewHolder(v);
            }
        }

        /**
         * return our ViewHolderFactory implementation here
         *
         * @return
         */
        @Override
        public ViewHolderFactory<? extends ViewHolder> getFactory() {
            return FACTORY;
        }


        protected class ViewHolder extends RecyclerView.ViewHolder {
            protected final View view;
            private TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                mTextView = (TextView) view.findViewById(R.id.tv_item);
            }
        }
    }


}
