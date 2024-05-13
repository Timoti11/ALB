package net.timoti11.api.legends.selected.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private int value;

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
}