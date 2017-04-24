package lv.android.tsi.langbook.screens.dictionaries;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lv.android.tsi.langbook.App;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.model.domain.Dictionary;
import lv.android.tsi.langbook.screens.dictionaries.presenter.DictionariesPresenter;
import lv.android.tsi.langbook.screens.dictionaries.presenter.DictionariesScreen;

import static lv.android.tsi.langbook.utilities.functions.DialogUtilities.showCreateDialogWithCallback;
import static lv.android.tsi.langbook.utilities.functions.DialogUtilities.showDeleteDialogWithCallback;


public class DictionariesFragment extends Fragment implements DictionariesScreen{


    @BindString(R.string.dialog_title_dictionaries) String CREATE_DICTIONARY_DIALOG_TITLE;

    @BindView(R.id.dictionaries_list_view) ListView mdDctionariesListView;
    private Unbinder unbinder;

    @Inject DictionariesPresenter presenter;

    private MenuItem mDeleteMenuItem;
    private DictionariesAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dictionaries, container, false);

        this.unbinder = ButterKnife.bind(this, view);
        ((App)getActivity().getApplication()).getAppComponent().inject(this);

        this.presenter.initialize(this);

        this.mAdapter = new DictionariesAdapter(getContext(), presenter.getDictionaries());

        this.mdDctionariesListView.setAdapter(mAdapter);
        this.mdDctionariesListView.setOnItemClickListener(this::onItemClick);
        this.mdDctionariesListView.setOnItemLongClickListener(this::onItemLongClick);

        setHasOptionsMenu(true);

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.mDeleteMenuItem = menu.findItem(R.id.action_delete_item);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.performSelectDictionaryClick(position);
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ImageView itemMarker = (ImageView) view.findViewById(R.id.item_select_marker);
        this.presenter.toggleDictionaryCheck(position, itemMarker);
        return true;
    }

    private void onConfirmDeleteClick(DialogInterface dialog, int which) {
        this.presenter.deleteCheckedDictionary();
        this.presenter.resetCheck();
    }

    private void onConfirmCreateClick(View view) {
        String text = ((EditText) view.findViewById(R.id.dialog_item_name_edit_text)).getText().toString();
        this.presenter.createDictionary(text);
    }

    public void resetCheck(){
        this.presenter.resetCheck();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_add_dictionary) this.presenter.performMenuCreateClick();
        if (itemId == R.id.action_delete_item)    this.presenter.preformMenuDeleteClick();
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.presenter.detachScreen();
        unbinder.unbind();
    }

    /* Dictionaries screen specific behaviour */

    @Override
    public void showCreateDialog() {
        showCreateDialogWithCallback(getContext(), CREATE_DICTIONARY_DIALOG_TITLE, this::onConfirmCreateClick);
    }

    @Override
    public void showDeleteDialog() {
        showDeleteDialogWithCallback(getContext(), this::onConfirmDeleteClick);
    }

    @Override
    public void refreshDictionariesList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToDictionaryNotes(Dictionary dictionary) {
        OnDictionarySelectedListener activity = (OnDictionarySelectedListener)getActivity();
        activity.onDictionarySelected(dictionary);
    }

    /* Check delete list screen behaviour */

    @Override
    public void showDeleteButton() {
        mDeleteMenuItem.setVisible(true);
    }

    @Override
    public void hideDeleteButton() {
        mDeleteMenuItem.setVisible(false);
    }
}
