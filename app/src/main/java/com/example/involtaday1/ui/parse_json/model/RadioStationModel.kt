package com.example.involtaday1.ui.parse_json.model

import android.net.Uri


class RadioStationModel(val name: String, val streamLink: String, val imageUrl: String) {
    var isSelected: Boolean = false

    fun getStreamLinkUri(): Uri? {
        return try {
            Uri.parse(streamLink)
        } catch (ex: Exception) {
            null
        }
    }

    fun getImageUri(): Uri? {
        return try {
            Uri.parse(imageUrl)
        } catch (ex: Exception) {
            null
        }
    }
}