package com.home.climate.configuration;

import lombok.Data;

@Data
public class MqttClimateConfiguration {
    private String topic;
    private PayloadClimateConfiguration payload;
}
