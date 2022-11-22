package com.example.spacenews.domain.di

import com.example.spacenews.domain.GetLatestPostsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {


    fun load() {
        loadKoinModules(useCaseModule())
    }


    private fun useCaseModule(): Module {
        return module {
            factory { GetLatestPostsUseCase(get()) }
        }
    }

}