package com.home.configuration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class YamlConfigurationService implements ConfigurationService {
    private ObjectMapper objectMapper;
    private Supplier<InputStream> configSupplier;

    @Inject
    public YamlConfigurationService(Supplier<InputStream> configSupplier) {
        this.configSupplier = configSupplier;
        objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T> T buildConfiguration(String configurationName, Class<T> configurationClass) {
        JsonNode configNode;

        try {
            InputStream configFileInputStream = configSupplier.get();
            configNode = objectMapper.readTree(configFileInputStream);
            configNode = configNode.get(configurationName);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while parse config file", e);
        }

        try {
            return objectMapper.treeToValue(configNode, configurationClass);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Error while parse config for class:" + configurationClass, e);
        }
    }
}
