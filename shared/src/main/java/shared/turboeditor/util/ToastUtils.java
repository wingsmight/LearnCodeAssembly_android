

package shared.turboeditor.util;

import android.content.Context;
import androidx.annotation.NonNull;
import android.widget.Toast;

/**
 * Helper class with utils related to toasts (no bacon.)
 *
 * @author Artem Chepurnoy
 */
public class ToastUtils {

    /**
     * Shows toast message with given message shortly.
     *
     * @param text message to show
     * @see #showLong(android.content.Context, CharSequence)
     */
    @NonNull
    public static Toast showShort(@NonNull Context context, @NonNull CharSequence text) {
        return show(context, text, Toast.LENGTH_SHORT);
    }

    @NonNull
    public static Toast showShort(@NonNull Context context, int stringRes) {
        return showShort(context, context.getString(stringRes));
    }

    /**
     * Shows toast message with given message for a long time.
     *
     * @param text message to show
     * @see #showShort(android.content.Context, CharSequence)
     */
    @NonNull
    public static Toast showLong(@NonNull Context context, @NonNull CharSequence text) {
        return show(context, text, Toast.LENGTH_LONG);
    }

    @NonNull
    public static Toast showLong(@NonNull Context context, int stringRes) {
        return showLong(context, context.getString(stringRes));
    }

    @NonNull
    private static Toast show(@NonNull Context context, CharSequence text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return toast;
    }

}
