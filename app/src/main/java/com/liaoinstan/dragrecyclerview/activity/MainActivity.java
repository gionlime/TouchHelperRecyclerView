package com.liaoinstan.dragrecyclerview.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.liaoinstan.dragrecyclerview.R;
import com.liaoinstan.dragrecyclerview.fragment.HorizontalFragment;
import com.liaoinstan.dragrecyclerview.fragment.MainFragment;
import com.liaoinstan.dragrecyclerview.fragment.GridFragment;
import com.liaoinstan.dragrecyclerview.fragment.VerticalFragment;
/**
 * create by jeek
 * 2022/4/27
 * des:
 **/
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


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
            case R.id.vertical:
                fragment = new VerticalFragment();
                break;

            case R.id.horizontal:
                fragment = new HorizontalFragment();
                break;

            case R.id.grid:
                fragment = new GridFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
