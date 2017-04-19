package lv.android.tsi.langbook.utilities.functions;

import android.app.Activity;

import lv.android.tsi.langbook.R;

/**
 * Created by Vladislav on 19.04.2017.
 */

public class AnimationUtilities {
    public static void setPendingTransitionAnimationNone(Activity activity){
        activity.overridePendingTransition(0, 0);
    }

    public static void setPendingTransitionFromRightToLeft(Activity activity){
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public static void setPendingTransitionFromLeftToRight(Activity activity){
        activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
