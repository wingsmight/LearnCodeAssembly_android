

package shared.turboeditor.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import shared.turboeditor.R;
import shared.turboeditor.views.DialogHelper;

/**
 * Dialog fragment that shows some info about this application.
 *
 * @author Artem Chepurnoy
 */
public class AboutDialog extends DialogFragment {

    private static final String VERSION_UNAVAILABLE = "N/A";

    /**
     * Merges app name and version name into one.
     */
    public static CharSequence getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        String versionName;
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            versionName = info.versionName;

            // Make the info part of version name a bit smaller.
            if (versionName.indexOf('-') >= 0) {
                versionName = versionName.replaceFirst("\\-", "<small>-") + "</small>";
            }
        } catch (PackageManager.NameNotFoundException e) {
            versionName = VERSION_UNAVAILABLE;
        }

        Resources res = context.getResources();
        return Html.fromHtml(
                res.getString(R.string.about_title,
                        res.getString(R.string.nome_app_turbo_editor), versionName)
        );
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        assert context != null;

        CharSequence message = Html.fromHtml(getString(
                R.string.about_message));

        View view = new DialogHelper.Builder(context)
                .setIcon(getResources().getDrawable(R.drawable.ic_launcher))
                .setTitle(getVersionName(context))
                .setMessage(message)
                .createCommonView();

        return new AlertDialog.Builder(context)
                .setView(view)
                .setNeutralButton(R.string.close, null)
                .create();
    }
}
