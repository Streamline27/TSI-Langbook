package lv.android.tsi.langbook.features;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.utilities.builders.CreateDialogBuilder;

/**
 * Created by Vladislav on 20.04.2017.
 */

public class CreateItemFeature {

    private Context mCtx;
    private String mDialogTitle;

    public CreateItemFeature(Context ctx, String dialogTitle) {
        this.mCtx = ctx;
        this.mDialogTitle = dialogTitle;
    }

    public void runCreateDialog(){
        CreateDialogBuilder.getBuilder(mCtx)
                .setTitle(mDialogTitle)
                .setCreateButtonClickListener(this::onCreateDialogConfirmButtonClick)
                .build().show();
    }

    private void onCreateDialogConfirmButtonClick(View view) {
        String text = ((EditText) view.findViewById(R.id.dialog_item_name_edit_text)).getText().toString();
        Toast.makeText(mCtx, text, Toast.LENGTH_SHORT).show();
    }
}
