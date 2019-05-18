/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.dare2

import com.google.firebase.auth.FirebaseAuth
import gr.brakaidevelopments.dare2.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { FirebaseAuth.getInstance() }
    viewModel { HomeViewModel(androidApplication()) }
}