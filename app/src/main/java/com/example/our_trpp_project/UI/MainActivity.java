package com.example.our_trpp_project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.our_trpp_project.R;
/** MainActivity for displaying the main page */
public class MainActivity extends AppCompatActivity {
    /** Constructor for MainActivity */
    public MainActivity() {
        super(R.layout.activity_main);
    }
    /**
     * onCreate function. Initializes the activity after its creation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                    findFragmentById(R.id.nav_host_fragment);
            NavController navController = navHostFragment.getNavController();
            navController.navigate(R.id.startFragment);
        }
    }
}