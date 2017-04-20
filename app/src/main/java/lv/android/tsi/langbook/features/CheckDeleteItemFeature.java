package lv.android.tsi.langbook.features;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import butterknife.BindString;
import butterknife.ButterKnife;
import lv.android.tsi.langbook.R;

/**
 * Created by Vladislav on 20.04.2017.
 */

public class CheckDeleteItemFeature {

    @BindString(R.string.dialog_delete_message) String DIALOG_DELETE_MESSAGE;
    @BindString(R.string.dialog_delete_title) String DIALOG_DELETE_TITLE;
    @BindString(R.string.dialog_delete_confirm_text) String DIALOG_DELETE_APROVE_TEXT;
    @BindString(R.string.dialog_delete_cancel_text) String DIALOG_DELETE_CANCEL_TEXT;


    private final ListAdapter mItemAdapter;

    private MenuItem mDeleteMenuItem;
    private Activity mContextActivity;

    private View mLastSelectedViewMarker;
    private int mLastSelectedPosition;

    private boolean checkModeEnabled;


    public CheckDeleteItemFeature(ListAdapter adapter, Activity activity) {
        this.mItemAdapter = adapter;
        this.mLastSelectedViewMarker = null;
        this.mContextActivity = activity;
        this.checkModeEnabled = false;
        this.mLastSelectedPosition = -1;

        ButterKnife.bind(this, mContextActivity);
    }

    public void toggleCheckOnItem(int position, View view){

        if (!checkModeEnabled)                      enterCheckMode(position, view);
        else if (mLastSelectedPosition == position) leaveCheckedMode();
        else                                        changeCheckedItem(position, view);
    }


    public AdapterView.OnItemLongClickListener getToggleCheckAction(){
        return (parent, view, position, id) -> {
            this.toggleCheckOnItem(position, view);
            return true;
        };
    }


    public void bindToggleableDeleteButton(Menu menu){
        this.mDeleteMenuItem = menu.findItem(R.id.action_delete_item);
    }


    public void runConfirmDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContextActivity);

        builder.setTitle(DIALOG_DELETE_TITLE)
                    .setMessage(DIALOG_DELETE_MESSAGE)
                    .setPositiveButton(DIALOG_DELETE_APROVE_TEXT, this::onConfirmDeleteClick)
                    .setNegativeButton(DIALOG_DELETE_CANCEL_TEXT, (dialog, which) -> {})
                    .create().show();
    }


    public void reset(){
        if (this.checkModeEnabled) leaveCheckedMode();
    }

    /*
         Private helper methods
         */
    private void onConfirmDeleteClick(DialogInterface dialog, int which) {

    }


    private void leaveCheckedMode(){
        uncheckCurrentItem();
        this.hideMenuDeleteButton();
        checkModeEnabled = false;
    }

    private void showMenuDeleteButton(){
        if (mDeleteMenuItem != null) mDeleteMenuItem.setVisible(true);

    }
    private void hideMenuDeleteButton(){
        if (mDeleteMenuItem != null) mDeleteMenuItem.setVisible(false);
    }


    private void changeCheckedItem(int position, View view) {
        mLastSelectedViewMarker.setVisibility(View.INVISIBLE);
        checkItem(position, view);
    }

    private void enterCheckMode(int position, View view) {
        checkItem(position, view);
        this.showMenuDeleteButton();
        checkModeEnabled = true;
    }

    private void uncheckCurrentItem() {
        mLastSelectedViewMarker.setVisibility(View.INVISIBLE);
        mLastSelectedViewMarker = null;
        mLastSelectedPosition = -1;
    }

    private void checkItem(int position, View view) {
        ImageView marker = (ImageView) view.findViewById(R.id.item_select_msrker);
        marker.setVisibility(View.VISIBLE);
        this.mLastSelectedPosition = position;
        this.mLastSelectedViewMarker = marker;
    }


}
