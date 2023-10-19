package io.appwrite.services

import io.appwrite.Client
import io.appwrite.models.*
import io.appwrite.models.locale.*
import io.appwrite.models.locale.Locale

/**
 * The Locale service allows you to customize your app based on your users&#039; location.
**/
class Locale(client: Client) : Service(client) {

    /**
     * Get User Locale
     *
     * Get the current user location based on IP. Returns an object with user country code, country name, continent name, continent code, ip address and suggested currency. You can use the locale header to get the data in a supported language.([IP Geolocation by DB-IP](https://db-ip.com))
     *
     * @return [io.appwrite.models.Locale]
     */
    suspend fun get(
    ): Locale {
        val apiPath = "locale"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> Locale = {
            Locale.from(map = it as Map<String, Any>)
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
     * List Locale Codes
     *
     * List of all locale codes in [ISO 639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes).
     *
     * @return [io.appwrite.models.LocaleCodeList]
     */
    suspend fun listCodes(
    ): LocaleCodeList {
        val apiPath = "locale/codes"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> LocaleCodeList = {
            LocaleCodeList.from(map = it as Map<String, Any>)
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
     * List Continents
     *
     * List of all continents. You can use the locale header to get the data in a supported language.
     *
     * @return [io.appwrite.models.ContinentList]
     */
    suspend fun listContinents(
    ): ContinentList {
        val apiPath = "locale/continents"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> ContinentList = {
            ContinentList.from(map = it as Map<String, Any>)
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
     * List Countries
     *
     * List of all countries. You can use the locale header to get the data in a supported language.
     *
     * @return [io.appwrite.models.CountryList]
     */
    suspend fun listCountries(
    ): CountryList {
        val apiPath = "locale/countries"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> CountryList = {
            CountryList.from(map = it as Map<String, Any>)
        }
        return client.call(
            method = "GET",
            path = apiPath,
            headers = apiHeaders,
            params = apiParams,
            converter = converter,
        )
    }


    /**
     * List EU Countries
     *
     * List of all countries that are currently members of the EU. You can use the locale header to get the data in a supported language.
     *
     * @return [io.appwrite.models.CountryList]
     */
    suspend fun listCountriesEU(
    ): CountryList {
        val apiPath = "locale/countries/eu"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> CountryList = {
            CountryList.from(map = it as Map<String, Any>)
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
     * List Countries Phone Codes
     *
     * List of all countries phone codes. You can use the locale header to get the data in a supported language.
     *
     * @return [io.appwrite.models.PhoneList]
     */
    suspend fun listCountriesPhones(
    ): PhoneList {
        val apiPath = "locale/countries/phones"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> PhoneList = {
            PhoneList.from(map = it as Map<String, Any>)
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
     * List Currencies
     *
     * List of all currencies, including currency symbol, name, plural, and decimal digits for all major and minor currencies. You can use the locale header to get the data in a supported language.
     *
     * @return [io.appwrite.models.CurrencyList]
     */
    suspend fun listCurrencies(
    ): CurrencyList {
        val apiPath = "locale/currencies"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> CurrencyList = {
            CurrencyList.from(map = it as Map<String, Any>)
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
     * List Languages
     *
     * List of all languages classified by ISO 639-1 including 2-letter code, name in English, and name in the respective language.
     *
     * @return [io.appwrite.models.LanguageList]
     */
    suspend fun listLanguages(
    ): LanguageList {
        val apiPath = "locale/languages"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf(
            "content-type" to "application/json",
        )
        val converter: (Any) -> LanguageList = {
            LanguageList.from(map = it as Map<String, Any>)
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