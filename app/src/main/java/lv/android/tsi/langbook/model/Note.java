package lv.android.tsi.langbook.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vladislav on 17.04.2017.
 */

public class Note implements Serializable {
    private String caption;
    private Date creationDate;
    private String text;

    public Note(String caption) {
        this.caption = caption;
        this.creationDate = new Date();
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
