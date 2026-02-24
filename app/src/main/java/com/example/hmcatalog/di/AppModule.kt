package com.example.hmcatalog.di

import com.example.hmcatalog.data.api.HmApi
import com.example.hmcatalog.data.repository.ProductRepositoryImpl
import com.example.hmcatalog.domain.repository.ProductRepository
import com.example.hmcatalog.domain.usecase.GetProductsUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): HmApi =
        Retrofit.Builder()
            .baseUrl("https://api.hm.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(HmApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(hmApi: HmApi): ProductRepository =
        ProductRepositoryImpl(hmApi)

    @Provides
    @Singleton
    fun provideUseCase(productRepository: ProductRepository) =
        GetProductsUseCase(productRepository)
}