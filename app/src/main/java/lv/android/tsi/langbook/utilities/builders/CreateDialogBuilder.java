package lv.android.tsi.langbook.utilities.builders;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.R;

/**
 * Created by Vladislav on 19.04.2017.
 */

public class CreateDialogBuilder {

    @BindView(R.id.dialog_cancel_action) TextView mCancelTextView;
    @BindView(R.id.dialog_create_action) TextView mCreateTextView;
    @BindView(R.id.dialog_title_text)    TextView mTitleTextView;

    @BindString(R.string.dialog_title_default) String title;

    private Context ctx;
    private View view;

    private View.OnClickListener positiveButtonClickListener;


    public static CreateDialogBuilder getBuilder(Context ctx){
        return new CreateDialogBuilder(ctx);
    }

    public CreateDialogBuilder(Context ctx) {
        this.ctx = ctx;
        this.positiveButtonClickListener = v -> {};
        this.view = LayoutInflater.from(ctx).inflate(R.layout.dialog_new_item, null);

        ButterKnife.bind(this, view);
    }

    public AlertDialog build(){

        AlertDialog dialog = new AlertDialog.Builder(ctx).create();

        mTitleTextView.setText(title);

        mCreateTextView.setOnClickListener(v -> { this.positiveButtonClickListener.onClick(view); dialog.dismiss(); });
        mCancelTextView.setOnClickListener(v -> dialog.dismiss());

        dialog.setView(view);

        return dialog;
    }

    public CreateDialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateDialogBuilder setCreateButtonClickListener(View.OnClickListener positiveButtonClickListener) {
        this.positiveButtonClickListener = positiveButtonClickListener;
        return this;
    }

}
