package lv.android.tsi.langbook.screens.notes;

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
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lv.android.tsi.langbook.App;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.model.domain.Dictionary;
import lv.android.tsi.langbook.model.domain.Note;
import lv.android.tsi.langbook.screens.notes.presenter.NotesPresenter;
import lv.android.tsi.langbook.screens.notes.presenter.NotesScreen;
import lv.android.tsi.langbook.utilities.Constants;

import static lv.android.tsi.langbook.utilities.functions.DialogUtilities.showCreateDialogWithCallback;
import static lv.android.tsi.langbook.utilities.functions.DialogUtilities.showDeleteDialogWithCallback;

public class NotesFragment extends Fragment implements NotesScreen{

    @BindString(R.string.dialog_title_notes) String DIALOG_CREATE_NOTE_TITLE;

    @BindView(R.id.notes_list_view) ListView mNotesListView;
    private Unbinder unbinder;

    private MenuItem mDeleteMenuItem;

    private NotesAdapter mAdapter;

    @Inject NotesPresenter presenter;

    public static NotesFragment newInstance(Dictionary dictionary){
        NotesFragment f = new NotesFragment();

        Bundle args = new Bundle();
        args.putSerializable(Constants.DICTIONARY_EXTRA_KEY, dictionary);
        f.setArguments(args);

        return  f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        Dictionary dictionary = (Dictionary) getArguments().getSerializable(Constants.DICTIONARY_EXTRA_KEY);
        View listViewHeader = getInitializedHeaderView(dictionary, inflater);

        this.unbinder = ButterKnife.bind(this, view);
        ((App)getActivity().getApplication()).getAppComponent().inject(this);


        this.presenter.initialize(this, dictionary);

        this.mAdapter = new NotesAdapter(getContext(), this.presenter.getNotes());

        this.mNotesListView.addHeaderView(listViewHeader, null, false);
        this.mNotesListView.setOnItemClickListener(this::onItemClick);
        this.mNotesListView.setOnItemLongClickListener(this::onItemLongClick);
        this.mNotesListView.setAdapter(mAdapter);

        setHasOptionsMenu(true);

        return view;
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        position -= 1; // This solves first element header issue
        ImageView itemMarker = (ImageView) view.findViewById(R.id.item_select_marker);
        this.presenter.toggleNoteCheck(position, itemMarker);
        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position -= 1; // This solves first element header issue
        this.presenter.performSelectNoteClick(position);
        this.presenter.resetCheck();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notes, menu);
        this.mDeleteMenuItem = menu.findItem(R.id.action_delete_item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.presenter.detachScreen();
        this.unbinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) this.presenter.performUpButtonClick();
        if (id == R.id.action_add_note) this.presenter.performMenuCreateClick();
        if (id == R.id.action_delete_item) this.presenter.performMenuDeleteClick();
        return true;

    }

    private void onConfirmDeleteClick(DialogInterface dialog, int which){
        this.presenter.deleteCheckedNote();
        this.presenter.resetCheck();
    }

    private void onCreateDialogConfirmButtonClick(View view) {
        String text = ((EditText) view.findViewById(R.id.dialog_item_name_edit_text)).getText().toString();
        this.presenter.createNote(text);
    }

    public void onBackPressed(){
        this.presenter.performBackButtonClick();
    }


    /* Notes screen specific behaviour */

    @Override
    public void showDeleteButton() {
        mDeleteMenuItem.setVisible(true);
    }

    @Override
    public void hideDeleteButton() {
        mDeleteMenuItem.setVisible(false);
    }

    @Override
    public void showCreateDialog() {
        showCreateDialogWithCallback(getContext(), DIALOG_CREATE_NOTE_TITLE, this::onCreateDialogConfirmButtonClick);
    }


    @Override
    public void showDeleteDialog() {
        showDeleteDialogWithCallback(getContext(), this::onConfirmDeleteClick);
    }

    @Override
    public void refreshNotesList() {
        this.mAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToNoteContent(Note note) {
        OnNoteSelectedListener listener = (OnNoteSelectedListener) getActivity();
        listener.onNoteSelected(note);
    }

    @Override
    public void goUpToDictionaryList() {
        OnNoteSelectedListener listener = (OnNoteSelectedListener) getActivity();
        listener.onUpButtonPressed();
    }

    /* Private helper methods */

    private View getInitializedHeaderView(Dictionary dictionary, LayoutInflater inflater){
        View listViewHeader = inflater.inflate(R.layout.header_notes, null, false);
        TextView captionText = (TextView)listViewHeader.findViewById(R.id.header_notes_caption);

        captionText.setText(dictionary.getTitle());
        return listViewHeader;

    }

}
