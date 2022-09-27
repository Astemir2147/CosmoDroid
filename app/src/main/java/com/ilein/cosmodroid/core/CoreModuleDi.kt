package com.ilein.cosmodroid.core
import com.ilein.cosmodroid.feature_news_list.data.api.ApiRequest
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module{
    single<ApiRequest> {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://ll.thespacedevs.com/2.2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiRequest::class.java)
    }
}