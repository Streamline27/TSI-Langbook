package lv.android.tsi.langbook;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.domain.Dictionary;

public class NotesActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    private Dictionary dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ButterKnife.bind(this);

        dictionary =  (Dictionary) getIntent().getSerializableExtra(Constants.DICTIONARY_EXTRA_KEY);
        mToolbar.setTitle("Notes");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        getSupportActionBar().setElevation(0);
    }
}
