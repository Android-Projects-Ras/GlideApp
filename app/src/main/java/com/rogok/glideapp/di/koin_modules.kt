package com.rogok.glideapp.di

import com.rogok.glideapp.api.BASE_URL
import com.rogok.glideapp.api.JsonPlaceholderApi
import com.rogok.glideapp.data.UserRepository
import com.rogok.glideapp.data.UserRepositoryImpl
import com.rogok.glideapp.ui.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        //provide GsonConverterFactory()
        GsonConverterFactory.create()
    }

    //creating Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                    .build()
            )
            .build()
    }

    //creating API
    single { get<Retrofit>().create(JsonPlaceholderApi::class.java) }
}

val repositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}

val viewModelModule = module {
    viewModel {
        UserViewModel(get())
    }
}