package com.home.mqtt;

import com.home.configuration.ConfigurationService;
import dagger.Module;
import dagger.Provides;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

@Module
public class MqttModule {

    @Provides
    public MqttClient mqttClient(ConfigurationService configurationService) {
        MqttConfiguration configuration =
                configurationService.buildConfiguration("mqtt", MqttConfiguration.class);

        try {
            MqttClient mqttClient = new MqttClient(configuration.getServerUrl(), configuration.getClientId());
            mqttClient.connect(setUpConnectionOptions(configuration));

            return mqttClient;
        } catch (MqttException e) {
            throw new IllegalStateException("Error while create mqtt client", e);
        }
    }

    private static MqttConnectOptions setUpConnectionOptions(MqttConfiguration configuration) {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(configuration.getUsername());
        connOpts.setPassword(configuration.getPassword().toCharArray());
        connOpts.setAutomaticReconnect(configuration.getAutoReconnect());
        return connOpts;
    }
}
