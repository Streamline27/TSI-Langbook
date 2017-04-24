package lv.android.tsi.langbook.screens.notes;

import lv.android.tsi.langbook.model.domain.Note;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface OnNoteSelectedListener {
    void onNoteSelected(Note note);
    void onUpButtonPressed();
}
