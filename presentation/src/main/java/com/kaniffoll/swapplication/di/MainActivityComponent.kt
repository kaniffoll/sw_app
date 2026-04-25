package com.kaniffoll.swapplication.di

import com.kaniffoll.di.ActivityScope
import com.kaniffoll.di.AppComponent
import com.kaniffoll.swapplication.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface MainActivityComponent {

    fun inject(activity: MainActivity)
    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): MainActivityComponent
    }
}