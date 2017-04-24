package lv.android.tsi.langbook.model.domain;

import android.content.ContentValues;
import android.database.Cursor;

import com.j256.ormlite.stmt.query.In;

import java.io.Serializable;
import java.util.Date;

import lv.android.tsi.langbook.model.database.DatabaseContract.NoteEntry;

/**
 * Created by Vladislav on 17.04.2017.
 */


public class Note implements Serializable {

    private long id;

    private String caption;

    private Date creationDate;

    private String content;

    private long dictionaryId;

    public Note(String caption, long dictionaryId) {
        this.caption = caption;
        this.dictionaryId = dictionaryId;
        this.creationDate = new Date();
        this.content = "";
        this.id = -1;
    }

    public Note(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(NoteEntry._ID);
        int captionIndex = cursor.getColumnIndex(NoteEntry.COLUMN_CAPTION);
        int dateIndex = cursor.getColumnIndex(NoteEntry.COLUMN_DATE);
        int contextIndex = cursor.getColumnIndex(NoteEntry.COLUMN_CONTENT);
        int dictionaryIndex = cursor.getColumnIndex(NoteEntry.COLUMN_DICTIONARY_ID_FK);


        this.id = cursor.getInt(idIndex);
        this.caption = cursor.getString(captionIndex);
        this.creationDate = new Date(cursor.getLong(dateIndex));
        this.content = cursor.getString(contextIndex);
        this.dictionaryId = cursor.getLong(dictionaryIndex);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getContent()
    {
        if (content == null) return "";
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public void setCreationDate(long creationDate){
        this.creationDate = new Date(creationDate);
    }

    public long getCreationDateLong(){
        return creationDate.getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContentValues toContentValues(){
        ContentValues v = new ContentValues();

        v.put(NoteEntry.COLUMN_CAPTION, caption);
        v.put(NoteEntry.COLUMN_DATE, getCreationDateLong());
        v.put(NoteEntry.COLUMN_CONTENT, content);
        if (dictionaryId != -1) v.put(NoteEntry.COLUMN_DICTIONARY_ID_FK, dictionaryId);
        if (id != -1) v.put(NoteEntry._ID, id);

        return v;
    }
}
