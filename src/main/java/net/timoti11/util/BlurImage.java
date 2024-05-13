package net.timoti11.util;

import com.jhlabs.image.GaussianFilter;

import java.awt.image.BufferedImage;

public class BlurImage {
    public static BufferedImage getBlur(BufferedImage image) {
        try {
            GaussianFilter filter = new GaussianFilter();

            filter.setRadius(50);

            image = filter.filter(image, null);

            return image;
        } catch (Exception e) {
            System.out.println(e);
            return image;
        }
    }
}
