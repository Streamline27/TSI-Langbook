package lv.android.tsi.langbook.screens.content.presenter;

import lv.android.tsi.langbook.model.Model;
import lv.android.tsi.langbook.model.domain.Note;

/**
 * Created by Natasa on 22.04.2017.
 */

public class ContentPresenterImpl implements ContentPresenter{

    private ContentScreen screen;
    private Model model;

    private Note note;

    private boolean mEditModeEnabled;

    public ContentPresenterImpl(Model model) {
        this.mEditModeEnabled = false;
        this.model = model;
    }

    @Override
    public void performUpButtonClick(String contentText) {
        if (mEditModeEnabled) this.saveAndResetEditMode(contentText);
        else                  this.screen.goToNotesListScreen();
    }

    @Override
    public void saveContent(String contentText) {
        note.setContent(contentText);
        model.updateNote(note);
    }

    @Override
    public void performEditButtonClick() {
        this.screen.hideEditButton();
        this.screen.setActionBarTitleEditMode();
        this.screen.setUpButtonIconToCheckMark();
        this.screen.setEditTextEditable();
        this.mEditModeEnabled = true;

    }

    @Override
    public void saveAndResetEditMode(String contentText) {
        this.screen.showEditButton();
        this.screen.setActionbarTitleDefault();
        this.screen.setUpButtonIconToDefault();
        this.screen.setEditTextStatic();
        this.mEditModeEnabled = false;

        saveContent(contentText);
    }

    @Override
    public void initialize(ContentScreen screen, Note note) {
        this.note = note;
        this.screen = screen;
    }

    @Override
    public void detachScreen() {
        this.note = null;
        this.screen = null;
    }
}
