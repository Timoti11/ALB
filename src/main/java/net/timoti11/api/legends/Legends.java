package net.timoti11.api.legends;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.timoti11.api.legends.selected.Selected;

public class Legends {
    @JsonProperty("selected")
    private Selected selected;

    public Selected getSelected() {
        return selected;
    }
}
