package com.example.projectcrest.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.projectcrest.R;
import com.example.projectcrest.fragments.auction_add;
import com.example.projectcrest.fragments.auction_list;
import com.example.projectcrest.fragments.home_page;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LandingPage extends AppCompatActivity {

    private FirebaseAuth firebase;
    BottomNavigationView bottom_navView;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        firebase = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DrawerLayout drawerLayout = findViewById(R.id.landing_page_body);
        NavigationView navigationView = findViewById(R.id.nav_view);

        if (firebaseUser != null) {
            String email = firebaseUser.getEmail();
            Log.d("Email Is:", email);
        }

        bottom_navView = findViewById(R.id.bottom_navbar);
        bottom_navView.setSelectedItemId(R.id.bottom_navbar_home);

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
                getSupportFragmentManager().beginTransaction().replace(R.id.landing_page_body, fragment).commit();

                return true;
            }
        });

    }


}