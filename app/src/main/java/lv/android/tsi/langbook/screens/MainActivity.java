package lv.android.tsi.langbook.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.App;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.screens.dictionaries.DictionariesFragment;
import lv.android.tsi.langbook.screens.dictionaries.OnDictionarySelectedListener;
import lv.android.tsi.langbook.Constants;
import lv.android.tsi.langbook.utilities.ImageUtilities;
import lv.android.tsi.langbook.utilities.IntentUtilities;


public class MainActivity extends AppCompatActivity implements OnDictionarySelectedListener {

    public static final int GET_IMAGE_REQUEST_CODE = 0;
    @BindString(R.string.title_fragment_dictionaries) String DICTIONARIES_TITLE;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.drawer_content) NavigationView mNavigationView;


    /* TODO: add presenter to all of this */
    /* TODO: Refactor this for godness sake */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        ButterKnife.bind(this);

        // Initialize toolbar
        mToolbar.setTitle(DICTIONARIES_TITLE);
        setSupportActionBar(mToolbar);

        // Initialize drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        String avatarFilePath = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(Constants.SHARED_PREFERENCES_AVATAR_FILE_PATH, "");
        if (!avatarFilePath.equals("")) setDrawerHeaderAvatar(ImageUtilities.loadImage(this, avatarFilePath));

        mNavigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_action_load_img){
            Intent i = IntentUtilities.getImageRequestIntent();
            startActivityForResult(Intent.createChooser(i, "Select Picture"), GET_IMAGE_REQUEST_CODE);
        }
        if (id == R.id.nav_action_reset_img){
            setDrawerHeaderAvatar(getResources().getDrawable(android.R.drawable.sym_def_app_icon));
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putString(Constants.SHARED_PREFERENCES_AVATAR_FILE_PATH, "")
                    .apply();
        }
        if (id == R.id.nav_action_cloud_enable){
            ((App)getApplication()).setCloudModeOn();
            restartActivity();
        }
        if (id == R.id.nav_action_cloud_disable){
            ((App)getApplication()).setCloudModeOff();
            restartActivity();
        }

        return false;
    }

    private void restartActivity() {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                setDrawerHeaderAvatar(bitmap);

                String imagePath = ImageUtilities.saveImage(getApplicationContext(), bitmap, "avatar");
                PreferenceManager.getDefaultSharedPreferences(this)
                        .edit().putString(Constants.SHARED_PREFERENCES_AVATAR_FILE_PATH, imagePath).apply();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dictionaries, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent i = new Intent(this, SettingsActivity.class);
            i.putExtra(Constants.CALLING_ACTIVITY_EXTRA, this.getClass().getName());
            startActivity(i);
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDictionarySelected(Dictionary dictionary) {
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra(Constants.DICTIONARY_EXTRA_KEY, dictionary);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            DictionariesFragment fragment = (DictionariesFragment)getSupportFragmentManager().findFragmentById(R.id.dictionaries_fragment);
            fragment.resetCheck();
            super.onBackPressed();
        }

    }

    private void setDrawerHeaderAvatar(Bitmap image){
        ImageView avatar = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.drawer_header_avatar);
        Drawable drawable = new BitmapDrawable(getResources(), image);
        avatar.setImageDrawable(drawable);
    }
    private void setDrawerHeaderAvatar(Drawable image){
        ImageView avatar = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.drawer_header_avatar);
        avatar.setImageDrawable(image);
    }

}
