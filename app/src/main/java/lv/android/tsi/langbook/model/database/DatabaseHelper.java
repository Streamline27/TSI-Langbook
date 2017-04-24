package lv.android.tsi.langbook.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lv.android.tsi.langbook.model.database.DatabaseContract.DictionaryEntry;
import lv.android.tsi.langbook.model.database.DatabaseContract.NoteEntry;
import lv.android.tsi.langbook.model.domain.Dictionary;
import lv.android.tsi.langbook.model.domain.Note;

/**
 * Created by Natasa on 23.04.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "langbookdb";
    public static final int    VERSION = 1;

    final String SQL_CREATE_DICTIONARY_TABLE = "CREATE TABLE " + DictionaryEntry.TABLE_NAME + " (" +
            DictionaryEntry._ID + " INTEGER PRIMARY KEY," +
            DictionaryEntry.COLUMN_TITLE + " TEXT UNIQUE NOT NULL, " +
            DictionaryEntry.COLUMN_DATE  + " INTEGER NOT NULL " +
            " );";

    final String SQL_CREATE_NOTE_TABLE = "CREATE TABLE " + NoteEntry.TABLE_NAME + " (" +
            NoteEntry._ID + " INTEGER PRIMARY KEY," +
            NoteEntry.COLUMN_CAPTION          + " TEXT UNIQUE NOT NULL, " +
            NoteEntry.COLUMN_DATE             + " INTEGER NOT NULL, " +
            NoteEntry.COLUMN_CONTENT          + " TEXT NOT NULL, " +
            NoteEntry.COLUMN_DICTIONARY_ID_FK + " INTEGER NOT NULL, " +
            " FOREIGN KEY (" + NoteEntry.COLUMN_DICTIONARY_ID_FK + ") REFERENCES " +
            DictionaryEntry.TABLE_NAME + " (" + DictionaryEntry._ID + ") " +
            " );";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DICTIONARY_TABLE);
        db.execSQL(SQL_CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DictionaryEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NoteEntry.TABLE_NAME);
        onCreate(db);
    }




}
