package com.home.amplifier;

import com.home.amplifier.configuration.AmplifierConfiguration;
import com.home.configuration.ConfigurationService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public abstract class AmplifierModule {
    @Binds
    @Singleton
    abstract AmplifierService amplifierService(MqttAmplifierService amplifierService);

    @Provides
    static AmplifierConfiguration configuration(ConfigurationService configurationService) {
        return configurationService.buildConfiguration("amplifier", AmplifierConfiguration.class);
    }
}
