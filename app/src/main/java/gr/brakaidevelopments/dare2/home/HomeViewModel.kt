/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.dare2.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import gr.brakaidevelopments.dare2.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : BaseViewModel(application) {

    val onRefresh: () -> Unit = {
        loadChallenges()
    }


    private fun loadChallenges() = viewModelScope.launch {

    }

}