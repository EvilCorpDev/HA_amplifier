package com.home.configuration;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;
import java.util.function.Supplier;

public class ConfigurationInputStreamSupplier implements Supplier<InputStream> {

    private String configFileName;

    @Inject
    public ConfigurationInputStreamSupplier(@Named("configuration_file_name") String configFileName) {
        this.configFileName = configFileName;
    }

    @Override
    public InputStream get() {
        return ConfigurationInputStreamSupplier.class.getClassLoader().getResourceAsStream(configFileName);
    }
}
