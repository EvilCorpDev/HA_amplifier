package com.home.cover;

import com.home.cover.configuration.CoverConfiguration;
import com.home.cover.configuration.MqttCoverConfiguration;
import com.home.shared.mqtt.MqttBaseService;
import org.eclipse.paho.client.mqttv3.MqttClient;

import javax.inject.Inject;

public class MqttCoverService extends MqttBaseService implements CoverService {

    private MqttCoverConfiguration config;

    @Inject
    public MqttCoverService(CoverConfiguration configuration, MqttClient mqttClient) {
        super(mqttClient);
        config = configuration.getMqtt();
    }

    @Override
    public void openCover() {
        String openCoverPayload = config.getPayload().getOpenCoverPayload();

        sendCommand(openCoverPayload, config.getTopic());
    }

    @Override
    public void closeCover() {
        String closeCoverPayload = config.getPayload().getCloseCoverPayload();

        sendCommand(closeCoverPayload, config.getTopic());
    }
}
