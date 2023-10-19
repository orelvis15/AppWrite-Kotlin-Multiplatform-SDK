package io.appwrite.services

import io.appwrite.Client
import io.appwrite.models.*
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.database.Document
import io.appwrite.models.database.DocumentList

/**
 * The Databases service allows you to create structured collections of documents, query and filter lists of documents
**/
class Databases(client: Client) : Service(client) {

    /**
     * List Documents
     *
     * Get a list of all the user&#039;s documents in a given collection. You can use the query params to filter your results.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID. You can create a new collection using the Database service [server integration](https://appwrite.io/docs/server/databases#databasesCreateCollection).
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long.
     * @return [io.appwrite.models.database.DocumentList<T>]
     */
    @JvmOverloads
    suspend fun <T> listDocuments(
        databaseId: String,
        collectionId: String,
        queries: List<String>? = null
    ): DocumentList<T> {
        val apiPath = "databases/$databaseId/collections/$collectionId/documents"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> DocumentList<T> = {
            DocumentList.from(map = it as Map<String, Any>)
        }
        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * List Documents
     *
     * Get a list of all the user&#039;s documents in a given collection. You can use the query params to filter your results.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID. You can create a new collection using the Database service [server integration](https://appwrite.io/docs/server/databases#databasesCreateCollection).
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long.
     * @return [io.appwrite.models.database.DocumentList<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun listDocumentsDatabase(
        databaseId: String,
        collectionId: String,
        queries: List<String>? = null,
    ): DocumentList<Map<String, Any>> = listDocuments(
        databaseId,
        collectionId,
        queries
    )

    /**
     * Create Document
     *
     * Create a new Document. Before using this route, you should create a new collection resource using either a [server integration](/docs/server/databases#databasesCreateCollection) API or directly from your database console.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID. You can create a new collection using the Database service [server integration](https://appwrite.io/docs/server/databases#databasesCreateCollection). Make sure to define attributes before creating documents.
     * @param documentId Document ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param data Document data as JSON object.
     * @param permissions An array of permissions strings. By default, only the current user is granted all permissions. [Learn more about permissions](/docs/permissions).
     * @return [io.appwrite.models.database.Document<T>]
     */
    @JvmOverloads
    suspend fun <T> createDocument(
        databaseId: String,
        collectionId: String,
        documentId: String,
        data: Any,
        permissions: List<String>? = null
    ): Document<T> {
        val apiPath = "databases/$databaseId/collections/$collectionId/documents"

        val apiParams = mutableMapOf(
            "documentId" to documentId,
            "data" to data,
            "permissions" to permissions,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Document<T> = {
            Document.from(map = it as Map<String, Any>)
        }
        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter,
        )
    }

    /**
     * Create Document
     *
     * Create a new Document. Before using this route, you should create a new collection resource using either a [server integration](/docs/server/databases#databasesCreateCollection) API or directly from your database console.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID. You can create a new collection using the Database service [server integration](https://appwrite.io/docs/server/databases#databasesCreateCollection). Make sure to define attributes before creating documents.
     * @param documentId Document ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param data Document data as JSON object.
     * @param permissions An array of permissions strings. By default, only the current user is granted all permissions. [Learn more about permissions](/docs/permissions).
     * @return [io.appwrite.models.database.Document<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun createDocumentDatabase(
        databaseId: String,
        collectionId: String,
        documentId: String,
        data: Any,
        permissions: List<String>? = null,
    ): Document<Map<String, Any>> = createDocument(
        databaseId,
        collectionId,
        documentId,
        data,
        permissions
    )

    /**
     * Get Document
     *
     * Get a document by its unique ID. This endpoint response returns a JSON object with the document data.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID. You can create a new collection using the Database service [server integration](https://appwrite.io/docs/server/databases#databasesCreateCollection).
     * @param documentId Document ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/databases#querying-documents). Only method allowed is select.
     * @return [io.appwrite.models.database.Document<T>]
     */
    @JvmOverloads
    suspend fun <T> getDocument(
        databaseId: String,
        collectionId: String,
        documentId: String,
        queries: List<String>? = null
    ): Document<T> {
        val apiPath = "databases/$databaseId/collections/$collectionId/documents/$documentId"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Document<T> = {
            Document.from(map = it as Map<String, Any>)
        }
        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }

    /**
     * Get Document
     *
     * Get a document by its unique ID. This endpoint response returns a JSON object with the document data.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID. You can create a new collection using the Database service [server integration](https://appwrite.io/docs/server/databases#databasesCreateCollection).
     * @param documentId Document ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/databases#querying-documents). Only method allowed is select.
     * @return [io.appwrite.models.database.Document<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun getDocumentDatabase(
        databaseId: String,
        collectionId: String,
        documentId: String,
        queries: List<String>? = null,
    ): Document<Map<String, Any>> = getDocument(
        databaseId,
        collectionId,
        documentId,
        queries
    )

    /**
     * Update Document
     *
     * Update a document by its unique ID. Using the patch method you can pass only specific fields that will get updated.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID.
     * @param documentId Document ID.
     * @param data Document data as JSON object. Include only attribute and value pairs to be updated.
     * @param permissions An array of permissions strings. By default, the current permissions are inherited. [Learn more about permissions](/docs/permissions).
     * @return [io.appwrite.models.database.Document<T>]
     */
    @JvmOverloads
    suspend fun <T> updateDocument(
        databaseId: String,
        collectionId: String,
        documentId: String,
        data: Any? = null,
        permissions: List<String>? = null
    ): Document<T> {
        val apiPath = "databases/$databaseId/collections/$collectionId/documents/$documentId"

        val apiParams = mutableMapOf(
            "data" to data,
            "permissions" to permissions,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Document<T> = {
            Document.from(map = it as Map<String, Any>)
        }
        return client.call(
            method = "PATCH",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter,
        )
    }

    /**
     * Update Document
     *
     * Update a document by its unique ID. Using the patch method you can pass only specific fields that will get updated.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID.
     * @param documentId Document ID.
     * @param data Document data as JSON object. Include only attribute and value pairs to be updated.
     * @param permissions An array of permissions strings. By default, the current permissions are inherited. [Learn more about permissions](/docs/permissions).
     * @return [io.appwrite.models.database.Document<T>]
     */
    @JvmOverloads
    @Throws(AppwriteException::class)
    suspend fun updateDocumentDatabase(
        databaseId: String,
        collectionId: String,
        documentId: String,
        data: Any? = null,
        permissions: List<String>? = null,
    ): Document<Map<String, Any>> = updateDocument(
        databaseId,
        collectionId,
        documentId,
        data,
        permissions
    )

    /**
     * Delete Document
     *
     * Delete a document by its unique ID.
     *
     * @param databaseId Database ID.
     * @param collectionId Collection ID. You can create a new collection using the Database service [server integration](https://appwrite.io/docs/server/databases#databasesCreateCollection).
     * @param documentId Document ID.
     * @return [Any]
     */
    suspend fun deleteDocument(
        databaseId: String,
        collectionId: String,
        documentId: String,
    ): Any {
        val apiPath = "databases/$databaseId/collections/$collectionId/documents/$documentId"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams
        )
    }
}