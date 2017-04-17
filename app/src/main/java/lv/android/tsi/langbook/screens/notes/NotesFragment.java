package lv.android.tsi.langbook.screens.notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import lv.android.tsi.langbook.domain.Note;

public class NotesFragment extends Fragment {

    @BindView(R.id.notes_list_view) ListView mNotesListView;
    private Unbinder unbinder;

    private NotesAdapter adapter;
    private List<Note> notes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        View listViewHeader = inflater.inflate(R.layout.header_notes, null, false);

        this.unbinder = ButterKnife.bind(this, view);

        this.notes = getMockContent();

        this.adapter = new NotesAdapter(getContext(), notes);
        this.mNotesListView.addHeaderView(listViewHeader);
        this.mNotesListView.setOnItemClickListener(this::onNoteItemClick);
        this.mNotesListView.setAdapter(adapter);

        return view;
    }

    public void onNoteItemClick(AdapterView<?> parent, View view, int position, long id) {
        OnNoteSelectedListener listener = (OnNoteSelectedListener)getActivity();
        listener.onNoteSelected();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public List<Note> getMockContent() {
        List<Note> noteMocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) noteMocks.add(new Note(Integer.toString(i)));
        return noteMocks;
    }

    public interface OnNoteSelectedListener{
        void onNoteSelected();
    }

}
