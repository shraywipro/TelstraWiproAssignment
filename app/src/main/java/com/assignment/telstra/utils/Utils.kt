package com.assignment.telstra.utils

import android.app.AlertDialog
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.assignment.telstra.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @desc Utils class used for utility operations and methods
 */
@Singleton
open class Utils @Inject constructor(val context: Context) {

    /**
     * @desc This function is used to get the Day name from the supplied date
     */
    fun getDayNameFromDate(input: String) : String{
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = inFormat.parse(input)
        val outFormat = SimpleDateFormat("EEEE")
        val goal = outFormat.format(date)
        return goal
    }

    /**

     * @desc This function is used for checking internet connection
     */
    fun isNetworkAvailable(): Boolean {

        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        } catch (e: Exception) {
            return false
        }

    }

    /**
     * @desc This function is used to show Toast Message
     */
    fun showToast(respMsg: String) {
        try {
            if (context != null) {
                Toast.makeText(context, respMsg, Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * @desc This function is used to show Snakebar with provided message
     */
    fun showSnakeBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }

    fun showMessageDialog(context: Context, msg: String?) {
        val myAlertDialog = AlertDialog.Builder(context)
        myAlertDialog.setTitle(context.getString(R.string.dlg_title_alert))
        myAlertDialog.setCancelable(false)
        myAlertDialog.setMessage(msg)
        myAlertDialog.setPositiveButton(context.getString(R.string.dlg_btn_ok)) { dialog, which ->
            dialog.dismiss()
        }
        val dialog = myAlertDialog.create()
        dialog.show()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        if (target == null) {
            return false
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun getHeight(): ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 4..7) {
            for (j in 0..11) {
                if (i == 7 && j == 6) {
                    break
                } else
                    list.add("$i.$j")
            }
        }
        return list
    }

    fun getWeight(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 50..150) {
            list.add("$i")
        }
        return list
    }


    fun getDefaultAlbumArt(context: Context): Bitmap? {
        var bm: Bitmap? = null
        val options = BitmapFactory.Options()
        try {
            bm = BitmapFactory.decodeResource(context.resources,
                R.mipmap.ic_launcher, options)
        } catch (ee: Error) {
        } catch (e: Exception) {
        }

        return bm
    }

    fun getImagePathFromURI(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor!!.close()
            }
        }
    }

    fun getVideoPathFromURI(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Video.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

    fun getVideoIdFromURI(context: Context, contentUri: Uri): Int {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Video.Media._ID, MediaStore.Video.Media.TITLE, MediaStore.Video.Thumbnails.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            return cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

    fun getVideoThumb(context: Context, video_id: Int): String? {
        val thumbColumns = arrayOf(MediaStore.Video.Thumbnails.DATA)
        var thumbCursor: Cursor? = null
        var thumbPath: String? = null
        try {

            thumbCursor = context.getContentResolver().query(
                MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID + " = "
                        + video_id, null, null)

            if (thumbCursor!!.moveToFirst()) {
                thumbPath = thumbCursor.getString(thumbCursor
                    .getColumnIndex(MediaStore.Video.Thumbnails.DATA))

            }

        } catch (e: Exception) {

        } finally {
            if (thumbCursor != null && !thumbCursor.isClosed)
                thumbCursor.close()
        }
        return thumbPath
    }

}