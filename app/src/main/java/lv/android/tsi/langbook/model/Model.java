package lv.android.tsi.langbook.model;

import java.util.List;

import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.domain.Note;

/**
 * Created by Natasa on 23.04.2017.
 */

public interface Model {

    List<Note> getNotes(Dictionary dictionary);
    List<Dictionary> getDictionaries();

    long addNote(Note note);
    long addDictionary(Dictionary dictionary);

    void deleteNote(Note note);
    void deleteDictionary(Dictionary dictionary);

    void updateNote(Note note);

}
