package net.timoti11.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.awt.*;
import java.io.File;

public class StatsEmbedBuilder {
    static MessageEmbed messageEmbed;

    //Stats embed template
    public static MessageEmbed getMessageEmbed() {
        EmbedBuilder stats = new EmbedBuilder();
        stats.setColor(Color.DARK_GRAY);
        stats.addField("TextTextTextTextTextText", "TextTextTextTextTextText", true);

        return stats.build();
    }

    //PreGenerate embed with ImagePlaceholder
    public static void sendEmbedPre(SlashCommandInteractionEvent event, File file) {
        event.replyEmbeds(getMessageEmbed())
                .addFiles(FileUpload.fromData(file))
                .queue();
    }

    //Finish embed with rendered image
    public static void sendEmbedImage(SlashCommandInteractionEvent event, File unique) {
        event.getHook().editOriginalEmbeds()
                .setEmbeds(getMessageEmbed())
                .setFiles(FileUpload.fromData(unique)).queue();
//        unique.deleteOnExit();
    }
}