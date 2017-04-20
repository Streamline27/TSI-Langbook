package lv.android.tsi.langbook.screens.dictionaries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.features.CheckDeleteItemFeature;
import lv.android.tsi.langbook.features.CreateItemFeature;


public class DictionariesFragment extends Fragment {

    @BindView(R.id.dictionaries_list_view) ListView mdDctionariesListView;
    @BindString(R.string.dialog_title_dictionaries) String CREATE_DICTIONARY_DIALOG_TITLE;

    private CheckDeleteItemFeature mCheckDeleteFeature;
    private CreateItemFeature mCreateItemFeature;

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

        this.mCheckDeleteFeature = new CheckDeleteItemFeature(adapter, getActivity());
        this.mCreateItemFeature = new CreateItemFeature(getContext(), CREATE_DICTIONARY_DIALOG_TITLE);

        mdDctionariesListView.setAdapter(adapter);
        mdDctionariesListView.setOnItemClickListener(this::onDictionaryItemSelectedAction);
        mdDctionariesListView.setOnItemLongClickListener(mCheckDeleteFeature.getToggleCheckAction());

        mdDctionariesListView.setVerticalScrollBarEnabled(true);


        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mCheckDeleteFeature.bindToggleableDeleteButton(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_add_dictionary)   mCreateItemFeature.runCreateDialog();
        if (itemId == R.id.action_delete_item) mCheckDeleteFeature.runConfirmDeleteDialog();
        return true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        this.mCheckDeleteFeature.reset();
        super.onPause();

    }

    private void onDictionaryItemSelectedAction(AdapterView<?> parent, View view, int position, long id){
        OnDictionarySelectedListener activity = (OnDictionarySelectedListener) getActivity();
        activity.onDictionarySelected(dictionaries.get(position));
    }



    public interface OnDictionarySelectedListener {
        void onDictionarySelected(Dictionary dictionary);
    }



    /*
        Private helpers
     */

    private List<Dictionary> getMockContent() {
        List<Dictionary> mockDictionaries = new ArrayList<>();
        for (int i = 0; i < 10; i++) mockDictionaries.add(new Dictionary(Integer.toString(i)));
        return mockDictionaries;
    }

}
