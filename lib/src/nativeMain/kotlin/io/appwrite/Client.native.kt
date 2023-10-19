package io.appwrite

actual class Client private actual constructor(
    endPoint: String,
    endPointRealtime: String?,
    selfSigned: Boolean
){
    data class Builder(
        var endPoint: String = "https://HOSTNAME/v1",
        var endPointRealtime: String? = null,
        var selfSigned: Boolean = false
    )
}