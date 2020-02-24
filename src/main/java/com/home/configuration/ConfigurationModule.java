package com.home.configuration;

import dagger.Binds;
import dagger.Module;

import java.io.InputStream;
import java.util.function.Supplier;

@Module
public interface ConfigurationModule {
    @Binds
    Supplier<InputStream> configInputStreamSupplier(ConfigurationInputStreamSupplier supplier);

    @Binds
    ConfigurationService configurationService(YamlConfigurationService configurationService);
}
