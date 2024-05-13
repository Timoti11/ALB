package net.timoti11.api.global;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.timoti11.api.global.bans.Bans;
import net.timoti11.api.global.rank.Rank;

public class Global {
    @JsonProperty("name")
    public String name;
    @JsonProperty("tag")
    private String tag;
    @JsonProperty("platform")
    private String platform;
    @JsonProperty("levelPrestige")
    private int levelPrestige;
    @JsonProperty("level")
    private int level;
    @JsonProperty("toNextLevelPercent")
    private int toNextLevelPercent;
    @JsonProperty("rank")
    private Rank rank;
    @JsonProperty("bans")
    private Bans bans;

    public String getName() {
        return name;
    }
    public String getTag() {
        if (!tag.isEmpty()) {
            tag = "[" + tag + "] ";
        }
        return tag;
    }
    public String getPlatform() {
        return platform;
    }
    public int getLevelPrestige() {
        return levelPrestige;
    }
    public String getLevel() {
        int fullLevel = levelPrestige*500+level;
        return fullLevel + " Level";
    }
    public Rank getRank() {
        return rank;
    }
    public Bans getBans() {
        return bans;
    }
    public int getToNextLevelPercent() {
        return toNextLevelPercent;
    }
}
