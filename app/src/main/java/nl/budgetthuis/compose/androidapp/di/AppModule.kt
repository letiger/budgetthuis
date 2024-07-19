package nl.budgetthuis.compose.androidapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.budgetthuis.compose.androidapp.common.Constants.BASE_URL
import nl.budgetthuis.compose.androidapp.common.DefaultDispatchers
import nl.budgetthuis.compose.androidapp.common.DispatchersProvider
import nl.budgetthuis.compose.androidapp.data.remote.CoinPaprikaApi
import nl.budgetthuis.compose.androidapp.data.repository.CoinRepositoryImpl
import nl.budgetthuis.compose.androidapp.domain.repository.CoinRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()
                val request = requestBuilder
                    .method(originalRequest.method, originalRequest.body)
                    .build()
                chain.proceed(request)
            }
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCoinRepository(coinPaprikaApi: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(coinPaprikaApi)
    }

    @Provides
    @Singleton
    fun provideDispatchersProvider(): DispatchersProvider = DefaultDispatchers()

    @Singleton
    @Provides
    fun provideRadiusApi(retrofit: Retrofit): CoinPaprikaApi =
        retrofit.create(CoinPaprikaApi::class.java)
}