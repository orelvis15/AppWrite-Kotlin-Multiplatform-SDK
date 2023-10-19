package io.appwrite.extensions

import java.io.ByteArrayOutputStream
import java.io.InputStream

fun InputStream.toByteArray(): ByteArray {
    val buffer = ByteArray(1024)
    val output = ByteArrayOutputStream()

    var bytesRead: Int
    while (this.read(buffer).also { bytesRead = it } != -1) {
        output.write(buffer, 0, bytesRead)
    }

    return output.toByteArray()
}