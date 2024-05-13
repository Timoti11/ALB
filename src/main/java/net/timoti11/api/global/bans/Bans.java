package net.timoti11.api.global.bans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bans {
    @JsonProperty("isActive")
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }
}
