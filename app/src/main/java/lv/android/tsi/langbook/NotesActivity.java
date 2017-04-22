package lv.android.tsi.langbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.model.Dictionary;
import lv.android.tsi.langbook.screens.content.ContentFragment;
import lv.android.tsi.langbook.screens.notes.NotesFragment;
import lv.android.tsi.langbook.screens.notes.OnNoteSelectedListener;
import lv.android.tsi.langbook.utilities.Constants;


public class NotesActivity extends AppCompatActivity implements OnNoteSelectedListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindString(R.string.title_fragment_notes) String NOTES_TITLE;


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
    public void onUpButtonPressed() {
        finish();
    }
}
