

package shared.turboeditor.util;

import android.app.Activity;

import shared.turboeditor.R;
import shared.turboeditor.preferences.PreferenceHelper;

public class ThemeUtils {

    public static void setTheme(Activity activity){
        boolean light = PreferenceHelper.getLightTheme(activity);
        if (light) {
            activity.setTheme(R.style.AppThemeBaseLight);
        } else {
            activity.setTheme(R.style.AppThemeBaseDark);
        }
    }

    public static void setWindowsBackground(Activity activity) {
        boolean whiteTheme = PreferenceHelper.getLightTheme(activity);
        boolean darkTheme = PreferenceHelper.getDarkTheme(activity);
        boolean blackTheme = PreferenceHelper.getBlackTheme(activity);
        if (whiteTheme) {
            activity.getWindow().setBackgroundDrawableResource(R.color.window_background_light);
        } else if (darkTheme) {
            activity.getWindow().setBackgroundDrawableResource(R.color.window_background);
        } else {
            activity.getWindow().setBackgroundDrawableResource(android.R.color.black);
        }
    }
}
