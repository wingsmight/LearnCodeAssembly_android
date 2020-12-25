

package shared.turboeditor.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import shared.turboeditor.R;
import shared.turboeditor.util.GreatUri;

public class AdapterDrawer extends ArrayAdapter<GreatUri> {

    private final Callbacks callbacks;
    // Layout Inflater
    private final LayoutInflater inflater;
    // List of file details
    private final LinkedList<GreatUri> greatUris;
    private GreatUri selectedGreatUri = new GreatUri(Uri.EMPTY, "", "");

    public AdapterDrawer(Context context,
                         LinkedList<GreatUri> greatUris,
                         Callbacks callbacks) {
        super(context, R.layout.item_file_list, greatUris);
        this.greatUris = greatUris;
        this.inflater = LayoutInflater.from(context);
        this.callbacks = callbacks;
    }


    @Override
    public View getView(final int position,
                        View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater
                    .inflate(R.layout.item_drawer_list,
                            parent, false);
            final ViewHolder hold = new ViewHolder();
            hold.nameLabel = (TextView) convertView.findViewById(android.R.id.text1);
            hold.cancelButton = (ImageView) convertView.findViewById(R.id.button_remove_from_list);
            convertView.setTag(hold);

            final GreatUri greatUri = greatUris.get(position);
            final String fileName = greatUri.getFileName();
            hold.nameLabel.setText(fileName);
            hold.cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean closeOpenedFile = selectedGreatUri.getUri().equals(greatUri.getUri());
                    callbacks.CancelItem(position, closeOpenedFile);
                    if (closeOpenedFile)
                        selectPosition(new GreatUri(Uri.EMPTY, "", ""));

                }
            });

            if (selectedGreatUri.getUri().equals(greatUri.getUri())) {
                hold.nameLabel.setTypeface(null, Typeface.BOLD);
            } else {
                hold.nameLabel.setTypeface(null, Typeface.NORMAL);
            }

        } else {
            final ViewHolder hold = ((ViewHolder) convertView.getTag());
            final GreatUri greatUri = greatUris.get(position);
            final String fileName = greatUri.getFileName();
            hold.nameLabel.setText(fileName);
            hold.cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean closeOpenedFile = selectedGreatUri.getUri().equals(greatUri.getUri());
                    callbacks.CancelItem(position, closeOpenedFile);
                    if (closeOpenedFile)
                        selectPosition(new GreatUri(Uri.EMPTY, "", ""));

                }
            });

            if (selectedGreatUri.getUri().equals(greatUri.getUri())) {
                hold.nameLabel.setTypeface(null, Typeface.BOLD);
            } else {
                hold.nameLabel.setTypeface(null, Typeface.NORMAL);
            }

        }
        return convertView;
    }

    public void selectPosition(GreatUri greatUri) {
        //callbacks.ItemSelected(selectedPath);
        this.selectedGreatUri = greatUri;
        notifyDataSetChanged();
    }

    public interface Callbacks {
        void CancelItem(int position, boolean andCloseOpenedFile);

        //void ItemSelected(String path);
    }

    public static class ViewHolder {

        // Name of the file
        public TextView nameLabel;

        public ImageView cancelButton;
    }
}
