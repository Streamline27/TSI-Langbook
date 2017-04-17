package lv.android.tsi.langbook;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.screens.content.ContentFragment;
import lv.android.tsi.langbook.screens.notes.NotesFragment;

public class NotesActivity extends AppCompatActivity implements NotesFragment.OnNoteSelectedListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;

    private Dictionary dictionary;

    private String notesFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ButterKnife.bind(this);

        dictionary =  (Dictionary) getIntent().getSerializableExtra(Constants.DICTIONARY_EXTRA_KEY);
        mToolbar.setTitle("Notes");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.note_fragment_container, new NotesFragment()).commit();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        getSupportActionBar().setElevation(0);
    }

    @Override
    public void onNoteSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.note_fragment_container, new ContentFragment())
                .addToBackStack(null)
                .commit();
    }

}
