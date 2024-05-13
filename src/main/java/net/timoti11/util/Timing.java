package net.timoti11.util;

public class Timing {
    public String getTiming(String desc, long now, long last) {
        return "Time (" + desc + "): " + (now - last) + " ms";
    }
}
