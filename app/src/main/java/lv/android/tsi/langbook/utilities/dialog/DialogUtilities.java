package lv.android.tsi.langbook.utilities.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import lv.android.tsi.langbook.R;
import lv.android.tsi.langbook.utilities.dialog.CreateDialogBuilder;

/**
 * Created by Natasa on 22.04.2017.
 */

public class DialogUtilities {

    public static void showDeleteDialogWithCallback(Context ctx, DialogInterface.OnClickListener positiveClickAction){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setTitle(R.string.dialog_delete_title)
                .setMessage(R.string.dialog_delete_message)
                .setPositiveButton(R.string.dialog_delete_confirm_text, positiveClickAction)
                .setNegativeButton(R.string.dialog_delete_cancel_text, (dialog, which) -> {})
                .create().show();
    }

    public static void showCreateDialogWithCallback(Context ctx,
                                                    String title,
                                                    View.OnClickListener positiveClickAction){
        CreateDialogBuilder.getBuilder(ctx)
                .setTitle(title)
                .setCreateButtonClickListener(positiveClickAction)
                .build().show();


    }

}
