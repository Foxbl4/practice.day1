package com.example.involtaday1.ui.image_gallery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.involtaday1.R
import com.kbeanie.multipicker.api.ImagePicker
import com.kbeanie.multipicker.api.Picker
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback
import com.kbeanie.multipicker.api.entity.ChosenImage
import kotlinx.android.synthetic.main.fragment_gallery.*


class ImageGalleryFragment: Fragment() {

    private val imagePicker = ImagePicker(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bPickPhoto.setOnClickListener {
            imagePicker.setImagePickerCallback(object : ImagePickerCallback {
                override fun onImagesChosen(images: List<ChosenImage>) {
                    // Display images
                }

                override fun onError(message: String) {
                    // Do error handling
                }
            }
            )
            imagePicker.pickImage()
        }
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                imagePicker.submit(data)
            }
        }
    }

}