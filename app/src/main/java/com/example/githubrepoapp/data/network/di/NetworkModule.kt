package com.example.githubrepoapp.data.network.di

import com.example.githubrepoapp.BuildConfig
import com.example.githubrepoapp.data.network.apis.DetailsAPI
import com.example.githubrepoapp.data.network.apis.HomeAPI
import com.example.githubrepoapp.data.network.apis.IssuesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            if(BuildConfig.DEBUG){
                this.level = HttpLoggingInterceptor.Level.BODY
            }else {
                this.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideHttpClientInterceptor( loggingInterceptor:HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer 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")
                .build()
            chain.proceed(modifiedRequest)
        }
            .build()
    }

    @Provides
    fun provideHomeApi(retrofit: Retrofit) : HomeAPI {
        return retrofit.create(HomeAPI::class.java)
    }

    @Provides
    fun provideDetailsApi(retrofit: Retrofit) : DetailsAPI {
        return retrofit.create(DetailsAPI::class.java)
    }

    @Provides
    fun provideIssuesApi(retrofit: Retrofit) : IssuesApi {
        return retrofit.create(IssuesApi::class.java)
    }
}