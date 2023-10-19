package io.appwrite.services

import io.appwrite.Client

/**
 * The GraphQL API allows you to query and mutate your Appwrite server using GraphQL.
**/
class Graphql(client: Client) : Service(client) {

    /**
     * GraphQL Endpoint
     *
     * Execute a GraphQL mutation.
     *
     * @param query The query or queries to execute.
     * @return [Any]
     */
    suspend fun query(
        query: Any,
    ): Any {
        val apiPath = "graphql"

        val apiParams = mutableMapOf<String, Any?>(
            "query" to query,
        )
        val apiHeaders = mutableMapOf(
            "x-sdk-graphql" to "true",
            "content-type" to "application/json",
        )
        val converter: (Any) -> Any = {
            it
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
     * GraphQL Endpoint
     *
     * Execute a GraphQL mutation.
     *
     * @param query The query or queries to execute.
     * @return [Any]
     */
    suspend fun mutation(
        query: Any,
    ): Any {
        val apiPath = "graphql/mutation"

        val apiParams = mutableMapOf<String, Any?>(
            "query" to query,
        )
        val apiHeaders = mutableMapOf(
            "x-sdk-graphql" to "true",
            "content-type" to "application/json",
        )
        val converter: (Any) -> Any = {
            it
        }
        return client.call(
            method = "POST",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter
        )
    }
}