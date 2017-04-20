package lv.android.tsi.langbook.screens.notes;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.domain.Note;
import lv.android.tsi.langbook.features.CheckDeleteItemFeature;
import lv.android.tsi.langbook.features.CreateItemFeature;

public class NotesFragment extends Fragment {

    @BindString(R.string.dialog_title_notes) String DIALOG_CREATE_NOTE_TITLE;

    @BindView(R.id.notes_list_view) ListView mNotesListView;
    private Unbinder unbinder;

    private NotesAdapter adapter;
    private List<Note> notes;

    private CreateItemFeature mCreateItemFeature;
    private CheckDeleteItemFeature mCheckDeleteFeature;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        View listViewHeader = inflater.inflate(R.layout.header_notes, null, false);
        setHasOptionsMenu(true);

        this.unbinder = ButterKnife.bind(this, view);

        this.notes = getMockContent();

        this.adapter = new NotesAdapter(getContext(), notes);

        this.mCreateItemFeature = new CreateItemFeature(getContext(), DIALOG_CREATE_NOTE_TITLE);
        this.mCheckDeleteFeature = new CheckDeleteItemFeature(adapter, getActivity());

        this.mNotesListView.addHeaderView(listViewHeader, null, false);
        this.mNotesListView.setOnItemClickListener(this::onNoteItemClick);
        this.mNotesListView.setOnItemLongClickListener(mCheckDeleteFeature.getToggleCheckAction());
        this.mNotesListView.setAdapter(adapter);


        return view;
    }

    public void onNoteItemClick(AdapterView<?> parent, View view, int position, long id) {

        OnNoteSelectedListener listener = (OnNoteSelectedListener) getActivity();
        listener.onNoteSelected();
        this.mCheckDeleteFeature.reset();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notes, menu);
        mCheckDeleteFeature.bindToggleableDeleteButton(menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) ((OnNoteSelectedListener)getActivity()).onUpButtonPressed();
        if (id == R.id.action_add_note) mCreateItemFeature.runCreateDialog();
        if (id == R.id.action_delete_item) mCheckDeleteFeature.runConfirmDeleteDialog();
        return true;

    }


    private void onCreateDialogCreateButtonClick(View view){
        String text = ((EditText) view.findViewById(R.id.dialog_item_name_edit_text)).getText().toString();
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public interface OnNoteSelectedListener{
        void onNoteSelected();
        void onUpButtonPressed();
    }


    /*
        Private helper methods
        */

    private List<Note> getMockContent() {
        List<Note> noteMocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) noteMocks.add(new Note(Integer.toString(i)));
        return noteMocks;
    }

}
