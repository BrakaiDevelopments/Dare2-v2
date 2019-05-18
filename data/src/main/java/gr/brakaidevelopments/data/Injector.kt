/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data

import org.koin.dsl.module

val retrofitModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideDaretoApi(retrofit = get()) }
    single { get<RetrofitClient>().getGson() }
    single { get<RetrofitClient>().getHttpLoggingInterceptor() }
    single { get<RetrofitClient>().getHttpClient(httpLoggingInterceptor = get()) }
    single { get<RetrofitClient>().provideRetrofitInterface(gson = get(), httpClient = get()) }
}

val databaseModule = module {

}

val dataRepositoryModule = module {

}