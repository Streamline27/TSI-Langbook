package lv.android.tsi.langbook.screens.content.presenter;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface ContentScreen {

    void setUpButtonIconToCheckMark();
    void setUpButtonIconToDefault();

    void showEditButton();
    void hideEditButton();

    void setEditTextEditable();
    void setEditTextStatic();

    void setActionBarTitleEditMode();
    void setActionbarTitleDefault();

    void goToNotesListScreen();



}
