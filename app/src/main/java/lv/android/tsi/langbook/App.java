package lv.android.tsi.langbook;

import android.app.Application;
import android.preference.PreferenceManager;

import lv.android.tsi.langbook.modules.AppComponent;
import lv.android.tsi.langbook.modules.AppModule;
import lv.android.tsi.langbook.modules.DaggerAppComponent;
import lv.android.tsi.langbook.modules.StorageOption;

import static lv.android.tsi.langbook.utilities.LanguageUtils.setPreferedLanguage;


/**
 * Created by Natasa on 22.04.2017.
 */

public class App extends Application {

    private AppComponent mAppComponent;
    private boolean cloudModeEnabled = false;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, StorageOption.SQLITE))
                .build();

        setPreferedLanguage(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public void setCloudModeOn(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, StorageOption.FIREBASE))
                .build();
        cloudModeEnabled = true;
    }

    public void setCloudModeOff(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, StorageOption.SQLITE))
                .build();
        cloudModeEnabled = false;
    }

    public boolean isCloudModeEnabled(){
        return cloudModeEnabled;
    }
}
