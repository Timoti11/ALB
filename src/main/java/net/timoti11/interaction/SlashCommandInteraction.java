package net.timoti11.interaction;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.timoti11.api.ApiResponse;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.timoti11.api.GetInfo.getInfo;
import static net.timoti11.util.ImageBuilder.imageGen;
import static net.timoti11.util.StatsEmbedBuilder.sendEmbedImage;
import static net.timoti11.util.StatsEmbedBuilder.sendEmbedPre;

public class SlashCommandInteraction extends ListenerAdapter {
    private static final File waitingImage = new File("waiting-min.png");
    private static final File loadingImage = new File("loading-min.png");
    private static final Map<String, String> PLATFORM_MAP = new HashMap<>();

    static {
        PLATFORM_MAP.put("PC (Steam/Origin)", "PC");
        PLATFORM_MAP.put("Playstation", "PS4");
        PLATFORM_MAP.put("Xbox", "X1");
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("stats")) {

            //Take platform and player from slash-command
            String platform = PLATFORM_MAP.getOrDefault(event.getOption("platform").getAsString(), "WTF");
            String player = event.getOption("player").getAsString();

            if (platform.equals("WTF")) {
                event.reply("Incorrect platform").setEphemeral(true).queue();
                return;
            }

            //Request
            ApiResponse playerData = getInfo(player, platform, event);

            //Stop event by null-response
            if (playerData == null) return;

            //PreGenerate image
            sendEmbedPre(event, loadingImage);

            //Generate image
            String unique = imageGen(playerData);

            //Send embed
            sendEmbedImage(event, new File(unique + ".png"));
        }
    }

    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
        if (event.getName().equals("stats") && event.getFocusedOption().getName().equals("platform")) {
            List<Command.Choice> options = PLATFORM_MAP.keySet().stream()
                    .sorted()
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue()))
                    .map(word -> new Command.Choice(word, word))
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
