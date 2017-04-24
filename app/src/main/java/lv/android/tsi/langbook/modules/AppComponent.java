package lv.android.tsi.langbook.modules;

import javax.inject.Singleton;

import dagger.Component;
import lv.android.tsi.langbook.screens.content.ContentFragment;
import lv.android.tsi.langbook.screens.dictionaries.DictionariesFragment;
import lv.android.tsi.langbook.screens.notes.NotesFragment;

/**
 * Created by Natasa on 23.04.2017.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(NotesFragment fragment);
    void inject(DictionariesFragment fragment);
    void inject(ContentFragment fragment);
}
