package lv.android.tsi.langbook.utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Strea on 26.04.2017.
 */

public class ImageUtilities {
    /**
     *
     * @return AbsoulutePath + fileName
     */
    public static String saveImage(Context ctx, Bitmap image, String fileName){
        ContextWrapper cw = new ContextWrapper(ctx);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, fileName);

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(mypath);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
    }

    public static Bitmap loadImage(Context ctx, String filePath){
        return BitmapFactory.decodeFile(filePath);

    }
}
