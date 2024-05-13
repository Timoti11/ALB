package net.timoti11.api.total.kills;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kills {
    @JsonProperty("value")
    private int value;

    public int getValue() {
        return value;
    }
}