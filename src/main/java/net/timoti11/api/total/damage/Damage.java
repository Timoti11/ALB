package net.timoti11.api.total.damage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Damage {
    @JsonProperty("value")
    private int value;

    public int getValue() {
        return value;
    }
}
