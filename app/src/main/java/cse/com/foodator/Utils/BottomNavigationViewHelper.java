package cse.com.foodator.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import cse.com.foodator.Home.HomeActivity;
import cse.com.foodator.Likes.LikesActivity;
import cse.com.foodator.Profile.ProfileActivity;
import cse.com.foodator.R;
import cse.com.foodator.Search.SearchActivity;
import cse.com.foodator.Share.ShareActivity;

/**
 * Created by Raghuveer on 11-11-2017.
 */

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupNavigationView(BottomNavigationViewEx bottomNav) {
        Log.d(TAG, "setupNavigationView: disabling all animations");
        bottomNav.enableAnimation(false);
        bottomNav.enableItemShiftingMode(false);
        bottomNav.enableShiftingMode(false);
        bottomNav.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context,BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.ic_house:
                        context.startActivity(new Intent(context, HomeActivity.class)); //ACTIVITY_NUM = 0
                        break;
                    case R.id.ic_search:
                        context.startActivity(new Intent(context, SearchActivity.class)); //ACTIVITY_NUM = 1
                        break;
                    case R.id.ic_circle:
                        context.startActivity(new Intent(context, ShareActivity.class)); //ACTIVITY_NUM = 2
                        break;
                    case R.id.ic_alert:
                        context.startActivity(new Intent(context, LikesActivity.class)); //ACTIVITY_NUM = 3
                        break;
                    case R.id.ic_android:
                        context.startActivity(new Intent(context, ProfileActivity.class)); //ACTIVITY_NUM = 4
                        break;
                }

                return false;
            }
        });
    }
}
