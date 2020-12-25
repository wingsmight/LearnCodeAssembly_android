

package shared.turboeditor.dialogfragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.EditText

import androidx.fragment.app.DialogFragment

import java.io.File
import java.io.IOException

import shared.turboeditor.R
import shared.turboeditor.home.MainActivity
import shared.turboeditor.preferences.PreferenceHelper
import shared.turboeditor.util.GreatUri
import shared.turboeditor.views.DialogHelper

// ...
@SuppressLint("ValidFragment")
class NewFileDetailsDialog(
        var currentUri: GreatUri,
        var fileText: String,
        var fileEncoding: String) : DialogFragment() {

    private var mName: EditText? = null
    private var mFolder: EditText? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = DialogHelper.Builder(activity)
                .setTitle(R.string.save_as)
                .setView(R.layout.dialog_fragment_new_file_details)
                .createSkeletonView()

        this.mName = view.findViewById(android.R.id.text1)
        this.mFolder = view.findViewById(android.R.id.text2)

        val noName = TextUtils.isEmpty(currentUri.fileName)
        val noPath = TextUtils.isEmpty(currentUri.filePath)

        if (noName) {
            this.mName!!.setText(".txt")
        } else {
            this.mName!!.setText(currentUri.fileName)
        }
        if (noPath) {
            this.mFolder!!.setText(PreferenceHelper.getWorkingFolder(activity))
        } else {
            this.mFolder!!.setText(currentUri.parentFolder)
        }

        // Show soft keyboard automatically
        this.mName!!.requestFocus()
        this.mName!!.setSelection(0)
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        return AlertDialog.Builder(activity)
                .setView(view)
                .setPositiveButton(android.R.string.ok
                ) { dialog, which ->

                    if (!mName!!.text.toString().isEmpty() && !mFolder!!.text.toString().isEmpty()) {

                        val file = File(mFolder!!.text.toString(), mName!!.text.toString())
                        try {
                            file.parentFile!!.mkdirs()
                            file.createNewFile()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        val newUri = GreatUri(Uri.fromFile(file), file.absolutePath, file.name)
                        if (activity != null) {
                            (activity as MainActivity).startSavingFile(newUri, fileText, fileEncoding)
                        }
                    }
                }
                .setNegativeButton(android.R.string.cancel
                ) { dialog, which ->

                }
                .create()
    }

}