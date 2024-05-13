package net.timoti11.api.legends.selected.imgAssets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgAssets {
    @JsonProperty("banner")
    private String banner;

    public String getBanner() {
        return banner;
    }
}
