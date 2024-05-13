package net.timoti11.api.total;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.timoti11.api.total.damage.Damage;
import net.timoti11.api.total.kd.Kd;
import net.timoti11.api.total.kills.Kills;

public class Total {
    @JsonProperty("kills")
    private Kills kills;
    @JsonProperty("specialEvent_damage")
    private Damage damage;
    @JsonProperty("kd")
    private Kd kd;

    public Kills getKills() {
        return kills;
    }
    public Damage getDamage() {
        return damage;
    }
    public Kd getKd() {
        return kd;
    }
}
