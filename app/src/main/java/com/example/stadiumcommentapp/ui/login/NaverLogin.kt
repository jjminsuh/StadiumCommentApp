package com.example.stadiumcommentapp.ui.login

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class NaverLogin {
    fun connect(apiUrl: String): HttpURLConnection {
        return try {
            val url: URL = URL(apiUrl)
            url.openConnection() as HttpURLConnection
        } catch (e: MalformedURLException) {
            throw RuntimeException("API URL wrong! : $apiUrl", e)
        } catch (e: IOException) {
            throw RuntimeException("connection failed! : $apiUrl", e)
        }
    }

    fun readyBody(body: InputStream): String? {
        val streamReader = InputStreamReader(body)

        try {
            BufferedReader(streamReader).use { reader ->
                val responseBody = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    responseBody.append(line)
                }

                return responseBody.toString()
            }
        } catch (e: IOException) {
            throw RuntimeException("reading API response failed!", e)
        }
    }

    fun get(apiUrl: String, requestHeaders: Map<String, String>): String? {
        val conn: HttpURLConnection = connect(apiUrl)

        return try {
            conn.requestMethod = "GET"

            for ((key, value ) in requestHeaders) {
                conn.setRequestProperty(key, value)
            }

            val responseCode: Int = conn.responseCode

            if(responseCode == HttpURLConnection.HTTP_OK) {
                readyBody(conn.inputStream)
            } else {
                readyBody(conn.errorStream)
            }
        } catch (e: IOException) {
            throw RuntimeException("API request and response failed!", e)
        } finally {
            conn.disconnect()
        }
    }
}