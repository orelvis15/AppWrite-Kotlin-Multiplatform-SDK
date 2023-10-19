package io.appwrite

import Multiplatform_Appwrite_SDK.lib.BuildConfig
import io.appwrite.cookies.CustomCookiesStorage
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.parseResponse
import io.appwrite.models.common.Part
import io.appwrite.models.storage.InputFile
import io.appwrite.models.storage.UploadProgress
import io.appwrite.serialization.toJsonObject
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.core.*
import io.ktor.utils.io.streams.*
import kotlinx.serialization.json.*
import okhttp3.OkHttpClient
import java.io.File
import java.io.RandomAccessFile
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

actual class Client actual constructor(
    var endPoint: String,
    var endPointRealtime: String?,
    var selfSigned: Boolean
) {

    var httpClient: HttpClient

    init {
        httpClient = buildClient()
    }

    companion object {
        const val CHUNK_SIZE = 5 * 1024 * 1024 // 5MB

        //Headers
        const val END_POINT_HEADER = "x-appwrite-endpoint"
        const val END_POINT_REALTIME_HEADER = "x-appwrite-endpoint-realtime"
        const val SELF_SIGNED_HEADER = "x-appwrite-self-signed"
        const val PROJECT_HEADER = "x-appwrite-project"
        const val JWT_HEADER = "x-appwrite-jwt"
        const val LOCALE_HEADER = "x-appwrite-locale"
    }

    val customHeaders = mutableMapOf(
        "origin" to "appwrite-kotlin-multiplatform://${BuildConfig.APP_PACKAGE}",
        "user-agent" to "AppwriteKotlinMultiplatformSDK/${BuildConfig.APP_VERSION}",
        "x-sdk-name" to "KotlinMultiplatform",
        "x-sdk-platform" to "client",
        "x-sdk-language" to "Kotlin",
        "x-sdk-version" to BuildConfig.APP_VERSION,
        "x-appwrite-response-format" to BuildConfig.APPWRITE_RESPONSE_FORMAT,
    )

    fun endPoint(endPoint: String) = apply { customHeaders[END_POINT_HEADER] = endPoint }
    fun endPointRealtime(endPointRealtime: String) =
        apply { customHeaders[END_POINT_REALTIME_HEADER] = endPointRealtime }
    fun setSelfSigned(selfSigned: Boolean) = apply { customHeaders[SELF_SIGNED_HEADER] = selfSigned.toString() }
    fun project(value: String) = apply { customHeaders[PROJECT_HEADER] = value }
    fun getProject() = customHeaders[PROJECT_HEADER]
    fun setJWT(value: String) = apply { customHeaders[JWT_HEADER] = value }
    fun setLocale(value: String) = apply { customHeaders[LOCALE_HEADER] = value }

    private fun buildClient(): HttpClient {

        return HttpClient(OkHttp) {
            install(WebSockets) {
                this@HttpClient.engine {
                    preconfigured = OkHttpClient.Builder()
                        .pingInterval(20, TimeUnit.SECONDS)
                        .build()
                }
            }

            install(DefaultRequest) {
                customHeaders.forEach {
                    header(it.key, it.value)
                }
            }

            install(HttpCookies) {
                storage = CustomCookiesStorage
            }

            install(ContentNegotiation) {
                json()
            }

            engine {
                config {
                    trustCerts()
                }
            }
        }
    }

    private fun OkHttpClient.Builder.trustCerts() {
        if (selfSigned) {
            val trustAllCerts =
                @Suppress("CustomX509TrustManager")
                object : X509TrustManager {
                    @Suppress("TrustAllX509TrustManager")
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    @Suppress("TrustAllX509TrustManager")
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }

            val sslContext = SSLContext.getInstance("SSL")
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            sslSocketFactory(sslSocketFactory, trustAllCerts)
        }
    }

    @JvmOverloads
    suspend fun <T> call(
        method: String,
        path: String,
        headers: Map<String, String> = mapOf(),
        params: Map<String, Any?> = mapOf(),
        responseType: Class<T>? = null,
        converter: ((Any) -> T)? = null
    ): T {

        val filteredParams = params.filterValues { it != null }

        customHeaders.putAll(headers)

        val response: HttpResponse = httpClient.request("$endPoint/$path") {
            this.method = HttpMethod.parse(method)
            this.contentType(ContentType.parse(headers["content-type"] ?: "application/json"))

            if (HttpMethod.parse(method) == HttpMethod.Get) {
                filteredParams.forEach {
                    if (it.value is List<*>) {
                        val list = it.value as List<*>
                        for (index in list.indices) {
                            parameter("${it.key}[]", it.value.toString())
                        }
                    } else {
                        parameter(it.key, it.value.toString())
                    }
                }
            } else {
                if (ContentType.MultiPart.FormData == ContentType.parse(
                        headers["content-type"] ?: "application/json"
                    )
                ) {
                    val formData = MultiPartFormDataContent(
                        formData {
                            filteredParams.forEach {
                                when {
                                    it.key == "file" -> {
                                        append("file", (it.value as Part).data, Headers.build {
                                            append(
                                                HttpHeaders.ContentDisposition,
                                                "filename=\"${(it.value as Part).fileName}\""
                                            )
                                        })
                                    }

                                    it.value is List<*> -> {
                                        val list = it.value as List<*>
                                        for (index in list.indices) {
                                            append("${it.key}[]", list[index].toString())
                                        }
                                    }

                                    else -> {
                                        append(it.key, it.value.toString())
                                    }
                                }
                            }
                        }
                    )
                    setBody(formData)
                } else {
                    setBody(Json.encodeToJsonElement(filteredParams.toJsonObject()))
                }
            }
        }

        if (response.status.isSuccess()) {
            try {
                return response.parseResponse(responseType, converter)
            } catch (e: Exception) {
                throw AppwriteException(
                    message = e.message,
                    code = response.status.value,
                    response = response.bodyAsText()
                )
            }

        } else {
            throw AppwriteException(
                code = response.status.value,
                response = response.bodyAsText()
            )
        }
    }

    /**
     * Upload a file in chunks
     *
     * @param path
     * @param headers
     * @param params
     *
     * @return [T]
     */
    @Throws(AppwriteException::class)
    suspend fun <T> chunkedUpload(
        path: String,
        headers: MutableMap<String, String>,
        params: MutableMap<String, Any?>,
        converter: ((Any) -> T),
        paramName: String,
        idParamName: String? = null,
        onProgress: ((UploadProgress) -> Unit)? = null,
    ): T {
        var file: RandomAccessFile? = null
        val input = params[paramName] as InputFile
        val size: Long = when (input.sourceType) {
            "path", "file" -> {
                file = RandomAccessFile(input.path, "r")
                file.length()
            }

            "bytes" -> {
                (input.data as ByteArray).size.toLong()
            }

            else -> throw UnsupportedOperationException()
        }

        if (size < CHUNK_SIZE) {
            val data = when (input.sourceType) {
                "file", "path" -> File(input.path)
                "bytes" -> File.createTempFile("temp", ".bin").apply {
                    writeBytes(input.data as ByteArray)
                }

                else -> throw UnsupportedOperationException()
            }
            params[paramName] = Part(input.filename, data.readBytes())
            return call(
                method = "POST",
                path = path,
                headers = headers,
                params = params,
                converter = converter
            )
        }

        val buffer = ByteArray(CHUNK_SIZE)
        var offset = 0L
        var result: Map<*, *>? = null

        if (idParamName?.isNotEmpty() == true && params[idParamName] != ID.unique()) {
            //Check if a file already exists
            val current: Map<*, *> = call(
                method = "GET",
                path = "$path/${params[idParamName]}",
                headers = headers,
                params = emptyMap()
            )
            val chunksUploaded = current["chunksUploaded"] as Long
            offset = chunksUploaded * CHUNK_SIZE
        }

        while (offset < size) {
            when (input.sourceType) {
                "file", "path" -> {
                    file?.seek(offset)
                    file?.read(buffer)
                }

                "bytes" -> {
                    val end = if (offset + CHUNK_SIZE < size) {
                        offset + CHUNK_SIZE - 1
                    } else {
                        size - 1
                    }
                    (input.data as ByteArray).copyInto(
                        buffer,
                        startIndex = offset.toInt(),
                        endIndex = end.toInt()
                    )
                }

                else -> throw UnsupportedOperationException()
            }

            params[paramName] = Part(input.filename, buffer)

            headers["Content-Range"] =
                "bytes $offset-${((offset + CHUNK_SIZE) - 1).coerceAtMost(size - 1)}/$size"

            result = call(
                method = "POST",
                path = path,
                headers = headers,
                params = params
            )

            offset += CHUNK_SIZE
            headers["x-appwrite-id"] = Json.decodeFromString<String>(result!!["\$id"].toString())
            onProgress?.invoke(
                UploadProgress(
                    id = result["\$id"].toString(),
                    progress = offset.coerceAtMost(size).toDouble() / size * 100,
                    sizeUploaded = offset.coerceAtMost(size),
                    chunksTotal = result["chunksTotal"].toString().toInt(),
                    chunksUploaded = result["chunksUploaded"].toString().toInt(),
                )
            )
        }

        return converter(result as Map<String, Any>)
    }
}