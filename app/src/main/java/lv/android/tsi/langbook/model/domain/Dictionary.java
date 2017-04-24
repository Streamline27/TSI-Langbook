package lv.android.tsi.langbook.model.domain;

import android.content.ContentValues;
import android.database.Cursor;


import java.io.Serializable;
import java.util.Date;

import lv.android.tsi.langbook.model.database.DatabaseContract.DictionaryEntry;

/**
 * Created by Vladislav on 17.04.2017.
 */

public class Dictionary implements Serializable{

    private long id;

    private String title;

    private Date creationDate;

    public Dictionary(String title) {
        this.title = title;
        this.creationDate = new Date();
        this.id = -1;
    }

    public Dictionary(Cursor cursor) {
        int titleIndex = cursor.getColumnIndex(DictionaryEntry.COLUMN_TITLE);
        int dateIndex = cursor.getColumnIndex(DictionaryEntry.COLUMN_DATE);
        int idIndex = cursor.getColumnIndex(DictionaryEntry._ID);

        this.id = idIndex;
        this.title = cursor.getString(titleIndex);
        this.creationDate = new Date(cursor.getLong(dateIndex));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getCreationDateLong() {
        return creationDate.getTime();
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = new Date(creationDate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContentValues toContentValues(){
        ContentValues v = new ContentValues();

        v.put(DictionaryEntry.COLUMN_DATE, getCreationDateLong());
        v.put(DictionaryEntry.COLUMN_TITLE, title);
        if (id != -1) v.put(DictionaryEntry._ID, id);

        return v;
    }


}
