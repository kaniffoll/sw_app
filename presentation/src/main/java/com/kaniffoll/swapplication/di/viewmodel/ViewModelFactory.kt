package com.kaniffoll.swapplication.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jakarta.inject.Inject
import jakarta.inject.Provider


@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.entries.firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value
            ?: throw IllegalArgumentException("Unknown ViewModel $modelClass")

        return creator.get() as T
    }
}