package net.timoti11.api.total.kd;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kd {
    @JsonProperty("value")
    private float value;

    public float getValue() {
        return value;
    }
}
