package lv.android.tsi.langbook;

import android.app.Application;
import android.preference.PreferenceManager;

import lv.android.tsi.langbook.modules.AppComponent;
import lv.android.tsi.langbook.modules.AppModule;
import lv.android.tsi.langbook.modules.DaggerAppComponent;

import static lv.android.tsi.langbook.utilities.LanguageUtils.setPreferedLanguage;


/**
 * Created by Natasa on 22.04.2017.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        setPreferedLanguage(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
