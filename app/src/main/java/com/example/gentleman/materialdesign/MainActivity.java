package com.example.gentleman.materialdesign;

import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gentleman.materialdesign.Adapter.FruitAdapter;
import com.example.gentleman.materialdesign.data.Fruit;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private List<Fruit> mListFruit = new ArrayList<>();

    private FruitAdapter fruitAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        initFruit();
        fruitAdapter = new FruitAdapter(mListFruit);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(fruitAdapter);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            //设置返回键可见
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置返回键的图标
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //设置默认选中项
        //navView.setCheckedItem(R.id.nav_mine);
        //navView点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //FloatingActionButton点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"Data restored",Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }).show();
            }
        });
    }

    private void initFruit() {
        for (int i = 0;i<50;i++){
            Fruit one = new Fruit(String.valueOf(i),R.drawable.background);
            mListFruit.add(one);
        }
    }

    /**
     * 设置搜索按钮
     * @param menu Menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //初始化菜单
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        //创建SearchView
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        if (searchView!=null) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }


    /**
     * 导航栏点击事件
     * @param item MenuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}
