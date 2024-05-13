package net.timoti11.util;

public class RankImage {
    public static String getImage(String rankName, String rankDiv) {
        return "assets/ranks/" + rankName.toLowerCase() + "/" + rankName.toLowerCase() + rankDiv;
    }
}