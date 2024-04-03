package com.github.gunin_igor75.githubapp.data.network.api


import com.github.gunin_igor75.githubapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://api.github.com/"
    private const val HEADER = "Authorization"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiTokenInterceptor())
        .addInterceptor(loggingInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)?: throw IllegalStateException(
        "ApiService is null"
    )

    private fun loggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun apiTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
                val newBuilder = chain.request()
                    .newBuilder()
                    .addHeader(HEADER, "Bearer ${BuildConfig.API_TOKEN}")
                    .build()
            return@Interceptor chain.proceed(newBuilder)
        }
    }
}