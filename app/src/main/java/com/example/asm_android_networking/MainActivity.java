package com.example.asm_android_networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
    Menu menu;
    Boolean mStatehide = false;
    private Manage_fragment_adapter manage_fragment_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------------Ánh xạ-----------------//
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        vpmain = (ViewPager)findViewById(R.id.vpmain);
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        //-------------------------------------------------------------//

        // ---------------------Toolbar-------------------//
        toolbar.setTitle("Truyen 24h");
        toolbar.setSubtitle("(4/50)");
        setSupportActionBar(toolbar);
        //-------------------------------------------------------------//


        //------------------ Xét fargment vào tablayout--------------------//
        manage_fragment_adapter = new Manage_fragment_adapter(getSupportFragmentManager());
       setupViewPage(vpmain);
       tabLayout.setupWithViewPager(vpmain);
        //-------------------------------------------------------------//
        createTabIcons();

      //  tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));//put your color

        // ----------------xét toolbar khi thay đổi fragment--------------------------//
        vpmain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    getSupportActionBar().show();
                    toolbar.setTitle("Truyen 24h");
                    mStatehide=false;
                    //----------callback function onCreateOptionsMenu--------------///
                    invalidateOptionsMenu();
                    //---------------------///////////////////////
                }
                else if(position==1){
                    getSupportActionBar().show();
                    toolbar.setTitle("TheLoai");
                    toolbar.hideOverflowMenu();
                    mStatehide=true;
                    invalidateOptionsMenu();
                } else if(position==2){
                    getSupportActionBar().show();
                    toolbar.setTitle("Thuvien");
                    mStatehide=true;
                    invalidateOptionsMenu();
                  //  MenuItem searchitem = menu.findItem (R.id.action_search);
                 //   menu.findItem(R.id.action_load).setVisible(false);
                } else if(position==3){
                    getSupportActionBar().hide();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //-------------------------------------------------------------//
    }


//----------------Tiêm nemu----------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        if (mStatehide)
        {
                menu.getItem(1).setVisible(false);
        }
        return true;
    }
    //-------------------------------------------------------------//

    //-----------------Event khi click menu---------------------//
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

    //-------------------------------------------------------------//

    //!!--------- SetupViewPage---------!!////
    private void setupViewPage(ViewPager viewPager){
        Manage_fragment_adapter adapter = new Manage_fragment_adapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Home(),"Home");
        adapter.addFragment(new Fragment_Category(),"Category");
        adapter.addFragment(new Fragment_Library(),"Library");
        adapter.addFragment(new Fragment_User(),"User");
        viewPager.setAdapter(adapter);
    }
//-------------------------------------------------------------//

    //-------------Override Fragment---------------//
    @Override
    public void onFragmentInteraction(Uri uri) {
    }
    //---------------------------------------------//

//----------Custom Tablayout ---------------//
    private void createTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_action_home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_action_category, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_action_library, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabfour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabfour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_action_user, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabfour);
    }
    //---------------------------------------------------------//
}
