package com.shoma.mypocketcare.model;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.shoma.mypocketcare.R;

public class IncomeMasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_master);
        initializeScreen();
    }

    private void initializeScreen() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

       // setSupportActionBar(toolbar);

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    private class SectionPagerAdapter extends FragmentStatePagerAdapter{
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new ViewIncomeFragment();
                case 1: return new AddIncomeFragment();

                default: return new ViewIncomeFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "VIEW INCOME";
                case 1: return "ADD INCOME";
                default: return "VIEW INCOME";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }


        /*@Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Fragment old_fragment = getActiveFragment(old_position);
            Fragment new_fragment = getActiveFragment(new_position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }*/
    }
}
