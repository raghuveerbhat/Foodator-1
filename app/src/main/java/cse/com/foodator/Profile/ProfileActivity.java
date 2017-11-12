package cse.com.foodator.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import cse.com.foodator.R;
import cse.com.foodator.Utils.BottomNavigationViewHelper;
import cse.com.foodator.Utils.GridImageAdapter;
import cse.com.foodator.Utils.UniversalImageLoader;

/**
 * Created by Raghuveer on 11-11-2017.
 */

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;
    private int mcurrentApiVersion;

    private Context mContext = ProfileActivity.this;

    private ProgressBar mProgressBar;
    private ImageView mProfilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        mcurrentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(mcurrentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }

        setupActivityWidgets();
        setupBottomNavigationView();
        setupToolbar();
        setProfileImage();
        tempGridSetup();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mcurrentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void tempGridSetup() {
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://www.ndtv.com/cooks/images/kuttu.dosa.jpg");
        imgURLs.add("https://expressgiftservices.files.wordpress.com/2015/01/35_jalebi.png");
        imgURLs.add("https://thefoodfairy.files.wordpress.com/2011/08/dsc_8376.jpg");
        imgURLs.add("https://i1.wp.com/files.hungryforever.com/wp-content/uploads/2017/01/02171720/paper-dosa-recipe.jpg?w=1269&strip=all&quality=80");
        imgURLs.add("http://foodofinterest.com/wp-content/uploads/2017/01/DSC_0037_00037-1.jpg");
        imgURLs.add("https://i.ytimg.com/vi/-1sT6hy_wg4/maxresdefault.jpg");
        imgURLs.add("http://www.nithyas-kitchen.com/wp-content/uploads/2016/07/idli-dosa-batter-using-mixie.1024x1024.jpg");
        imgURLs.add("https://i2.wp.com/files.hungryforever.com/wp-content/uploads/2015/04/Featured-image-masala-dosa-recipe-720x378.jpg?resize=720%2C378");
        imgURLs.add("http://cdn1.foodviva.com/static-content/food-images/desserts-sweets-recipes/rasgulla/rasgulla.jpg");
        imgURLs.add("http://files.hungryforever.com/wp-content/uploads/2016/01/11225703/Featured-image-pongal-delhi.jpg");
        imgURLs.add("https://i.ytimg.com/vi/SZ-uZt4zjs8/maxresdefault.jpg");
        imgURLs.add("http://images.media-allrecipes.com/userphotos/250x250/708879.jpg");
        imgURLs.add("http://www.bikanervala.ae/images/south-indian/header1.jpg");
        imgURLs.add("https://www.ndtv.com/cooks/images/mysore.masala.dosa.1%20%281%29.jpg");
        imgURLs.add("http://maayeka.com/wp-content/uploads/2012/03/Gulab-jamunrasgulla-maayeka.jpg");
        imgURLs.add("http://www.vegrecipesofindia.com/wp-content/uploads/2016/05/dosa-recipe-5.jpg");
        imgURLs.add("http://www.diettaste.com/images/side-dishes/sweet-potato-flatbread-roti3-w.jpg");
        imgURLs.add("http://www.bollywoodsweetbazaar.com.au/wp-content/uploads/2017/06/indian-snacks.jpg");
        imgURLs.add("http://www.manjulaskitchen.com/blog/wp-content/uploads/aloo_puri.jpg");
        imgURLs.add("https://inhabitat.com/wp-content/blogs.dir/1/files/2016/03/Leftovers-for-Hungry-Indian-Food.jpg");
        imgURLs.add("http://www.vegrecipesofindia.com/wp-content/uploads/2012/09/dry-aloo-matar-recipe-04.jpg");
        imgURLs.add("http://onedaycart.com/odcb/wp-content/uploads/2015/01/Rava-Idli.jpg");
        imgURLs.add("https://i0.wp.com/files.hungryforever.com/wp-content/uploads/2017/06/20131010/easy-rasgulla-recipes-600x451.jpg?resize=600%2C451");
        imgURLs.add("https://www.ndtv.com/cooks/images/idli.2.jpg");
        imgURLs.add("https://www.ndtv.com/cooks/images/Radha%20Ballavi%20%28Stuffed%20Puri%29.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgURLs) {
        GridView gridView = (GridView) findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adpater = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(adpater);

    }

    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: Loading profile image...");
        String imgURL = "timedotcom.files.wordpress.com/2017/08/donald-trump-charlottesville-press-conference.jpg";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, mProgressBar, "https://");
    }

    private void setupActivityWidgets() {
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        mProfilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.ProfileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account settings");
                startActivity(new Intent(mContext, AccountSettingsActivity.class));
            }
        });
    }

    /**
     * Setting up bottom navigation view
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up bottom navigation view...");
        BottomNavigationViewEx bottomNavEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupNavigationView(bottomNavEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavEx);
        Menu menu = bottomNavEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
