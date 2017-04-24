package lv.android.tsi.langbook;

import android.app.Application;

import lv.android.tsi.langbook.modules.AppComponent;
import lv.android.tsi.langbook.modules.AppModule;
import lv.android.tsi.langbook.modules.DaggerAppComponent;


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
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
