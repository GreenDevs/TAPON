package com.crackdeveloperz.tapon.utility;

import android.app.Activity;
import android.os.Build;
import android.view.View;

/**
 * Created by trees on 8/21/15.
 */
public class Utility
{

    public static void hideNagivation(Activity context) {
        View decor_View = context.getWindow().getDecorView();
        int ui_Options = 0;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            ui_Options = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        } else {
            ui_Options = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        decor_View.setSystemUiVisibility(ui_Options);
    }
}
