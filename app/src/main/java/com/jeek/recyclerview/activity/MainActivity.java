package com.jeek.recyclerview.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.jeek.recyclerview.R;
import com.jeek.recyclerview.fragment.HorizontalFragment;
import com.jeek.recyclerview.fragment.MainFragment;
import com.jeek.recyclerview.fragment.GridFragment;
import com.jeek.recyclerview.fragment.VerticalFragment;
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
