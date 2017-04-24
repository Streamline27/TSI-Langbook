package lv.android.tsi.langbook.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import java.util.Locale;

import javax.inject.Inject;

import lv.android.tsi.langbook.App;
import lv.android.tsi.langbook.Constants;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.utilities.LanguageUtils;

/**
 * Created by Natasa on 25.04.2017.
 */

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        ((App)getApplication()).getAppComponent().inject(this);

        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(getString(R.string.pref_language_key))){
            LanguageUtils.setPreferedLanguage(this, sharedPreferences);

            Intent i = new Intent(this, getCallerActivityClass());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
    }

    @Nullable
    private Class<?> getCallerActivityClass() {
        try {
            String callerName = getIntent().getExtras().getString(Constants.CALLING_ACTIVITY_EXTRA);
            return Class.forName(callerName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
