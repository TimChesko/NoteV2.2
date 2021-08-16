package com.example.notev22;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notev22.note.MainFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readSettings();
        initToolbar();
        initDrawer(initToolbar());
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        // связываем   drawerLayout toolbar
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.add,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_main:
                        showFragment(MainFragment.newInstance());
                        break;
                    case R.id.action_favorite:
                        showFragment(com.example.notev22.FavoriteFragment.newInstance());
                        break;
                    case R.id.action_settings:
                        showFragment(SettingsFragment.newInstance());
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main:
                showFragment(MainFragment.newInstance());
                break;
            case R.id.action_favorite:
                showFragment(com.example.notev22.FavoriteFragment.newInstance());
                break;
            case R.id.action_settings:
                showFragment(SettingsFragment.newInstance());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void readSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(com.example.notev22.Settings.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        com.example.notev22.Settings.isDeleteFragment = sharedPreferences.getBoolean(com.example.notev22.Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, false);
        com.example.notev22.Settings.isBackIsRemove = sharedPreferences.getBoolean(com.example.notev22.Settings.IS_BACK_IS_REMOVE_FRAGMENT, false);
        com.example.notev22.Settings.isBackStack = sharedPreferences.getBoolean(com.example.notev22.Settings.IS_BACK_STACK_USED, false);
        com.example.notev22.Settings.isReplaceFragment = sharedPreferences.getBoolean(com.example.notev22.Settings.IS_REPLACE_FRAGMENT_USED, false);
        com.example.notev22.Settings.isAddFragment = !com.example.notev22.Settings.isReplaceFragment;
    }

    Fragment getVisibleFragment(FragmentManager fragmentManager) {
        List<Fragment> fList = fragmentManager.getFragments();
        for (int i = 0; i < fList.size(); i++) {
            Fragment fragment = fList.get(i);
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    public void showFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (com.example.notev22.Settings.isDeleteFragment) {
            Fragment fragmentForDelete = getVisibleFragment(fragmentManager);
            if (fragmentForDelete != null) {
                fragmentTransaction.remove(fragmentForDelete);
            }
        }

        if (com.example.notev22.Settings.isAddFragment) {
            fragmentTransaction
                    .add(R.id.fragment_container, fragment);
        }

        if (com.example.notev22.Settings.isReplaceFragment) {
            fragmentTransaction
                    .replace(R.id.fragment_container, fragment);
        }

        if(com.example.notev22.Settings.isBackStack){
            fragmentTransaction.addToBackStack("");
        }

        fragmentTransaction.commit();

    }
}