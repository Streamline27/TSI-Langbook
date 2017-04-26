package lv.android.tsi.langbook;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.domain.Note;

/**
 * Created by Strea on 26.04.2017.
 */

@RunWith(AndroidJUnit4.class)
public class FirebaseTest {

    public static final String FIREBASE_TEST_TAG = "FIREBASE_TEST";

    @Test
    public void testCreateData(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("Test");

        ref.setValue("Hello world");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(FIREBASE_TEST_TAG, value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(FIREBASE_TEST_TAG, "Error");
            }
        });

        database.getReference().removeValue();
    }

    @Test
    public void testPushData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("Test");
        DatabaseReference ref2 = ref.push();
        ref2.setValue("Hello world");

        Log.d(FIREBASE_TEST_TAG, ref2.getKey());
        database.getReference().removeValue();
    }

    public void testPusthWithChild(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Add data
        Dictionary dictionary = new Dictionary("Test dictionary");

        DatabaseReference dFolderRef = database.getReference("dictionaries");
        DatabaseReference dNodeRef = dFolderRef.push();

        long id = dFolderRef.getKey().hashCode();

        dictionary.setId(id);
        dNodeRef.setValue(dictionary);

        Note note = new Note("Test note", dictionary.getId());
        DatabaseReference noteFolderRef = database.getReference("notes");
        noteFolderRef.child(Long.toString(note.getDictionaryId())).setValue(note);

        // Retrieve data
        database.getReference("dictionaries").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){

                    Dictionary retrievedDictionary = dataSnapshot.getValue(Dictionary.class);
                    Log.d(FIREBASE_TEST_TAG, retrievedDictionary.getTitle());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

}
