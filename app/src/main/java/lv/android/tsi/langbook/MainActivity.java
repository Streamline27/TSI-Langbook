package lv.android.tsi.langbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.screens.dictionaries.DictionariesFragment;

public class MainActivity extends AppCompatActivity implements DictionariesFragment.OnDictionarySelectedListener{

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbar.setTitle("Dictionaries");
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onDictionarySelected(Dictionary dictionary) {
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra(Constants.DICTIONARY_EXTRA_KEY, dictionary);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
