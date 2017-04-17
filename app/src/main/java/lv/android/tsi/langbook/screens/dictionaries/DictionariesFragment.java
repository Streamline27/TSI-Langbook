package lv.android.tsi.langbook.screens.dictionaries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.domain.Dictionary;


public class DictionariesFragment extends Fragment {

    @BindView(R.id.dictionaries_list_view) ListView mdDctionaryView;
    private Unbinder unbinder;

    private DictionariesAdapter adapter;
    private List<Dictionary> dictionaries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionaries, container, false);
        unbinder = ButterKnife.bind(this, view);

        dictionaries = getMockContent();

        adapter = new DictionariesAdapter(getContext(), dictionaries);
        mdDctionaryView.setAdapter(adapter);
        mdDctionaryView.setOnItemClickListener(this::onDictionaryItemSelectedAction);

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
