

package shared.turboeditor.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import androidx.documentfile.provider.DocumentFile;
import android.view.View;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Date;

import shared.turboeditor.R;
import shared.turboeditor.adapter.AdapterTwoItem;
import shared.turboeditor.util.AccessStorageApi;
import shared.turboeditor.util.Device;
import shared.turboeditor.views.DialogHelper;

// ...
public class FileInfoDialog extends DialogFragment {

    public static FileInfoDialog newInstance(Uri uri) {
        final FileInfoDialog f = new FileInfoDialog();
        final Bundle args = new Bundle();
        args.putParcelable("uri", uri);
        f.setArguments(args);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = new DialogHelper.Builder(getActivity())
                .setTitle(R.string.info)
                .setView(R.layout.dialog_fragment_file_info)
                .createSkeletonView();
        //final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_file_info, null);

        ListView list = (ListView) view.findViewById(android.R.id.list);

        DocumentFile file = DocumentFile.fromFile(new File(AccessStorageApi.getPath(getActivity(), (Uri) getArguments().getParcelable("uri"))));

        if (file == null && Device.hasKitKatApi()) {
            file = DocumentFile.fromSingleUri(getActivity(), (Uri) getArguments().getParcelable("uri"));
        }

        // Get the last modification information.
        Long lastModified = file.lastModified();

        // Create a new date object and pass last modified information
        // to the date object.
        Date date = new Date(lastModified);

        String[] lines1 = {
                getString(R.string.name),
                //getString(R.string.folder),
                getString(R.string.size),
                getString(R.string.modification_date)
        };
        String[] lines2 = {
                file.getName(),
                //file.getParent(),
                FileUtils.byteCountToDisplaySize(file.length()),
                date.toString()
        };

        list.setAdapter(new AdapterTwoItem(getActivity(), lines1, lines2));


        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }
                )
                .create();
    }
}