package com.home.amplifier.configuration;

import lombok.Data;

@Data
public class MqttConfiguration {
    private String topic;
    private PayloadConfiguration payload;
}
