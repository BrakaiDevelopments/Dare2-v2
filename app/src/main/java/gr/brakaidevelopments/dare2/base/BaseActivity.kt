/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.dare2.base

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {
    val mAuth by inject<FirebaseAuth>()
}