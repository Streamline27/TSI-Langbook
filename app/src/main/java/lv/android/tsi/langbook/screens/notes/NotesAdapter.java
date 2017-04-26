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

import butterknife.BindView;
import butterknife.ButterKnife;
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

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder h = (ViewHolder) convertView.getTag();
        h.captionText.setText(notes.get(position).getCaption());

        return convertView;
    }

    class ViewHolder{

        @BindView(R.id.item_note_caption) TextView captionText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
