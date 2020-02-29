package com.example.advancedagger2.di.auth

import com.bins.data.api.SquireRepoApi
import com.bins.data.repository.SquireRepositoryImpl
import com.bins.domain.repository.SquireRepository
import com.bins.domain.usecases.SquireRepoUseCase
import com.bins.domain.usecases.SquireRepoUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    internal fun provideSquireRepoApi(retrofit: Retrofit): SquireRepoApi {
        return retrofit.create(SquireRepoApi::class.java)
    }

    @Provides
    internal fun provideSquireRepository( api: SquireRepoApi): SquireRepository {
        return SquireRepositoryImpl(api)
    }

    @Provides
    internal fun provideSquireRepoUseCase( squireRepository: SquireRepository): SquireRepoUseCase {
        return SquireRepoUseCaseImpl(squireRepository)
    }

}