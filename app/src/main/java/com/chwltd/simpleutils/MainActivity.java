package com.chwltd.simpleutils;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chwltd.simpleutils.databinding.ActivityMainBinding;
import com.chwltd.utils.SystemUtils;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        initRecyclerView();
        init();
        SystemUtils.CHToast(this,"测试Toast");
    }

    private void init() {
        SystemUtils.changeStatusBarTextColor(this,false);
        binding.mainToolbar.setPadding(0, SystemUtils.getStatusBarHeight(this), 0, 0);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.mainRecyclerView;

        MainAdapter adapter = new MainAdapter(getStrings());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected List<String> getStrings() {
        List<String> lists = new ArrayList<>();
        int num = (int) (1 + Math.random() * (50 - 10 + 1));
        for (int i = 0; i < num; i++) {
            lists.add("第 " + i + " 条数据");
        }
        return lists;
    }
}