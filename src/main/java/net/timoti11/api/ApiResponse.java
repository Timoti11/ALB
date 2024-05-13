package net.timoti11.api;

import net.timoti11.api.global.Global;
import net.timoti11.api.legends.Legends;
import net.timoti11.api.legends.selected.imgAssets.ImgAssets;
import net.timoti11.api.realtime.Realtime;
import net.timoti11.api.total.Total;

public class ApiResponse {
    private Global global;
    private Realtime realtime;
    private Total total;
    private Legends legends;

    public Global getGlobal() {
        return global;
    }

    public Realtime getRealtime() {
        return realtime;
    }

    public Total getTotal() {
        return total;
    }

    public Legends getLegends() {
        return legends;
    }
}