package lv.android.tsi.langbook.screens.dictionaries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.utilities.builders.CreateDialogBuilder;


public class DictionariesFragment extends Fragment {

    @BindView(R.id.dictionaries_list_view) ListView mdDctionariesListView;

    @BindString(R.string.dialog_title_dictionaries) String CREATE_DICTIONARY_DIALOG_TITLE;


    private Unbinder unbinder;

    private DictionariesAdapter adapter;
    private List<Dictionary> dictionaries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionaries, container, false);
        unbinder = ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        dictionaries = getMockContent();

        adapter = new DictionariesAdapter(getContext(), dictionaries);
        mdDctionariesListView.setAdapter(adapter);
        mdDctionariesListView.setOnItemClickListener(this::onDictionaryItemSelectedAction);
        mdDctionariesListView.setVerticalScrollBarEnabled(true);

        return view;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_add_dictionary) getCreateDictionaryDialog().show();
        return true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private AlertDialog getCreateDictionaryDialog() {
        return CreateDialogBuilder.getBuilder(getContext())
                .setTitle(CREATE_DICTIONARY_DIALOG_TITLE)
                .setCreateButtonClickListener(this::onCreateDialogCreateButtonClick)
                .build();
    }

    private void onCreateDialogCreateButtonClick(View view){
        String text = ((EditText) view.findViewById(R.id.dialog_item_name_edit_text)).getText().toString();
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }



    private void onDictionaryItemSelectedAction(AdapterView<?> parent, View view, int position, long id){
        OnDictionarySelectedListener activity = (OnDictionarySelectedListener) getActivity();
        activity.onDictionarySelected(dictionaries.get(position));

    }

    private List<Dictionary> getMockContent() {
        List<Dictionary> mockDictionaries = new ArrayList<>();
        for (int i = 0; i < 10; i++) mockDictionaries.add(new Dictionary(Integer.toString(i)));
        return mockDictionaries;
    }


    public interface OnDictionarySelectedListener {
        void onDictionarySelected(Dictionary dictionary);
    }



}
