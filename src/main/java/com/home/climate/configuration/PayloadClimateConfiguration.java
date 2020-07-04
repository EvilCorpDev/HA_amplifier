package com.home.climate.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayloadClimateConfiguration {
    @JsonProperty("temp_up")
    private String tempUpPayload;

    @JsonProperty("temp_down")
    private String tempDownPayload;
}
