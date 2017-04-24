package lv.android.tsi.langbook.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import lv.android.tsi.langbook.model.database.DatabaseHelper;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.domain.Note;

import lv.android.tsi.langbook.model.database.DatabaseContract.DictionaryEntry;
import lv.android.tsi.langbook.model.database.DatabaseContract.NoteEntry;


/**
 * Created by Natasa on 24.04.2017.
 */

public class DatabaseModel implements Model {

    private Context mContext;

    public DatabaseModel(Context context) {
        this.mContext = context;
    }

    @Override
    public List<Note> getNotes(Dictionary dictionary) {
        SQLiteDatabase db = new DatabaseHelper(mContext).getReadableDatabase();

        String[] args = getArgs(Long.toString(dictionary.getId()));
        Cursor c = db.query(
                NoteEntry.TABLE_NAME,
                null,
                NoteEntry.COLUMN_DICTIONARY_ID_FK+"=?",
                args,
                null,
                null,
                null);

        List<Note> notes = new ArrayList<>();

        if (c.moveToFirst()){
            do {
                notes.add(new Note(c));
            }while (c.moveToNext());
        }
        c.close();

        return notes;
    }

    @Override
    public List<Dictionary> getDictionaries() {
        SQLiteDatabase db = new DatabaseHelper(mContext).getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + DictionaryEntry.TABLE_NAME, null);

        List<Dictionary> dictionaries = new ArrayList<>();

        if (c.moveToFirst()){
            do {
                dictionaries.add(new Dictionary(c));
            }while (c.moveToNext());
        }
        c.close();

        return dictionaries;
    }

    @Override
    public long addNote(Note note) {
        SQLiteDatabase db = new DatabaseHelper(mContext).getWritableDatabase();
        long id = db.insert(NoteEntry.TABLE_NAME, null, note.toContentValues());
        db.close();

        note.setId(id);
        return id;
    }

    @Override
    public long addDictionary(Dictionary dictionary) {
        SQLiteDatabase db = new DatabaseHelper(mContext).getWritableDatabase();
        long id = db.insert(DictionaryEntry.TABLE_NAME, null, dictionary.toContentValues());
        db.close();

        dictionary.setId(id);

        return id;
    }

    @Override
    public void deleteNote(Note note) {
        SQLiteDatabase db = new DatabaseHelper(mContext).getWritableDatabase();

        String[] args = getArgs(Long.toString(note.getId()));

        db.delete(NoteEntry.TABLE_NAME, NoteEntry._ID + "=?", args);
        db.close();
    }

    @Override
    public void deleteDictionary(Dictionary dictionary) {
        long dictionaryId = dictionary.getId();

        SQLiteDatabase db = new DatabaseHelper(mContext).getWritableDatabase();

        String[] args = getArgs(Long.toString(dictionaryId));

        db.delete(DictionaryEntry.TABLE_NAME, DictionaryEntry._ID + "=?", args);
        db.delete(NoteEntry.TABLE_NAME, NoteEntry.COLUMN_DICTIONARY_ID_FK + "=?", args);
        db.close();
    }

    @Override
    public void updateNote(Note note) {
        SQLiteDatabase db = new DatabaseHelper(mContext).getWritableDatabase();

        String[] args = getArgs(Long.toString(note.getId()));
        db.update(NoteEntry.TABLE_NAME, note.toContentValues(), NoteEntry._ID + "=?", args);
        db.close();
    }

    private String[] getArgs(String... args){
        return args;
    }
}
