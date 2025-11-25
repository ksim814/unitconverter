package com.example.unitconverter.di

import com.example.unitconverter.domain.UnitConverter
import com.example.unitconverter.domain.UnitConverterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConverterModule {

    @Binds
    @Singleton
    abstract fun bindUnitConverter(
        impl: UnitConverterImpl
    ): UnitConverter
}