package com.home.amplifier;

import com.home.amplifier.configuration.AmplifierConfiguration;
import com.home.amplifier.configuration.MqttConfiguration;
import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.inject.Inject;

@Log4j2
public class MqttAmplifierService implements AmplifierService {

    private MqttClient mqttClient;

    private MqttConfiguration configuration;

    @Inject
    public MqttAmplifierService(MqttClient mqttClient, AmplifierConfiguration configuration) {
        this.mqttClient = mqttClient;
        this.configuration = configuration.getMqtt();
    }

    @Override
    public void volumeUp() {
        String volumeUpPayload = configuration.getPayload().getVolumeUpPayload();

        sendCommand(volumeUpPayload);
    }

    @Override
    public void volumeDown() {
        String volumeDownPayload = configuration.getPayload().getVolumeDownPayload();

        sendCommand(volumeDownPayload);
    }

    @Override
    public void mute() {
        String mutePayload = configuration.getPayload().getMutePayload();

        sendCommand(mutePayload);
    }

    @Override
    public void setSource(AmplifierSource source) {
        String sourcePayload = configuration.getPayload().getSourcePayload();

        sendCommand(sourcePayload + "_" + source);
    }

    private void sendCommand(String message) {
        String topic = configuration.getTopic();

        MqttMessage mqttMessage = new MqttMessage(message.getBytes());

        try {
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            log.error("Error while send message by mqtt", e);
        }
    }
}
