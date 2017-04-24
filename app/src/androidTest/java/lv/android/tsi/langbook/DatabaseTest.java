package lv.android.tsi.langbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lv.android.tsi.langbook.model.database.DatabaseContract;
import lv.android.tsi.langbook.model.database.DatabaseHelper;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.domain.Note;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Natasa on 23.04.2017.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    @Test
    public void testDatabaseCreation() {

        getContext().deleteDatabase(DatabaseHelper.DATABASE_NAME);

        SQLiteDatabase db = new DatabaseHelper(getContext()).getWritableDatabase();

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly", c.moveToFirst());

    }

    public void testTableCreation(){
        getContext().deleteDatabase(DatabaseHelper.DATABASE_NAME);

        SQLiteDatabase db = new DatabaseHelper(getContext()).getWritableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        Set<String> databaseNames = new HashSet<>(Arrays.asList(DatabaseContract.DictionaryEntry.TABLE_NAME,
                                                                DatabaseContract.NoteEntry.TABLE_NAME));

    }

    @Test
    public void testInsertSelectDeleteDictionary(){
        String title = "Test";
        Dictionary dictionary = new Dictionary(title);

        getContext().deleteDatabase(DatabaseHelper.DATABASE_NAME);
        SQLiteDatabase db = new DatabaseHelper(getContext()).getWritableDatabase();

        db.insert(DatabaseContract.DictionaryEntry.TABLE_NAME, null, dictionary.toContentValues());

        Cursor cursor = db.query(
                DatabaseContract.DictionaryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        assertTrue("Should return that one value", cursor.moveToFirst());

        Dictionary dictionaryFromDb = new Dictionary(cursor);
        assertEquals(dictionary.getTitle(), dictionaryFromDb.getTitle());

        db.delete(
                DatabaseContract.DictionaryEntry.TABLE_NAME,
                DatabaseContract.DictionaryEntry.COLUMN_TITLE + " =?",
                new String[]{ title });

        cursor = db.rawQuery("SELECT * FROM "+ DatabaseContract.DictionaryEntry.TABLE_NAME, null);

        assertFalse("Should not return values", cursor.moveToFirst());
    }

    @Test
    public void testInsertSelectDependentNote(){
        String dictionaryTitle = "Test-title";
        String noteCaption = "Test-caption";

        Dictionary dictionary = new Dictionary(dictionaryTitle);

        getContext().deleteDatabase(DatabaseHelper.DATABASE_NAME);
        SQLiteDatabase db = new DatabaseHelper(getContext()).getWritableDatabase();

        long id = db.insert(DatabaseContract.DictionaryEntry.TABLE_NAME, null, dictionary.toContentValues());
        Note note = new Note(noteCaption, dictionary.getId());

        db.insert(DatabaseContract.NoteEntry.TABLE_NAME, null, note.toContentValues());

        Cursor cursor = db.query(
                DatabaseContract.NoteEntry.TABLE_NAME,
                null,
                DatabaseContract.NoteEntry.COLUMN_DICTIONARY_ID_FK + "=?",
                new String[]{Long.toString(id)},
                null,
                null,
                null);

        cursor.moveToFirst();

        Note noteFromDb = new Note(cursor);

        assertEquals(note.getCaption(), noteFromDb.getCaption());
        assertEquals(note.getContent(), noteFromDb.getContent());


    }




    private Context getContext(){
        return InstrumentationRegistry.getTargetContext();
    }
}
