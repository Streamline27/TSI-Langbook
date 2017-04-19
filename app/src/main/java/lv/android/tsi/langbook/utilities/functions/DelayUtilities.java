package lv.android.tsi.langbook.utilities.functions;

import android.os.Handler;
import android.view.View;

/**
 * Created by Vladislav on 19.04.2017.
 */

public class DelayUtilities {

    public static void executeAfterShortDelay(Runnable task){
        Handler handler = new Handler();
        handler.postDelayed(task, 100);
    }

    public static void makeVisibleAfterDelay(View vIew, long delay){
        Handler handler = new Handler();
        Runnable task = () -> { if (vIew != null) vIew.setVisibility(View.VISIBLE); };
        handler.postDelayed(task, delay);
    }
}
