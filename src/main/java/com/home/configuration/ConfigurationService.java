package com.home.configuration;

public interface ConfigurationService {
    <T> T buildConfiguration(String configurationName, Class<T> configurationClass);
}
