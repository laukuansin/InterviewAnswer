package com.example.carwashapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.carwashapplication.fragments.CompleteFragment;
import com.example.carwashapplication.fragments.HomeFragment;
import com.example.carwashapplication.fragments.QueueFragment;
import com.example.carwashapplication.util.CarWashTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Fragment _fragment;
    private int menuResource = 0;
    private int index = 0;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");
        setContentView(R.layout.activity_main);
        initialization();
        //set the default fragment
        if (savedInstanceState == null) {
            _fragment = HomeFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_container, _fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable= () -> {
            handler.postDelayed(runnable, delay);
            CarWashTask task = new CarWashTask();
            task.execute();
            ((QueueFragment)QueueFragment.newInstance()).update();
            ((CompleteFragment)CompleteFragment.newInstance()).update();

        }, delay);
        super.onResume();
    }


    private void initialization() {
        //setup bottom navigation bar
        BottomNavigationView btmNavBar = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);
        btmNavBar.getMenu().getItem(0).setEnabled(false);//because on default first item at bottom navigation did not disable

        btmNavBar.setOnNavigationItemSelectedListener(item -> {
            //set the 3 menu to enable
            for (int i = 0; i < 3; i++) {
                btmNavBar.getMenu().getItem(i).setEnabled(true);
            }
            switch (item.getItemId()) {
                case R.id.home:
                    _fragment = HomeFragment.newInstance();
                    index = 0;
                    menuResource=0;
                    btmNavBar.getMenu().getItem(0).setEnabled(false);
                    break;
                case R.id.queue:
                    _fragment = QueueFragment.newInstance();
                    index = 1;
                    menuResource=0;
                    btmNavBar.getMenu().getItem(1).setEnabled(false);
                    break;
                case R.id.checked:
                    _fragment = CompleteFragment.newInstance();
                    index = 2;
                    menuResource=R.menu.complete_only;
                    btmNavBar.getMenu().getItem(2).setEnabled(false);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Default frag", Toast.LENGTH_SHORT).show();
                    break;
            }
            btmNavBar.setSelectedItemId(item.getItemId());//set selected item
            //load the fragment
            return loadFragment(_fragment);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(menuResource!=0)
            getMenuInflater().inflate(menuResource, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.checked:
                if(index==2)
                {
                    ((CompleteFragment)_fragment).popUpBottomDialog();
                }
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }
}