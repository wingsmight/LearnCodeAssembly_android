

package shared.turboeditor.util

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

import java.io.File
import java.util.Objects

/**
 * Created by mac on 19/03/15.
 */
data class GreatUri(var uri: Uri?, var filePath: String?, var fileName: String?) : Parcelable {

    val parentFolder: String?
        get() = File(filePath!!).parent

    val isReadable: Boolean
        get() = File(filePath!!).canRead()

    val isWritable: Boolean
        get() = File(filePath!!).canWrite()

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Uri::class.java.classLoader),
            parcel.readString(),
            parcel.readString())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val greatUri = other as GreatUri?
        return uri == greatUri!!.uri
    }

    override fun hashCode(): Int {
        return Objects.hash(uri)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(uri, flags)
        parcel.writeString(filePath)
        parcel.writeString(fileName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GreatUri> {
        override fun createFromParcel(parcel: Parcel): GreatUri {
            return GreatUri(parcel)
        }

        override fun newArray(size: Int): Array<GreatUri?> {
            return arrayOfNulls(size)
        }
    }
}
