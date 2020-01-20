package com.home.amplifier;

import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface AmplifierModule {
    @Binds
    @Singleton
    AmplifierService amplifierService(RestAmplifierService restAmplifierService);
}
