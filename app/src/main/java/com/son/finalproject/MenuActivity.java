package com.son.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.son.finalproject.fragment.AssetFragment;
import com.son.finalproject.fragment.AssignmentFragment;
import com.son.finalproject.fragment.HomeFragment;
import com.son.finalproject.fragment.ReportFragment;
import com.son.finalproject.fragment.RequestFragment;
import com.son.finalproject.fragment.UserFragment;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_ASSET = 1;
    private static final int FRAGMENT_ASSIGNMENT = 2;
    private static final int FRAGMENT_REQUEST = 3;
    private static final int FRAGMENT_REPORT = 4;
    private static final int FRAGMENT_USER= 5;

    private int currentFragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Add Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Catch Navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Catch event when click item navigation view
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
        }else if( id == R.id.nav_asset){
            if(currentFragment != FRAGMENT_ASSET){
                replaceFragment(new AssetFragment());
                currentFragment = FRAGMENT_ASSET;
            }

        }else if( id == R.id.nav_assignment){
            if(currentFragment != FRAGMENT_ASSIGNMENT){
                replaceFragment(new AssignmentFragment());
                currentFragment = FRAGMENT_ASSIGNMENT;
            }

        }else if( id == R.id.nav_request){
            if(currentFragment != FRAGMENT_REQUEST){
                replaceFragment(new RequestFragment());
                currentFragment = FRAGMENT_REQUEST;
            }

        }else if( id == R.id.nav_report){
            if(currentFragment != FRAGMENT_REPORT){
                replaceFragment(new ReportFragment());
                currentFragment = FRAGMENT_REPORT;
            }

        }else if( id == R.id.nav_user){
            if(currentFragment != FRAGMENT_USER){
                replaceFragment(new UserFragment());
                currentFragment = FRAGMENT_USER;
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START); // Close Drawer
        return true;
    }

    // Press back to close the drawer
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void replaceFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}