package com.liaoinstan.dragrecyclerview.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.dragrecyclerview.R;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.vertical).setOnClickListener(this);
        view.findViewById(R.id.horizontal).setOnClickListener(this);
        view.findViewById(R.id.grid).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ((View.OnClickListener)getActivity()).onClick(v);
    }
}
