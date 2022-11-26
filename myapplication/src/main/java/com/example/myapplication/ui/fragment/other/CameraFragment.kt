package com.example.myapplication.ui.fragment.other

import android.annotation.TargetApi
import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCameraBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class CameraFragment : Fragment(), View.OnClickListener {
    private var imageUri: Uri? = null
    private var binding: FragmentCameraBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding!!.btnTakePhoto.setOnClickListener(this)
        binding!!.btnChosePhotoFromablum.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_takePhoto -> takePhoto()
            R.id.btn_chosePhotoFromablum -> chosePhotoFromablum()
        }
    }

    private fun takePhoto() {
        val outputImage = File(Environment.getExternalStorageDirectory(), "portrait.png")
        if (outputImage.exists()) {
            outputImage.delete()
        }
        try {
            outputImage.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        imageUri = Uri.fromFile(outputImage)
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, TAKE_PHOTO)
    }

    private fun chosePhotoFromablum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, CHOSE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            TAKE_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                val intent = Intent("com.android.camera.action.CROP")
                intent.setDataAndType(imageUri, "image/*")
                intent.putExtra("scale", true)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(intent, CROP_PHOTO)
            }
            CHOSE_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {    //4.4以上的系统
                    handleImageOnKitKat(data)
                } else {
                    handleImageBeforeKitKat(data)
                }
            }
            CROP_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                try {
                    val bitmap = BitmapFactory.decodeStream(context!!.contentResolver.openInputStream(imageUri!!))
                    //                       Bitmap  newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//                        bitmap.setWidth(100);
//                        bitmap.setHeight(100);
                    binding!!.imgView.setImageBitmap(bitmap)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
            else -> {
            }
        }
    }

    @TargetApi(19)
    private fun handleImageBeforeKitKat(data: Intent?) {
        var imagePath: String? = null
        val uri = data!!.data
        if (DocumentsContract.isDocumentUri(context, uri)) {
            val docID = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents".equals(uri!!.authority, ignoreCase = true)) {
                val id = docID.split(":").toTypedArray()[1]
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents".equals(uri.authority, ignoreCase = true)) {
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(docID))
                imagePath = getImagePath(contentUri, null)
            } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                imagePath = getImagePath(uri, null)
            }
            displayImage(imagePath)
        }
    }

    private fun handleImageOnKitKat(data: Intent?) {
        val uri = data!!.data
        val imagePath = getImagePath(uri, null)
        displayImage(imagePath)
    }

    private fun getImagePath(uri: Uri?, selection: String?): String? {
        var path: String? = null
        val cursor = context!!.contentResolver.query(uri!!, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex("_data"))
            }
            cursor.close()
        }
        return path
    }

    private fun displayImage(imagePath: String?) {
        if (imagePath == null) {
            Toast.makeText(context, "failed to get image", Toast.LENGTH_LONG).show()
        } else {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            binding!!.imgView.setImageBitmap(bitmap)
        }
    }

    companion object {
        private const val TAKE_PHOTO = 1
        private const val CHOSE_PHOTO = 2
        private const val CROP_PHOTO = 3
    }
}