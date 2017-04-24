package lv.android.tsi.langbook.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

import lv.android.tsi.langbook.R;

/**
 * Created by Natasa on 25.04.2017.
 */

public class LanguageUtils {
    public static void setPreferedLanguage(Context ctx, SharedPreferences preferences){
        String localeString = preferences.getString(ctx.getString(R.string.pref_language_key), "");

        Resources res = ctx.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(localeString);
        res.updateConfiguration(conf, dm);
    }


}
