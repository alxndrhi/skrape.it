package it.skrape.core.fetcher

import it.skrape.SkrapeItDsl
import it.skrape.core.fetcher.Authentication.Type.NONE
import it.skrape.core.fetcher.Method.GET
import it.skrape.core.fetcher.Mode.SOURCE

@SkrapeItDsl
data class Request(

        /**
         * Defines the request-mode.
         * Defaults to SOURCE.
         * @see Mode for all possible values.
         */
        var mode: Mode = SOURCE,

        /**
         * Defines the http verb of the request.
         * Defaults to GET.
         * @see Method for all possible values.
         */
        var method: Method = GET,

        /**
         * Defines the URL the request is made against.
         * Defaults to "http://localhost:8080/"
         * If you set this parameter other url-specific parameters (protocol, host, port, path, queryParam)
         * will have no effect.
         */
        var url: Url = UrlBuilder().toString(),

        var userAgent: String = "Mozilla/5.0 skrape.it",
        var headers: Map<String, String> = emptyMap(),
        var cookies: Map<String, String> = emptyMap(),
        var timeout: Int = 5000,
        var followRedirects: Boolean = true,
        var authentication: Authentication = Authentication(NONE)
) {
    val asConfig
        get() = this

    fun urlBuilder(init: UrlBuilder.() -> Unit): String {
        return UrlBuilder().also(init).toString()
    }
}

enum class Mode {
    /**
     * Use SOURCE to get server-side rendered responses.
     * If SOURCE-mode is active skrape{it} will behave like normal http-client.
     */
    SOURCE,
    /**
     * Use DOM to get client-side rendered responses.
     * If DOM-mode is active skrape{it} will use a browser engine to transform the response body to a rendered document.
     */
    DOM
}

enum class Method {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH,
    HEAD
}
