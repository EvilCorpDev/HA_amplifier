package com.home.cover.configuration;

import lombok.Data;

@Data
public class MqttCoverConfiguration {
    private String topic;
    private PayloadCoverConfiguration payload;
}
