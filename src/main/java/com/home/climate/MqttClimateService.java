package com.home.climate;

import com.home.climate.configuration.ClimateConfiguration;
import com.home.climate.configuration.MqttClimateConfiguration;
import com.home.shared.mqtt.MqttBaseService;
import org.eclipse.paho.client.mqttv3.MqttClient;

import javax.inject.Inject;

public class MqttClimateService extends MqttBaseService implements ClimateService {

    private final MqttClimateConfiguration configuration;

    @Inject
    public MqttClimateService(MqttClient mqttClient, ClimateConfiguration configuration) {
        super(mqttClient);
        this.configuration = configuration.getMqtt();
    }

    @Override
    public void tempUp() {
        String tempUpPayload = configuration.getPayload().getTempUpPayload();

        sendCommand(tempUpPayload, configuration.getTopic());
    }

    @Override
    public void tempDown() {
        String tempDownPayload = configuration.getPayload().getTempDownPayload();

        sendCommand(tempDownPayload, configuration.getTopic());
    }
}
