package com.home.cover;

import com.home.configuration.ConfigurationService;
import com.home.cover.configuration.CoverConfiguration;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public abstract class CoverModule {
    @Binds
    @Singleton
    abstract CoverService climateService(MqttCoverService coverService);

    @Provides
    static CoverConfiguration configuration(ConfigurationService configurationService) {
        return configurationService.buildConfiguration("cover", CoverConfiguration.class);
    }
}
