package org.gspi.protrack.common.utils

import androidx.core.bundle.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun <T> NavController.setBackStackEntryData(key: String, value: T) {
    this.currentBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <T> NavController.setBackStackEntryData(key: String, value: T, usePrevious: Boolean = false) {
    val targetEntry = if (usePrevious) this.previousBackStackEntry else this.currentBackStackEntry
    targetEntry?.savedStateHandle?.set(key, value)
}

inline fun <reified T> NavController.getBackStackEntryData(key: String, usePrevious: Boolean = false): T? {
    val targetEntry = if (usePrevious) this.previousBackStackEntry else this.currentBackStackEntry
    return targetEntry?.savedStateHandle?.get<T>(key)
}