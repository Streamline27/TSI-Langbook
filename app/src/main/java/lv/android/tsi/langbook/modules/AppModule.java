package lv.android.tsi.langbook.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lv.android.tsi.langbook.interactions.CheckDeleteInteraction;
import lv.android.tsi.langbook.model.FirebaseModel;
import lv.android.tsi.langbook.model.SQLiteModel;
import lv.android.tsi.langbook.model.Model;
import lv.android.tsi.langbook.screens.content.presenter.ContentPresenter;
import lv.android.tsi.langbook.screens.content.presenter.ContentPresenterImpl;
import lv.android.tsi.langbook.screens.dictionaries.presenter.DictionariesPresenter;
import lv.android.tsi.langbook.screens.dictionaries.presenter.DictionariesPresenterImpl;
import lv.android.tsi.langbook.screens.notes.presenter.NotesPresenter;
import lv.android.tsi.langbook.screens.notes.presenter.NotesPresenterImpl;

/**
 * Created by Natasa on 23.04.2017.
 */

@Module
public class AppModule {

    private Application mApplication;
    private StorageOption storageOption;

    public AppModule(Application mApplication, StorageOption storageOption) {
        this.mApplication = mApplication;
        this.storageOption = storageOption;
    }

    @Provides
    @Singleton
    public Model providesModel(Application application){
        if (storageOption == StorageOption.SQLITE)   return new SQLiteModel(application);
        if (storageOption == StorageOption.FIREBASE) return new FirebaseModel();
        return null;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    @Provides
    @Singleton
    Application providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    CheckDeleteInteraction providesCheckDelete(){
        return new CheckDeleteInteraction();
    }

    @Provides
    @Singleton
    NotesPresenter providesNotesPresenter(Model model){
        return new NotesPresenterImpl(model);
    }

    @Provides
    @Singleton
    DictionariesPresenter providesDictionariesPresenter(Model model){
        return new DictionariesPresenterImpl(model);
    }

    @Provides
    @Singleton
    ContentPresenter providesContentPresenter(Model model){
        return new ContentPresenterImpl(model);
    }

}
