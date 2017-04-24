package lv.android.tsi.langbook.interactions;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Natasa on 22.04.2017.
 */

public class CheckDeleteInteraction {

    private CheckDeleteScreen mScreen;

    private int  mLastSelectedPosition;
    private ImageView mLastSelectedItemMarker;

    private boolean mCheckModeEnabled;


    public CheckDeleteInteraction() {

        this.mLastSelectedItemMarker = null;
        this.mLastSelectedPosition = -1;

        this.mCheckModeEnabled = false;
    }

    public void toggleItemCheck(int position, ImageView marker){
        if      (checkModeDisabled())    enterCheckMode(position, marker);
        else if (isReselected(position)) leaveCheckedMode();
        else                             changeCheckedItemTo(position, marker);
    }

    public int getLastSelectedPosition(){
        return this.mLastSelectedPosition;
    }

    public void reset(){
        if (mCheckModeEnabled) leaveCheckedMode();
    }

    public void attachScreen(CheckDeleteScreen screen){
        this.mScreen = screen;
    }

    public void detachScreen(){
        this.mScreen = null;
    }

    /*
        Private helper methods
     */

    private void changeCheckedItemTo(int position, ImageView marker) {
        this.hideMarker(mLastSelectedItemMarker);
        this.showMarker(marker);

        this.mLastSelectedPosition = position;
        this.mLastSelectedItemMarker = marker;
    }



    private void leaveCheckedMode() {
        this.hideMarker(mLastSelectedItemMarker);
        this.mScreen.hideDeleteButton();

        this.mLastSelectedPosition = -1;
        this.mLastSelectedItemMarker = null;

        this.mCheckModeEnabled = false;
    }


    private void enterCheckMode(int position, ImageView marker) {
        this.mScreen.showDeleteButton();
        this.showMarker(marker);

        this.mLastSelectedPosition = position;
        this.mLastSelectedItemMarker = marker;

        this.mCheckModeEnabled = true;
    }


    private boolean isReselected(int position) {
        return mLastSelectedPosition == position;
    }


    private boolean checkModeDisabled(){
        return !this.mCheckModeEnabled;
    }


    private void hideMarker(ImageView marker) {
        marker.setVisibility(View.INVISIBLE);
    }

    private void showMarker(ImageView marker) {
        marker.setVisibility(View.VISIBLE);
    }

}
