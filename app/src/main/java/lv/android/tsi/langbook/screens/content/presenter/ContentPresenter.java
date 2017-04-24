package lv.android.tsi.langbook.screens.content.presenter;

import lv.android.tsi.langbook.model.domain.Note;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface ContentPresenter {

    void performEditButtonClick();

    void performUpButtonClick(String contentText);

    void saveContent(String contentText);

    void saveAndResetEditMode(String contentText);

    void initialize(ContentScreen screen, Note note);
    void detachScreen();
}
