package com.joseadilson.news_app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.tabs)
    TabLayout tabL;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabL.setupWithViewPager(mViewPager);
        getSupportActionBar().show();
        setIndicator();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new GoanFragment() ;

                case 1:
                    return new HeraldFragment();

                case 2:

                    return new GoanObserverFragment();

                case 3:
                    return new NavhindFragment() ;
            }

            //   return PlaceholderFragment.newInstance(position + 1);
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

    }

    public void setIndicator(){
        for (int i=0; i<mSectionsPagerAdapter.getCount();i++)
        {
            switch (i) {
                case 0:
                    tabL.getTabAt(i).setIcon(R.drawable.ic_menu_camera);
                    break;
                case 1:
                    tabL.getTabAt(i).setIcon(R.drawable.ic_menu_gallery);
                    break;
                case 2:
                    tabL.getTabAt(i).setIcon(R.drawable.ic_menu_manage);
                    break;
                case 3:
                    tabL.getTabAt(i).setIcon(R.drawable.ic_menu_share);
                    break;
                /*   case 4:
                    tabL.getTabAt(i).setIcon(R.drawable.user_grey);
                    break;*/
                default:
                    break;
            }
        }

        /*View custom = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ((TextView) custom.findViewById(R.id.tabTitle)).setText("Tab 3");
        (custom.findViewById(R.id.tabIcon)).setBackgroundResource(R.drawable.ic_place_white_18dp);
        TabLayout.Tab customTab = tabLayout.getTabAt(2);
        customTab.setCustomView(custom);*/

        tabL.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tabL.getSelectedTabPosition()) {
                    case 0:
                        tabL.getTabAt(0).setIcon(R.drawable.ic_menu_camera);
                        tabL.getTabAt(1).setIcon(R.drawable.ic_menu_gallery);
                        tabL.getTabAt(2).setIcon(R.drawable.ic_menu_manage);
                        tabL.getTabAt(3).setIcon(R.drawable.ic_menu_share);

                        //do what you want when tab 0 is selected
                        break;
                    case 1:
                        tabL.getTabAt(0).setIcon(R.drawable.ic_menu_camera);
                        tabL.getTabAt(1).setIcon(R.drawable.ic_menu_gallery);
                        tabL.getTabAt(2).setIcon(R.drawable.ic_menu_manage);
                        tabL.getTabAt(3).setIcon(R.drawable.ic_menu_share);
                        break;
                    case 2:
                        tabL.getTabAt(0).setIcon(R.drawable.ic_menu_camera);
                        tabL.getTabAt(1).setIcon(R.drawable.ic_menu_gallery);
                        tabL.getTabAt(2).setIcon(R.drawable.ic_menu_manage);
                        tabL.getTabAt(3).setIcon(R.drawable.ic_menu_share);
                        break;
                    case 3:
                        tabL.getTabAt(0).setIcon(R.drawable.ic_menu_camera);
                        tabL.getTabAt(1).setIcon(R.drawable.ic_menu_gallery);
                        tabL.getTabAt(2).setIcon(R.drawable.ic_menu_manage);
                        tabL.getTabAt(3).setIcon(R.drawable.ic_menu_share);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //mViewPager.setCurrentItem(tab.getPosition());

            }
        });
    }
}
