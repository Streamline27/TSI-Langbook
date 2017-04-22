package lv.android.tsi.langbook.screens.content;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.screens.content.presenter.ContentPresenter;
import lv.android.tsi.langbook.screens.content.presenter.ContentPresenterImpl;
import lv.android.tsi.langbook.screens.content.presenter.ContentScreen;

import static lv.android.tsi.langbook.utilities.functions.DelayUtilities.makeVisibleAfterDelay;
import static lv.android.tsi.langbook.utilities.functions.KeyboardUtilities.hideKeyboard;
import static lv.android.tsi.langbook.utilities.functions.KeyboardUtilities.showKeyBoard;

public class ContentFragment extends Fragment implements ContentScreen{

    @BindView(R.id.content_text)     EditText mEditTextContext;
    @BindView(R.id.content_edit_fab) FloatingActionButton mBtnEdit;

    private ActionBar mActionBar;

    @BindString(R.string.title_fragment_notes) String NOTES_TITLE;
    @BindString(R.string.title_mode_edit)  String EDIT_MODE_TITLE;

    private Unbinder unbinder;

    private ContentPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        this.unbinder = ButterKnife.bind(this, view);

        this.presenter = new ContentPresenterImpl(this);

        this.mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        this.setHasOptionsMenu(true);

        return view;
    }

    @OnClick(R.id.content_edit_fab)
    public void onEditButtonClick(View view){
        this.presenter.performEditButtonClick();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_content, menu);
    }

    @Override
    public void onDestroyView() {
        this.presenter.reset();

        super.onDestroyView();
        this.unbinder.unbind();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) this.presenter.performUpButtonClick();
        return true;
    }


    /* Content screen specific behaviour */

    @Override
    public void setUpButtonIconToCheckMark() {
        mActionBar.setHomeAsUpIndicator(R.mipmap.check_img);
    }

    @Override
    public void setUpButtonIconToDefault() {
        mActionBar.setHomeAsUpIndicator(null);
    }

    @Override
    public void showEditButton() {
        makeVisibleAfterDelay(mBtnEdit, 100);
    }

    @Override
    public void hideEditButton() {
        mBtnEdit.setVisibility(View.GONE);
    }

    @Override
    public void setEditTextEditable() {
        mEditTextContext.setFocusableInTouchMode(true);
        showKeyBoard(getActivity(), mEditTextContext);
    }

    @Override
    public void setEditTextStatic() {
        hideKeyboard(getActivity());
        mEditTextContext.clearFocus();
        mEditTextContext.setFocusable(false);
    }

    @Override
    public void setActionBarTitleEditMode() {
        mActionBar.setTitle(EDIT_MODE_TITLE);
    }

    @Override
    public void setActionbarTitleDefault() {
        mActionBar.setTitle(NOTES_TITLE);
    }

    @Override
    public void goToNotesListScreen() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}
