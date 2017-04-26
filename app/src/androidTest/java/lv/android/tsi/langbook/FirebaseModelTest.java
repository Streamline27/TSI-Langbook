package lv.android.tsi.langbook;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;

import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.domain.Note;
import lv.android.tsi.langbook.model.FirebaseModel;

/**
 * Created by Strea on 26.04.2017.
 */


@RunWith(AndroidJUnit4.class)
public class FirebaseModelTest {

    public static final String TAG = "FIREBASE_MODEL_TEST";

    @Test
    public void testInsertSelectDictionary(){

//        FirebaseDatabase.getInstance().getReference().removeValue();

        FirebaseModel model = new FirebaseModel();
        Dictionary dictionary = new Dictionary("Test dictionary");

        model.addDictionary(dictionary);

        Note note = new Note("Test note", dictionary.getId());
        model.addNote(note);

        model.getDictionaries(dictionaries -> dictionaries.forEach(d -> {
            Log.d(TAG, d.getTitle());
        }));
        model.getNotes(dictionary, notes -> notes.forEach(n -> {
            Log.d(TAG, n.getCaption());
        }));
    }
}
