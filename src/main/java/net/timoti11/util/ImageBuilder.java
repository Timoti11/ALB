package net.timoti11.util;

import net.timoti11.api.ApiResponse;
import net.timoti11.api.global.Global;
import net.timoti11.api.legends.Legends;
import net.timoti11.api.legends.selected.Selected;
import net.timoti11.api.legends.selected.data.Data;
import net.timoti11.api.legends.selected.imgAssets.ImgAssets;
import net.timoti11.api.realtime.Realtime;
import net.timoti11.api.total.Total;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.List;

public class ImageBuilder {
    public static String imageGen(ApiResponse apiResponse) {
        //Gen UUID for image
        String unique = UUID.randomUUID().toString();

        //Main
        Global global = apiResponse.getGlobal();
        Realtime realtime = apiResponse.getRealtime();
        Total total = apiResponse.getTotal();
        ImgAssets imgAssets = apiResponse.getLegends().getSelected().getImgAssets();

        //Global
        String globalName = global.getName();
        String tag = global.getTag();
        String platform = global.getPlatform();
        String level = global.getLevel();

        //Bans
        boolean bans = global.getBans().isActive();

        //Rank
        String rankScore = global.getRank().getRankScore();
        String rankName = global.getRank().getRankName();
        String rankDiv = global.getRank().getRankDiv();
        String rankImg = global.getRank().getRankImg();

        //Realtime
        int realtimeIsOnline = realtime.getIsOnline();
        int realtimeIsInGame = realtime.getIsInGame();
        String realtimeSelectLegend = realtime.getSelectedLegend();

        //Total
/*        int totalDamage = total.getDamage().getValue();
        int totalKills = total.getKills().getValue();
        float totalKd = total.getKd().getValue();*/

        try {
            //Take template image for width/height
            BufferedImage template = ImageIO.read(new File("assets/baseAssets/template.png"));

            BufferedImage resultImage = new BufferedImage(
                    template.getWidth(),
                    template.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = resultImage.createGraphics();


            //Background Image
            BufferedImage bannerRender = null;
            try {
                String banner = imgAssets.getBanner();
                bannerRender = ImageIO.read(new URL(banner));
            } catch (IOException e) {
                File banner = new File("assets/baseAssets/template.png");
                bannerRender = ImageIO.read(banner);
            }
            g.drawImage(bannerRender, 0, 0, null);


            //Dark background
            renderImageElement(g, "assets/baseAssets/black", 0, 0, 0, 0);


            //Platform Image
            if (platform.equals("PC")) {
                renderImageElement(g, "assets/platform/PC", 228, 150, 50, 50);
            } else if (platform.equals("X1")) {
                renderImageElement(g, "assets/platform/X1", 228, 150, 50, 50);
            }


            //Blur background for nameBackgroundRender
            bannerRender = BlurImage.getBlur(bannerRender);
            BufferedImage nameFieldMask = ImageIO.read(new File("assets/nameField/nameFieldMask.png"));

            BufferedImage nameFieldResult = new BufferedImage(nameFieldMask.getWidth(), nameFieldMask.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D gMask = nameFieldResult.createGraphics();

            gMask.drawImage(nameFieldMask, 0, 0, null);
            gMask.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN));
            gMask.drawImage(bannerRender, 0, 0, null);
            gMask.dispose();

            g.drawImage(nameFieldResult, 122, 44, null);


            //NameBackground Image
            renderImageElement(g, "assets/nameField/toNextLevelPercent", 123, 44, 0, 0);


            //toNextLevelPercent Image
            int percent = apiResponse.getGlobal().getToNextLevelPercent();

            renderImageElement(g, "assets/nameField/toNextLevelPercentChunk", 215, 123, percent * 5, 0);
            renderImageElement(g, "assets/nameField/toNextLevelPercentEnd", 215 + percent * 5, 123, 0, 0);


            //Rank Image
            String pathRankImage = RankImage.getImage(rankName, rankDiv);
            renderImageElement(g, pathRankImage, 12, 30, 220, 220);


            //Render bars
            renderImageBar(g, apiResponse);


            //Name Text
            renderTextElement(g, "TT Lakes Condensed", tag + globalName, 42, "Center", 430, 100);


            //Level Text
            renderTextElement(g, "TT Squares Condensed", level, 42, "Right", 720, 184);


            g.dispose();

            ImageIO.write(resultImage, "PNG", new File(unique + ".png"));

            return unique;
        } catch (IOException e) {
            return null;
        }
    }

    static void renderImageElement(Graphics2D g, String imageName, int x, int y, int imageWidth, int imageHeight) throws IOException {
        try {
            BufferedImage image = ImageIO.read(new File(imageName + ".png"));
            if (imageWidth == 0) imageWidth = image.getWidth();
            if (imageHeight == 0) imageHeight = image.getHeight();
            g.drawImage(image, x, y, imageWidth, imageHeight, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void renderTextElement(Graphics2D g, String fontName, String text, int size, String align, int x, int y) throws IOException {
        try {
            g.setFont(new Font(fontName, Font.PLAIN, size));
            int marginLeft = 0;
            switch (align) {
                //Left-align
                case "Center": {
                    marginLeft = g.getFontMetrics().stringWidth(text)/2;
                    break;
                }
                //Left-align
                case "Right": {
                    marginLeft = g.getFontMetrics().stringWidth(text);
                }
            }
            g.drawString(text, x-marginLeft, y);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void renderImageBar(Graphics2D g, ApiResponse apiResponse) throws IOException {
        List<Data> data = apiResponse.getLegends().getSelected().getData();
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) != null) {
                    g.drawImage(renderImageBarTemplate(i, data), 1006, 38+i*150, 445, 140, null);
                }
            }
        }
    }

    public static BufferedImage renderImageBarTemplate(int i, List<Data> data) throws IOException {
        try {
            BufferedImage imageBarTemplate = ImageIO.read(new File("assets/statsField/currentStatsField.png"));
            BufferedImage imageBarResult = new BufferedImage(
                    imageBarTemplate.getWidth(),
                    imageBarTemplate.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D gBar = imageBarResult.createGraphics();

            gBar.drawImage(imageBarTemplate, 0, 0, null);

            gBar.setFont(new Font("Agency FB", Font.BOLD, 36));
            gBar.drawString(data.get(i).getName(), 38, 45);
            gBar.setFont(new Font("Agency FB", Font.BOLD, 52));
            gBar.drawString(String.valueOf(data.get(i).getValue()), 38, 130);

            gBar.dispose();

            return imageBarResult;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}