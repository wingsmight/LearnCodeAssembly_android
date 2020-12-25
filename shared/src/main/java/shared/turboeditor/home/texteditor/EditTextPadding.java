

package shared.turboeditor.home.texteditor;

import android.content.Context;

import shared.turboeditor.preferences.PreferenceHelper;
import shared.turboeditor.util.PixelDipConverter;

public class EditTextPadding {

    public static int getPaddingWithoutLineNumbers(Context context) {
        return (int) PixelDipConverter.convertDpToPixel(5, context);
    }

    public static int getPaddingBottom(Context context) {
        boolean useAccessoryView = PreferenceHelper.getUseAccessoryView(context);
        return (int) PixelDipConverter.convertDpToPixel(useAccessoryView ? 50 : 0, context);
    }

    public static int getPaddingWithLineNumbers(Context context, float fontSize) {
        return (int) PixelDipConverter.convertDpToPixel(fontSize * 2f, context);
    }

    public static int getPaddingTop(Context context) {
        return getPaddingWithoutLineNumbers(context);
    }
}
