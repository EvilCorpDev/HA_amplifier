package com.home.cover.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayloadCoverConfiguration {

    @JsonProperty("open_cover")
    private String openCoverPayload;

    @JsonProperty("close_cover")
    private String closeCoverPayload;
}
