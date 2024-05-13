package net.timoti11.api.realtime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Realtime {
    @JsonProperty("isOnline")
    private int isOnline;
    @JsonProperty("isInGame")
    private int isInGame;
    @JsonProperty("selectedLegend")
    private String selectedLegend;

    public int getIsOnline() {
        return isOnline;
    }

    public int getIsInGame() {
        return isInGame;
    }

    public String getSelectedLegend() {
        return selectedLegend;
    }
}
