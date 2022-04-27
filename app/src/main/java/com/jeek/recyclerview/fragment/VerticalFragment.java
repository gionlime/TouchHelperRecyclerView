package com.jeek.recyclerview.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeek.recyclerview.entity.Item;
import com.jeek.recyclerview.R;
import com.jeek.recyclerview.adapter.RecyclerAdapter;
import com.jeek.recyclerview.helper.DragItemTouchCallback;
import com.jeek.recyclerview.helper.OnRecyclerItemClickListener;
import com.jeek.recyclerview.utils.VibratorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * create by jeek
 * 2022/4/27
 * des:
 **/
public class VerticalFragment extends Fragment{

    private List<Item> results = new ArrayList<Item>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i=0;i<3;i++){
            results.add(new Item(i*8+0,"收款", R.drawable.takeout_ic_category_brand));
            results.add(new Item(i*8+1,"转账", R.drawable.takeout_ic_category_flower));
            results.add(new Item(i*8+2,"余额宝", R.drawable.takeout_ic_category_fruit));
            results.add(new Item(i*8+3,"手机充值", R.drawable.takeout_ic_category_medicine));
            results.add(new Item(i*8+4,"医疗", R.drawable.takeout_ic_category_motorcycle));
            results.add(new Item(i*8+5,"彩票", R.drawable.takeout_ic_category_public));
            results.add(new Item(i*8+6,"电影", R.drawable.takeout_ic_category_store));
            results.add(new Item(i*8+7,"游戏", R.drawable.takeout_ic_category_sweet));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerAdapter adapter = new RecyclerAdapter(R.layout.adapter_item_list,results);

        RecyclerView recyclerView = (RecyclerView)view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new DragItemTouchCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition()!=results.size()-1) {
                    itemTouchHelper.startDrag(vh);
                    VibratorUtil.Vibrate(getActivity(), 70);   //震动70ms
                }
            }
        });
    }
}
