

package shared.turboeditor.dialogfragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment

import shared.turboeditor.R
import shared.turboeditor.util.FragmentArgumentDelegate
import shared.turboeditor.util.GreatUri
import shared.turboeditor.views.DialogHelper

class SaveFileDialog : DialogFragment() {

    private var uri: GreatUri by FragmentArgumentDelegate()
    private var text: String by FragmentArgumentDelegate()
    private var encoding: String by FragmentArgumentDelegate()
    private var openNewFileAfter: Boolean by FragmentArgumentDelegate()
    private var newUri: GreatUri by FragmentArgumentDelegate()

    private var saveDialogCallback: ISaveDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        saveDialogCallback = context as ISaveDialog
    }

    override fun onDetach() {
        super.onDetach()

        saveDialogCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = DialogHelper.Builder(activity)
                .setIcon(ResourcesCompat.getDrawable(resources, R.drawable.ic_action_save, activity?.theme))
                .setTitle(R.string.salva)
                .setMessage(String.format(getString(R.string.save_changes), uri.fileName))
                .createCommonView()

        return AlertDialog.Builder(activity)
                .setView(view)
                .setPositiveButton(R.string.salva
                ) { _, _ ->
                    if (uri.fileName?.isEmpty() == true) {
                        val dialogFrag = NewFileDetailsDialog(uri, text, encoding)
                        dialogFrag.show(fragmentManager!!, "dialog")
                    } else {
                        saveDialogCallback?.startSavingFile(uri, text, encoding)
                    }
                }
                .setNeutralButton(android.R.string.cancel, null)
                .setNegativeButton(R.string.no
                ) { _, _ ->
                    saveDialogCallback?.userDoesNotWantToSave(
                            openNewFileAfter, newUri
                    )
                }
                .create()
    }

    interface ISaveDialog {
        fun userDoesNotWantToSave(openNewFile: Boolean, newUri: GreatUri)

        fun startSavingFile(uri: GreatUri, text: String, encoding: String)
    }

    companion object {

        fun create(uri: GreatUri?, text: String?, encoding: String?, openNewFileAfter: Boolean = false,
                   newUri: GreatUri = GreatUri(Uri.EMPTY, "", "")) =

            SaveFileDialog().apply {
                if (uri != null)
                    this.uri = uri
                if (text != null)
                    this.text = text
                if (encoding != null)
                    this.encoding = encoding
                this.openNewFileAfter = openNewFileAfter
                this.newUri = newUri
            }
    }
}