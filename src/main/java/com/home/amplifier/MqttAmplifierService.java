package com.home.amplifier;

import com.home.amplifier.configuration.AmplifierConfiguration;
import com.home.amplifier.configuration.MqttConfiguration;
import com.home.shared.mqtt.MqttBaseService;
import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.MqttClient;

import javax.inject.Inject;

@Log4j2
public class MqttAmplifierService extends MqttBaseService implements AmplifierService {

    private final MqttConfiguration configuration;

    @Inject
    public MqttAmplifierService(MqttClient mqttClient, AmplifierConfiguration configuration) {
        super(mqttClient);
        this.configuration = configuration.getMqtt();
    }

    @Override
    public void volumeUp() {
        String volumeUpPayload = configuration.getPayload().getVolumeUpPayload();

        sendCommandWithTopic(volumeUpPayload);
    }

    @Override
    public void volumeDown() {
        String volumeDownPayload = configuration.getPayload().getVolumeDownPayload();

        sendCommandWithTopic(volumeDownPayload);
    }

    @Override
    public void mute() {
        String mutePayload = configuration.getPayload().getMutePayload();

        sendCommandWithTopic(mutePayload);
    }

    @Override
    public void setSource(AmplifierSource source) {
        String sourcePayload = configuration.getPayload().getSourcePayload();

        sendCommandWithTopic(sourcePayload + "_" + source);
    }

    private void sendCommandWithTopic(String message) {
        sendCommand(message, configuration.getTopic());
    }
}
