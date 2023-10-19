package io.appwrite.services

import io.appwrite.Client
import io.appwrite.models.*
import io.appwrite.models.function.Execution
import io.appwrite.models.function.ExecutionList

/**
 * The Functions Service allows you view, create and manage your Cloud Functions.
**/
class Functions(client: Client) : Service(client) {

    /**
     * List Executions
     *
     * Get a list of all the current user function execution logs. You can use the query params to filter your results.
     *
     * @param functionId Function ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: trigger, status, responseStatusCode, duration
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @return [io.appwrite.models.ExecutionList]
     */
    @JvmOverloads
    suspend fun listExecutions(
        functionId: String,
        queries: List<String>? = null,
        search: String? = null,
    ): ExecutionList {
        val apiPath = "functions/$functionId/executions"

        val apiParams = mutableMapOf(
            "queries" to queries,
            "search" to search,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> ExecutionList = {
            ExecutionList.from(map = it as Map<String, Any>)
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
     * Create Execution
     *
     * Trigger a function execution. The returned object will return you the current execution status. You can ping the `Get Execution` endpoint to get updates on the current execution status. Once this endpoint is called, your function execution process will start asynchronously.
     *
     * @param functionId Function ID.
     * @param body HTTP body of execution. Default value is empty string.
     * @param async Execute code in the background. Default value is false.
     * @param path HTTP path of execution. Path can include query params. Default value is /
     * @param method HTTP method of execution. Default value is GET.
     * @param headers HTTP headers of execution. Defaults to empty.
     * @return [io.appwrite.models.Execution]
     */
    @JvmOverloads
    suspend fun createExecution(
        functionId: String,
        body: String? = null,
        async: Boolean? = null,
        path: String? = null,
        method: String? = null,
        headers: Any? = null,
    ): Execution {
        val apiPath = "functions/$functionId/executions"

        val apiParams = mutableMapOf(
            "body" to body,
            "async" to async,
            "path" to path,
            "method" to method,
            "headers" to headers,
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Execution = {
            Execution.from(map = it as Map<String, Any>)
        }
        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }


    /**
     * Get Execution
     *
     * Get a function execution log by its unique ID.
     *
     * @param functionId Function ID.
     * @param executionId Execution ID.
     * @return [io.appwrite.models.Execution]
     */
    suspend fun getExecution(
        functionId: String,
        executionId: String,
    ): Execution {
        val apiPath = "functions/$functionId/executions/$executionId"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Execution = {
            Execution.from(map = it as Map<String, Any>)
        }
        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }
}