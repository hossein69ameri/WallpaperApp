package ameri.hossein.wallpaperapp.utils.di

import ameri.hossein.wallpaperapp.BuildConfig
import ameri.hossein.wallpaperapp.data.network.ApiServices
import ameri.hossein.wallpaperapp.utils.ACCEPT_VERSION
import ameri.hossein.wallpaperapp.utils.API_KEY
import ameri.hossein.wallpaperapp.utils.AUTHORIZATION
import ameri.hossein.wallpaperapp.utils.BASE_URL
import ameri.hossein.wallpaperapp.utils.CLIENT_ID
import ameri.hossein.wallpaperapp.utils.CONNECTION_TIME
import ameri.hossein.wallpaperapp.utils.NAMED_PING
import ameri.hossein.wallpaperapp.utils.PING_INTERVAL
import ameri.hossein.wallpaperapp.utils.V1
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiServices =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideClient(
        timeout: Long, @Named(NAMED_PING) ping: Long, interceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()

        .addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader(AUTHORIZATION, "$CLIENT_ID $API_KEY")
                it.addHeader(ACCEPT_VERSION, V1)
            }.build())
        }.also { client ->
            client.addInterceptor(interceptor)
        }
        .writeTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .pingInterval(ping, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Singleton
    fun provideTimeout() = CONNECTION_TIME

    @Provides
    @Singleton
    @Named(NAMED_PING)
    fun providePingInterval() = PING_INTERVAL
}