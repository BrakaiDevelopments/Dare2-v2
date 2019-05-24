/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data

import gr.brakaidevelopments.data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
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
    single { AppDatabase(androidContext()) }
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().challengeDao() }
    single { get<AppDatabase>().leaderBoardDao() }
    single { get<AppDatabase>().commentViewDao() }
    single { get<AppDatabase>().commentDao() }
    single(named("Test")) { AppDatabase.getInMemoryDatabase(androidContext()) }
}

val dataRepositoryModule = module {

}