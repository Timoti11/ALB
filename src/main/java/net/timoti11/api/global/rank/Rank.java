package net.timoti11.api.global.rank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rank {
    @JsonProperty("rankName")
    private String rankName;
    @JsonProperty("rankScore")
    private String rankScore;
    @JsonProperty("rankDiv")
    private String rankDiv;
    @JsonProperty("rankImg")
    private String rankImg;

    public String getRankName() {
        return rankName;
    }

    public String getRankScore() {
        rankScore = " (" + rankScore + ")";
        return rankScore;
    }

    public String getRankDiv() {
        return rankDiv;
    }

    public String getRankImg() {
        return rankImg;
    }
}
