package net.timoti11;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.timoti11.interaction.SlashCommandInteraction;
import okhttp3.OkHttpClient;

import java.util.EventListener;

public class OnLaunch implements EventListener {
    public static OkHttpClient client = new OkHttpClient();
    public static void main(String[] args) throws Exception {
        //Create the bot
        JDA jda = JDABuilder.createLight(Dotenv.load().get("DISCORD_TOKEN"))
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .enableIntents(GatewayIntent.GUILD_PRESENCES)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Apex Legends"))
                .addEventListeners(new SlashCommandInteraction())
                .build().awaitReady();

        //Update global commands
        jda.updateCommands().queue();

        //My server bind
        Guild guild = jda.getGuildById("710092032416284714");

        //Add guild commands
        assert guild != null;
        guild.updateCommands().addCommands(Commands.slash("stats", "Your game statistic here!")
                        .addOption(OptionType.STRING, "platform", "PC (Steam/Origin) | Playstation | Xbox", true, true)
                        .addOption(OptionType.STRING, "player", "Player name", true, false)
                )
                .queue();
    }
}