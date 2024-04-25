package com.example.parmegianocounter.di

import androidx.room.Room
import com.example.parmegianocounter.data.database.AppDatabase
import com.example.parmegianocounter.data.network.DishDataSource
import com.example.parmegianocounter.data.repository.DishRepository
import com.example.parmegianocounter.data.repository.DishRepositoryImpl
import com.example.parmegianocounter.ui.vm.DishViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single(named("BASE_URL")) {
        "http://192.168.0.59:3000"
    }
    single {
        Dispatchers.IO
    }
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }
    single {
        OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>()).build()
    }
    single {
        Retrofit.Builder().baseUrl(get<String>(named("BASE_URL")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).client(get()).build().create(DishDataSource::class.java)
    }
    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java, "database-name"
        ).build()
    }
    single {
        get<AppDatabase>().dishDao()
    }
    single<DishRepository> {
        DishRepositoryImpl(get(), get(), get())
    }
    viewModel {
        DishViewModel(get())
    }
}