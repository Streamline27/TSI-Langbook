package lv.android.tsi.langbook.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vladislav on 17.04.2017.
 */

public class Dictionary implements Serializable{
    private String title;
    private Date creationDate;
    private List<Note> notes;

    public Dictionary(String title) {
        this.title = title;
        this.creationDate = new Date();
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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note){
        if (notes == null) this.notes = new ArrayList<>();
        notes.add(note);
    }
}
