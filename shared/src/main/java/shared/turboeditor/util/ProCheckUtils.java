

package shared.turboeditor.util;

import android.content.Context;

public class ProCheckUtils {
    public static boolean isPro(Context context, boolean includeDonations) {

        // happy new year
        return context.getPackageName().equals("com.maskyn.fileeditorpro");
        /*
        if (Build.FOR_AMAZON)
            return true;
        else if ()
            return true;
        else if (includeDonations && PreferenceHelper.hasDonated(context))
            return true;
        else
            return false;*/
    }

    public static boolean isPro(Context context) {
        return isPro(context, true);
    }
}
