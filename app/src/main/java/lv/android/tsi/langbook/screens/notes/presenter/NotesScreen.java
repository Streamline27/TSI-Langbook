package lv.android.tsi.langbook.screens.notes.presenter;

import java.util.List;

import lv.android.tsi.langbook.domain.Note;
import lv.android.tsi.langbook.interactions.CheckDeleteScreen;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface NotesScreen extends CheckDeleteScreen{

    void displayFetchedData(List<Note> notes);

    void showCreateDialog();
    void showDeleteDialog();

    void refreshNotesAdapterList();

    void goToNoteContent(Note note);

    void goUpToDictionaryList();

    void changeListViewToSpinner();
    void changeSpinnerToListView();

}
