package lv.android.tsi.langbook.utilities;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Natasa on 24.04.2017.
 */

public class IntentUtilities {

    public static Intent getTwitterIntent(String tweet){

        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.setClassName("com.twitter.android", "com.twitter.android.composer.ComposerActivity");
        tweetIntent.setType("text/plain");
        tweetIntent.putExtra(Intent.EXTRA_TEXT, tweet);

        return tweetIntent;
    }

    public static Intent getImageRequestIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        return intent;
    }
}
