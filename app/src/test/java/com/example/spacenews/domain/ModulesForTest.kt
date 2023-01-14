package com.example.spacenews.domain

import com.example.spacenews.data.database.PostDatabase
import com.example.spacenews.data.repository.PostRepository
import com.example.spacenews.data.repository.PostRepositoryImpl
import com.example.spacenews.data.services.SpaceFlightNewsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun configureDataModuleForTest(baseUrl: String) = module {
    single<SpaceFlightNewsService> {

        val factory = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        createServiceForTest(factory = factory, baseUrl = baseUrl)
    }

    single<PostRepository> { PostRepositoryImpl(get(), get()) }
}

fun configureDAOModuleForTest() = module {
    single { PostDatabase.getInstance(androidContext()).dao }
}

fun configureDomainModuleForTest() = module {
    factory<GetLatestPostsUseCase> { GetLatestPostsUseCase(get()) }
    factory<GetLatestPostsTitleContainsUseCase> { GetLatestPostsTitleContainsUseCase(get()) }
}

private inline fun <reified T> createServiceForTest(
    factory: Moshi,
    baseUrl: String
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .build()
        .create(T::class.java)
}
