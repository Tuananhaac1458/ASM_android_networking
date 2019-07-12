package com.example.asm_android_networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asm_android_networking.Adapter.Manage_fragment_adapter;
import com.example.asm_android_networking.Fragment.Fragment_Category;
import com.example.asm_android_networking.Fragment.Fragment_Home;
import com.example.asm_android_networking.Fragment.Fragment_Library;
import com.example.asm_android_networking.Fragment.Fragment_User;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements Fragment_Home.OnFragmentInteractionListener, Fragment_Category.OnFragmentInteractionListener, Fragment_Library.OnFragmentInteractionListener, Fragment_User.OnFragmentInteractionListener {

    Toolbar toolbar;
    ViewPager vpmain;
    TabLayout tabLayout;
    private Manage_fragment_adapter manage_fragment_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Ánh xạ
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        vpmain = (ViewPager)findViewById(R.id.vpmain);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        // Toolbar
        toolbar.setTitle("Truyen 24h");
        toolbar.setSubtitle("(4/50)");
        setSupportActionBar(toolbar);

        //
        manage_fragment_adapter = new Manage_fragment_adapter(getSupportFragmentManager());
       setupViewPage(vpmain);
       tabLayout.setupWithViewPager(vpmain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            //mũi tên lùi trên toolbar
//            case android.R.id.home:
//                onBackPressed();
//                return true;
            case R.id.action_search:
                //code xử lý khi bấm menu1
                break;
            case R.id.action_load:
                //code xử lý khi bấm menu2
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPage(ViewPager viewPager){
        Manage_fragment_adapter adapter = new Manage_fragment_adapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Home(),"Home");
        adapter.addFragment(new Fragment_Category(),"Category");
        adapter.addFragment(new Fragment_Library(),"Library");
        adapter.addFragment(new Fragment_User(),"User");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
