package com.assignment.telstra.core.dagger.modules

import com.assignment.telstra.BuildConfig
import com.assignment.telstra.core.store.AppStore
import com.assignment.telstra.core.store.online.services.ApiService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NetworkModule {
    private val REQUEST_TIMEPUT = 10L
    private lateinit var okHttpClinet: OkHttpClient

    @Inject
    lateinit var appStore: AppStore

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        // httpClient.addInterceptor(getHeaderInterceptor())
        okHttpClinet = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEPUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEPUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(getHeaderInterceptor())
            .build()
        return okHttpClinet
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun getHeaderInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): Response {
                var request: okhttp3.Request = chain?.request()!!
                val headers = request.headers().newBuilder()
                    .add("Authorization", "Bearer " + "Token")//Token need to be replaced here
                    .add("Accept", "application/json")
                    .add("description", "")
                    .add("userId", "")
                    .add("token", "")
                    .add("deviceType", "ANDROID")
                    .add("deviceId", "")
                    .build()
                request = request.newBuilder().headers(headers).build()
                return chain.proceed(request)
            }
        }

    }

}