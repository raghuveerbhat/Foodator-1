/**
 * Application Name: Foodator
 * Authors: Raghuveer, Vinay, Santhosh
 * Logo Screen
 */

package cse.com.foodator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cse.com.foodator.Home.HomeActivity;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                next_Activity();
                finish();
            }
        }, 3000);
    }

    /**
     *Starts new activity
     */
    private void next_Activity() {
        startActivity(new Intent(this,HomeActivity.class));
    }
}
