package lv.android.tsi.langbook.screens.notes.presenter;

import android.widget.ImageView;

import java.util.List;

import lv.android.tsi.langbook.model.domain.Dictionary;
import lv.android.tsi.langbook.model.domain.Note;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface NotesPresenter {

    List<Note> getNotes();

    void performUpButtonClick();
    void performMenuCreateClick();
    void performMenuDeleteClick();
    void performSelectNoteClick(int position);

    void createNote(String text);

    void deleteCheckedNote();
    void toggleNoteCheck(int position, ImageView mark);
    void resetCheck();

    void initialize(NotesScreen screen, Dictionary dictionary);
    void detachScreen();

    void performBackButtonClick();

}
