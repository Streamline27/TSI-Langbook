package lv.android.tsi.langbook.screens.notes.presenter;

import lv.android.tsi.langbook.model.domain.Note;
import lv.android.tsi.langbook.interactions.CheckDeleteScreen;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface NotesScreen extends CheckDeleteScreen{

    void showCreateDialog();
    void showDeleteDialog();

    void refreshNotesList();

    void goToNoteContent(Note note);

    void goUpToDictionaryList();

}
