package com.jeek.recyclerview.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jeek.recyclerview.common.DividerGridItemDecoration;
import com.jeek.recyclerview.entity.Item;
import com.jeek.recyclerview.R;
import com.jeek.recyclerview.adapter.RecyclerAdapter;
import com.jeek.recyclerview.helper.DragItemTouchCallback;
import com.jeek.recyclerview.helper.OnRecyclerItemClickListener;
import com.jeek.recyclerview.utils.ACache;
import com.jeek.recyclerview.utils.VibratorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * create by jeek
 * 2022/4/27
 * des:
 **/
public class GridFragment extends Fragment implements DragItemTouchCallback.OnDragListener{

    private List<Item> results = new ArrayList<Item>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////////////////////////////////////////////////////////
        /////////初始化数据，如果缓存中有就使用缓存中的
        ArrayList<Item> items = (ArrayList<Item>) ACache.get(getActivity()).getAsObject("items");
        if (items!=null)
            results.addAll(items);
        else {
            for (int i = 0; i < 3; i++) {
                results.add(new Item(i * 8 + 0, "收款", R.drawable.takeout_ic_category_brand));
                results.add(new Item(i * 8 + 1, "转账", R.drawable.takeout_ic_category_flower));
                results.add(new Item(i * 8 + 2, "余额宝", R.drawable.takeout_ic_category_fruit));
                results.add(new Item(i * 8 + 3, "手机充值", R.drawable.takeout_ic_category_medicine));
                results.add(new Item(i * 8 + 4, "医疗", R.drawable.takeout_ic_category_motorcycle));
                results.add(new Item(i * 8 + 5, "彩票", R.drawable.takeout_ic_category_public));
                results.add(new Item(i * 8 + 6, "电影", R.drawable.takeout_ic_category_store));
                results.add(new Item(i * 8 + 7, "游戏", R.drawable.takeout_ic_category_sweet));
            }
        }
        results.remove(results.size()-1);
        results.add(new Item(results.size(), "更多", R.drawable.takeout_ic_more));
        ////////////////////////////////////////////////////////
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());
    }

    private RecyclerView recyclerView;
    private ItemTouchHelper itemTouchHelper;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerAdapter adapter = new RecyclerAdapter(R.layout.adapter_item_grid,results);
        recyclerView = (RecyclerView)view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        itemTouchHelper = new ItemTouchHelper(new DragItemTouchCallback(adapter).setOnDragListener(this));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition()!=results.size()-1) {
                    itemTouchHelper.startDrag(vh);
                    VibratorUtil.Vibrate(getActivity(), 70);   //震动70ms
                }
            }
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Item item = results.get(vh.getLayoutPosition());
                Toast.makeText(getActivity(),item.getId()+" "+item.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFinishDrag() {
        //存入缓存
        ACache.get(getActivity()).put("items",(ArrayList<Item>)results);
    }
}