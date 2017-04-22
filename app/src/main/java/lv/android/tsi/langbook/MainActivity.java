package lv.android.tsi.langbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.model.Dictionary;
import lv.android.tsi.langbook.screens.dictionaries.OnDictionarySelectedListener;
import lv.android.tsi.langbook.utilities.Constants;


public class MainActivity extends AppCompatActivity implements OnDictionarySelectedListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindString(R.string.title_fragment_dictionaries) String DICTIONARIES_TITLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbar.setTitle(DICTIONARIES_TITLE);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dictionaries, menu);
        return false;
    }

    @Override
    public void onDictionarySelected(Dictionary dictionary) {
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra(Constants.DICTIONARY_EXTRA_KEY, dictionary);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }




}
