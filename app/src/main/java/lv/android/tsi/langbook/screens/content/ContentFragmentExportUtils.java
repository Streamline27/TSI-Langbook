package lv.android.tsi.langbook.screens.content;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import lv.android.tsi.langbook.R;

/**
 * Created by Vladislav on 19.04.2017.
 */

public class ContentFragmentExportUtils {

    public static void setActionBarHomeButtonIconDefault(AppCompatActivity activity){
        activity.getSupportActionBar().setHomeAsUpIndicator(null);
    }

    public static void setActionBarHomeButtonIconOkMark(AppCompatActivity activity){
        activity.getSupportActionBar().setHomeAsUpIndicator(R.mipmap.check_img);
    }
}
