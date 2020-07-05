package com.home.shared.mqtt;

import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Log4j2
public abstract class MqttBaseService {

    private final MqttClient mqttClient;

    public MqttBaseService(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    protected void sendCommand(String message, String topic) {

        MqttMessage mqttMessage = new MqttMessage(message.getBytes());

        try {
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            log.error("Error while send message by mqtt", e);
        }
    }

}
