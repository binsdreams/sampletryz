package com.bins.tryz.di

import com.bins.tryz.TestTryzApplication
import com.bins.tryz.di.scopes.AppScoped
import com.bins.tryz.ui.di.MainFragmentBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Component is a graph. We build a component. Component will provide injected instances by using modules.
 * Extends appcomponent with [AndroidInjector] to avoid old way of injection application
 *
 * <code>
 *     fun inject(application: BaseApplication)
 * </code>
 *
 * AppComponent is act as a server whereas, [Application] act as a client.
 * Dagger interaction is like client-server interaction
 *
 * Anotated with [Singleton] Scope to tell dagger to keep it in the memory while application exists
 * and destroy it when application destroy
 */
@AppScoped
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MainFragmentBuilderModule::class,
    TestNetworkModule::class,
    ViewModelFactoryModule::class
    ]
)
interface TestAppComponent : AndroidInjector<TestTryzApplication> {

    @Component.Builder
    interface Builder {

        /**
         * [BindsInstance] annotation is used for, if you want to bind particular object or instance
         * of an object through the time of component construction
         */
        @BindsInstance
        fun application(application: TestTryzApplication) : Builder

        fun build() : TestAppComponent
    }

}