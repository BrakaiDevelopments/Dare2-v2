/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import gr.brakaidevelopments.data.api.DaretoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        private const val BASE_URL = "http://192.168.1.3:8080/"
        private const val TIMEOUT_SECONDS = 120
    }

    fun provideDaretoApi(retrofit: Retrofit): DaretoApi {
        return retrofit.create(DaretoApi::class.java)
    }

    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptorLevel =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().setLevel(interceptorLevel)
    }

    fun getGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()
    }

    fun getHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .build()
    }

    fun provideRetrofitInterface(gson: Gson, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}