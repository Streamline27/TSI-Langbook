package lv.android.tsi.langbook;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

import java.util.List;

import lv.android.tsi.langbook.model.DatabaseModel;
import lv.android.tsi.langbook.model.database.DatabaseHelper;
import lv.android.tsi.langbook.model.domain.Dictionary;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Natasa on 24.04.2017.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseModelTest {

    public void testInsertSelectDictionary(){
        getContext().deleteDatabase(DatabaseHelper.DATABASE_NAME);

        DatabaseModel model = new DatabaseModel(getContext());

        Dictionary dictionary = new Dictionary("Test");
        model.addDictionary(dictionary);

        List<Dictionary> dictionaries = model.getDictionaries();

        Dictionary dictionaryFromDb = dictionaries.get(0);

        assertEquals(dictionary.getTitle(), dictionaryFromDb.getTitle());
        assertEquals(dictionary.getId(), dictionaryFromDb.getId());


    }


    private Context getContext(){
        return InstrumentationRegistry.getTargetContext();
    }

}
