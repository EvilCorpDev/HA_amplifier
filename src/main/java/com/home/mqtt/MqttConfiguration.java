package com.home.mqtt;

import lombok.Data;

@Data
public class MqttConfiguration {
    private String serverUrl;
    private String clientId;
    private Boolean autoReconnect;
    private String username;
    private String password;
}
