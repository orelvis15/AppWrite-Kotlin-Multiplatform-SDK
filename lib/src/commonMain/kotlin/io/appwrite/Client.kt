package io.appwrite

expect class Client private constructor(
    endPoint: String = "https://HOSTNAME/v1",
    endPointRealtime: String? = null,
    selfSigned: Boolean = false
)