package lv.android.tsi.langbook.screens.content.presenter;

/**
 * Created by Natasa on 22.04.2017.
 */

public class ContentPresenterImpl implements ContentPresenter{

    private ContentScreen screen;

    private boolean mEditModeEnabled;

    public ContentPresenterImpl(ContentScreen screen) {
        this.screen = screen;
        this.mEditModeEnabled = false;
    }

    @Override
    public void performUpButtonClick() {

        if (mEditModeEnabled) this.reset();
        else                  this.screen.goToNotesListScreen();
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
    public void reset() {
        this.screen.showEditButton();
        this.screen.setActionbarTitleDefault();
        this.screen.setUpButtonIconToDefault();
        this.screen.setEditTextStatic();
        this.mEditModeEnabled = false;
    }
}
