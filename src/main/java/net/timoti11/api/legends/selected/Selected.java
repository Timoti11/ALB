package net.timoti11.api.legends.selected;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.timoti11.api.legends.selected.data.Data;
import net.timoti11.api.legends.selected.imgAssets.ImgAssets;

import java.util.List;

public class Selected {
    @JsonProperty("ImgAssets")
    private ImgAssets imgAssets;
    @JsonProperty("data")
    private List<Data> data;

    public ImgAssets getImgAssets() {
        return imgAssets;
    }
    public List<Data> getData() {
        return data;
    }
}
