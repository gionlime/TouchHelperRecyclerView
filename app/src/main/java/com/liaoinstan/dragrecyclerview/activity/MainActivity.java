package com.liaoinstan.dragrecyclerview.activity;

import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.liaoinstan.dragrecyclerview.R;
import com.liaoinstan.dragrecyclerview.fragment.MainFragment;
import com.liaoinstan.dragrecyclerview.fragment.MyGridFragment;
import com.liaoinstan.dragrecyclerview.fragment.MyListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    AppBarLayout A;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState==null){
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment,mainFragment)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.list:
                fragment = new MyListFragment();
                break;
            case R.id.grid:
                fragment = new MyGridFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
