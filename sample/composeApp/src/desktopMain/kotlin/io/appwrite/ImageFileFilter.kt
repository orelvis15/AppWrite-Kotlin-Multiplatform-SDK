package io.appwrite

import java.io.File
import java.io.FilenameFilter
import java.util.*

class ImageFileFilter : FilenameFilter {
    private val allowedExtensions = arrayOf("jpg", "jpeg", "png", "gif")
    override fun accept(dir: File, name: String): Boolean {
        for (extension in allowedExtensions) {
            if (name.lowercase(Locale.getDefault()).endsWith(".$extension")) {
                return true
            }
        }
        return false
    }
}