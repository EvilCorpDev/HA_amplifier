package com.home.climate;

import com.home.climate.configuration.ClimateConfiguration;
import com.home.configuration.ConfigurationService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public abstract class ClimateModule {
    @Binds
    @Singleton
    abstract ClimateService climateService(MqttClimateService climateService);

    @Provides
    static ClimateConfiguration configuration(ConfigurationService configurationService) {
        return configurationService.buildConfiguration("climate", ClimateConfiguration.class);
    }
}

