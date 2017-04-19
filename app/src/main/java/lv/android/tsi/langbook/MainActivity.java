package lv.android.tsi.langbook;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.screens.dictionaries.DictionariesFragment;
import lv.android.tsi.langbook.utilities.Constants;
import lv.android.tsi.langbook.utilities.builders.CreateDialogBuilder;

import static lv.android.tsi.langbook.utilities.functions.AnimationUtilities.setPendingTransitionAnimationNone;

public class MainActivity extends AppCompatActivity implements DictionariesFragment.OnDictionarySelectedListener{

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
        return true;
    }




    @Override
    public void onDictionarySelected(Dictionary dictionary) {
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra(Constants.DICTIONARY_EXTRA_KEY, dictionary);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        setPendingTransitionAnimationNone(this);
    }


    /*
        Private helper methods
        */




}
