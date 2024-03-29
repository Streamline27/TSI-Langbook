package lv.android.tsi.langbook.screens.dictionaries;

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
import lv.android.tsi.langbook.domain.Dictionary;

/**
 * Created by Vladislav on 16.04.2017.
 */

public class DictionariesAdapter extends ArrayAdapter<Dictionary> {

    private List<Dictionary> dictionaries;

    public DictionariesAdapter(@NonNull Context context, List<Dictionary> dictionaries) {
        super(context, 0, dictionaries);

        this.dictionaries = dictionaries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_dictionary, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder h = (ViewHolder) convertView.getTag();
        h.captionText.setText(dictionaries.get(position).getTitle());

        return convertView;
    }

    class ViewHolder{

        @BindView(R.id.item_dictionary_caption) TextView captionText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
