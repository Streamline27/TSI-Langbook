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
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lv.android.tsi.langbook.App;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.model.domain.Note;
import lv.android.tsi.langbook.screens.content.presenter.ContentPresenter;
import lv.android.tsi.langbook.screens.content.presenter.ContentScreen;
import lv.android.tsi.langbook.utilities.Constants;

import static lv.android.tsi.langbook.utilities.functions.DelayUtilities.makeVisibleAfterDelay;
import static lv.android.tsi.langbook.utilities.functions.KeyboardUtilities.hideKeyboard;
import static lv.android.tsi.langbook.utilities.functions.KeyboardUtilities.showKeyBoard;

public class ContentFragment extends Fragment implements ContentScreen{

    @BindView(R.id.content_text)     EditText mEditTextContext;
    @BindView(R.id.content_edit_fab) FloatingActionButton mBtnEdit;
    @BindView(R.id.header_content_caption) TextView mNoteCaptionText;

    private ActionBar mActionBar;

    @BindString(R.string.title_fragment_notes) String NOTES_TITLE;
    @BindString(R.string.title_mode_edit)  String EDIT_MODE_TITLE;

    private Unbinder unbinder;

    @Inject ContentPresenter presenter;

    public static ContentFragment newInstance(Note note){
        Bundle args = new Bundle();
        args.putSerializable(Constants.NOTE_EXTRA_KEY, note);
        ContentFragment f = new ContentFragment();
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ((App)getActivity().getApplication()).getAppComponent().inject(this);
        this.unbinder = ButterKnife.bind(this, view);

        Note note = (Note) getArguments().getSerializable(Constants.NOTE_EXTRA_KEY);

        this.mNoteCaptionText.setText(note.getCaption());
        this.mEditTextContext.setText(note.getContent());

        this.presenter.initialize(this, note);

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
        super.onDestroyView();

        String content = mEditTextContext.getText().toString();

        this.presenter.saveAndResetEditMode(content);
        this.presenter.detachScreen();
        this.unbinder.unbind();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            String contentText = mEditTextContext.getText().toString();
            this.presenter.performUpButtonClick(contentText);
        }
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
        mEditTextContext.setLongClickable(true);
        showKeyBoard(getActivity(), mEditTextContext);
    }

    @Override
    public void setEditTextStatic() {
        hideKeyboard(getActivity());
        mEditTextContext.clearFocus();
        mEditTextContext.setFocusable(false);
        mEditTextContext.setLongClickable(false);
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
