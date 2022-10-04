package com.example.mconomy.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

fun Fragment.launchCoroutine(): CoroutineScope {
    val job = Job()
    return CoroutineScope(Dispatchers.Default + job)
}

fun Activity.launchCoroutine(): CoroutineScope {
    val job = Job()
    return CoroutineScope(Dispatchers.Default + job)
}