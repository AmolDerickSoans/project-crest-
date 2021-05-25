package com.example.projectcrest.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.projectcrest.ImplementFirebaseActions;
import com.example.projectcrest.R;
import com.example.projectcrest.fragments.auction_add;
import com.example.projectcrest.fragments.auction_list;
import com.example.projectcrest.fragments.home_page;
import com.example.projectcrest.fragments.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class LandingPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebase;
    BottomNavigationView bottom_navView;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    ImplementFirebaseActions fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

fa = new ImplementFirebaseActions();
        firebase = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        drawerLayout = findViewById(R.id.landing_page_body_drawerLayout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (firebaseUser != null) {
            String email = firebaseUser.getEmail();
            Log.d("Email Is:", email);
        }

        bottom_navView = findViewById(R.id.bottom_navbar);
        bottom_navView.setSelectedItemId(R.id.bottom_navbar_home);

        navigationView.setNavigationItemSelectedListener(this);


        bottom_navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.bottom_navbar_home:
                        fragment = new home_page();
                        break;

                    case R.id.bottom_navbar_aucAdd:
                        fragment = new auction_add();
                        break;

                    case R.id.bottom_navbar_aucList:
                        fragment = new auction_list();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.landing_page_fragment_position, fragment).commit();

                return true;
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.landing_page_fragment_position,
                    new home_page()).commit();

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        switch (item.getItemId()) {
            case R.id.side_navbar_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.landing_page_fragment_position, new profile()).commit();
                toolbar.setTitle("Profile");
                break;
            case R.id.side_navbar_logout:
                try {
                    fa.signOut(this);
                } catch (Exception e) {
                    FirebaseAuth.getInstance().signOut();
                }


                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}



