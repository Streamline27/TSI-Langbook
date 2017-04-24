package lv.android.tsi.langbook.screens.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.domain.Note;

/**
 * Created by Vladislav on 17.04.2017.
 */

public class NotesAdapter extends ArrayAdapter<Note> {

    private List<Note> notes;

    public NotesAdapter(@NonNull Context context, List<Note> notes) {
        super(context, 0, notes);
        this.notes = notes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);

        TextView captionText = (TextView) v.findViewById(R.id.item_note_caption);
        captionText.setText(notes.get(position).getCaption());

        return v;
    }
}
