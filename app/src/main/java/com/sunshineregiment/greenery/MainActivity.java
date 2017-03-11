package com.sunshineregiment.greenery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //make the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //add some tabs to it
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("YOUR IMPACT"));
        tabLayout.addTab(tabLayout.newTab().setText("GRAPHS"));
        tabLayout.addTab(tabLayout.newTab().setText("STEPS"));
        tabLayout.addTab(tabLayout.newTab().setText("LEADERBOARDS"));
        //set the gravity
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        StepCounter stepCounter = new StepCounter();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 2){
                    SharedPreferences prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
                    int steps = prefs.getInt("steps", 0);
                    TextView dayTextView, weekTextView, lifeTextView;
                    dayTextView = (TextView) findViewById(R.id.steps_textView);
                    weekTextView = (TextView) findViewById(R.id.steps_textView2);
                    lifeTextView = (TextView) findViewById(R.id.steps_textView3);
                    dayTextView.setText("" + steps);
                    weekTextView.setText("" + (steps+5432));
                    lifeTextView.setText("" + (steps+87654));
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}