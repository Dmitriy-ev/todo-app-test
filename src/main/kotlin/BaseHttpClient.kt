import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

abstract class BaseHttpClient {

    val gson = Gson()

    private fun okHttpInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(okHttpInterceptor())
        .build()
    private val JSON = "application/json; charset=utf-8".toMediaType()

    inline fun<reified T> Gson.fromJson(json: String?) = fromJson(json, T::class.java)

    fun <T> doPostRequest(url: String, body: T?): Response {
        val request = Request.Builder()
            .post(gson.toJson(body).toRequestBody(JSON))
            .url(url)
            .build()

        return okHttpClient.newCall(request).execute()
    }
}