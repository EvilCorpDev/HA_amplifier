package com.home.amplifier.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayloadConfiguration {
    @JsonProperty("volume_up")
    private String volumeUpPayload;

    @JsonProperty("volume_down")
    private String volumeDownPayload;

    @JsonProperty("mute")
    private String mutePayload;

    @JsonProperty("source")
    private String sourcePayload;
}
