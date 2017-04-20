package lv.android.tsi.langbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.screens.content.ContentFragment;
import lv.android.tsi.langbook.screens.notes.NotesFragment;
import lv.android.tsi.langbook.utilities.Constants;

import static lv.android.tsi.langbook.screens.content.ContentFragmentExportUtils.setActionBarHomeButtonIconOkMark;
import static lv.android.tsi.langbook.screens.content.ContentFragmentExportUtils.setActionBarHomeButtonIconDefault;
import static lv.android.tsi.langbook.utilities.functions.AnimationUtilities.setPendingTransitionAnimationNone;

public class NotesActivity extends AppCompatActivity implements NotesFragment.OnNoteSelectedListener,
                                                                ContentFragment.OnEditButtonClickListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @BindString(R.string.title_fragment_notes) String NOTES_TITLE;
    @BindString(R.string.title_mode_edit) String EDIT_MODE_TITLE;

    private Dictionary dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ButterKnife.bind(this);

        dictionary =  (Dictionary) getIntent().getSerializableExtra(Constants.DICTIONARY_EXTRA_KEY);

        mToolbar.setTitle(NOTES_TITLE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().add(R.id.note_fragment_container, new NotesFragment()).commit();

    }

    @Override
    public void onNoteSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.note_fragment_container, new ContentFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onContentEditModeToggledOn() {
        setActionBarTitle(EDIT_MODE_TITLE);
        setActionBarHomeButtonIconOkMark(this);
    }

    @Override
    public void onContentEditModeToggledOff() {
        setActionBarTitle(NOTES_TITLE);
        setActionBarHomeButtonIconDefault(this);
    }

    @Override
    public void onUpButtonPressed() {
        finish();
    }

    /**
        Helper methods
     */

    private void setActionBarTitle(String title){
        mToolbar.setTitle(title);
    }
}
