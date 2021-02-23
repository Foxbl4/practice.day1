package com.example.involtaday1.ui.image_gallery

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException


open class ImageGalleryFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img_gallery.apply {
            pick_gallery_btn.setOnClickListener { if (checkReadExternalStoragePermission()) selectImageInAlbum() }
            take_gallery_btn.setOnClickListener { if (checkCameraPermission()) takePhoto() }
        }
    }

    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, 1)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun selectImageInAlbum() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0)
    }

    private fun checkReadExternalStoragePermission(): Boolean {
        if ((ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED)
        ) return true
        else requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            0
        )
        return false
    }
    private fun checkCameraPermission(): Boolean {
        if ((ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED)
        ) return true
        else requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            2
        )
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val thumbnailBitmap = data?.extras?.get("data") as Bitmap
            img_gallery.setImageBitmap(thumbnailBitmap)
        }
        if (requestCode === 0 && resultCode === RESULT_OK) {
            val selectedImage = data?.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context?.contentResolver?.query(
                selectedImage!!,
                filePathColumn, null, null, null)
            cursor?.moveToFirst()
            val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
            val picturePath = cursor?.getString(columnIndex!!)
            cursor?.close()
            img_gallery.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }
}