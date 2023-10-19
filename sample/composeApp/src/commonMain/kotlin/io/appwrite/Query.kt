package io.appwrite

class Query {
    companion object {
        fun equal(attribute: String, value: Any) = addQuery(attribute, "equal", value)

        fun notEqual(attribute: String, value: Any) = addQuery(attribute, "notEqual", value)

        fun lessThan(attribute: String, value: Any) = addQuery(attribute, "lessThan", value)

        fun lessThanEqual(attribute: String, value: Any) = addQuery(attribute, "lessThanEqual", value)

        fun greaterThan(attribute: String, value: Any) = addQuery(attribute, "greaterThan", value)

        fun greaterThanEqual(attribute: String, value: Any) = addQuery(attribute, "greaterThanEqual", value)

        fun search(attribute: String, value: String) = addQuery(attribute, "search", value)

        fun isNull(attribute: String) = "isNull(\"${attribute}\")"

        fun isNotNull(attribute: String) = "isNotNull(\"${attribute}\")"

        fun between(attribute: String, start: Int, end: Int) = addQuery(attribute, "between", listOf(start, end))

        fun between(attribute: String, start: Double, end: Double) =
            addQuery(attribute, "between", listOf(start, end))

        fun between(attribute: String, start: String, end: String) =
            addQuery(attribute, "between", listOf(start, end))

        fun startsWith(attribute: String, value: String) = addQuery(attribute, "startsWith", value)

        fun endsWith(attribute: String, value: String) = addQuery(attribute, "endsWith", value)

        fun select(attributes: List<String>) = "select([${attributes.joinToString(",") { "\"$it\"" }}])"

        fun orderAsc(attribute: String) = "orderAsc(\"${attribute}\")"

        fun orderDesc(attribute: String) = "orderDesc(\"${attribute}\")"

        fun cursorBefore(documentId: String) = "cursorBefore(\"${documentId}\")"

        fun cursorAfter(documentId: String) = "cursorAfter(\"${documentId}\")"

        fun limit(limit: Int) = "limit(${limit})"

        fun offset(offset: Int) = "offset(${offset})"

        private fun addQuery(attribute: String, method: String, value: Any): String {
            return when (value) {
                is List<*> -> "${method}(\"${attribute}\", [${value.joinToString(",") { parseValues(it!!) }}])"
                else -> "${method}(\"${attribute}\", [${parseValues(value)}])"
            }
        }

        private fun parseValues(value: Any): String {
            return when (value) {
                is String -> "\"${value}\""
                else -> "$value"
            }
        }
    }
}
