package lv.android.tsi.langbook.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.model.domain.Dictionary;
import lv.android.tsi.langbook.model.domain.Note;
import lv.android.tsi.langbook.screens.content.ContentFragment;
import lv.android.tsi.langbook.screens.notes.NotesFragment;
import lv.android.tsi.langbook.screens.notes.OnNoteSelectedListener;
import lv.android.tsi.langbook.utilities.Constants;


public class NotesActivity extends AppCompatActivity implements OnNoteSelectedListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindString(R.string.title_fragment_notes) String NOTES_TITLE;

    private final String NOTES_FRAGMENT_TAG = "NOTES_FRAGMENT_TAG";

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

        NotesFragment fragment = NotesFragment.newInstance(dictionary);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.note_fragment_container, fragment, NOTES_FRAGMENT_TAG)
                .commit();

    }

    @Override
    public void onNoteSelected(Note note) {
        ContentFragment f = ContentFragment.newInstance(note);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.note_fragment_container, f)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onUpButtonPressed() {
        onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NotesFragment fragment = (NotesFragment)getSupportFragmentManager().findFragmentByTag(NOTES_FRAGMENT_TAG);
        if (fragment != null) fragment.onBackPressed();

    }
}
