package net.timoti11.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static net.timoti11.OnLaunch.client;


public class GetInfo {
    public static ApiResponse getInfo(String name, String platform, SlashCommandInteractionEvent event) {
        //Create new client

        //Bind value for mergeStat
        String trueValue = "1";

        //Create base url with parameters
        HttpUrl url = HttpUrl.parse("https://api.mozambiquehe.re/bridge").newBuilder()
                .addQueryParameter("platform", platform)
                .addQueryParameter("player", name)
                .addQueryParameter("merge", trueValue)
                .addQueryParameter("removeMerged", trueValue)
                .build();

        //Request from url with Header
        //Key take here: https://portal.apexlegendsapi.com
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Dotenv.load().get("STATS_KEY"))
                .build();

        //Try to response
        try (Response response = client.newCall(request).execute()) {
            //Response code variants reply
            //For more information - check API docs: https://apexlegendsapi.com/#errors
            switch (response.code()) {
                case (400): {
                    event.reply("Try again in a few minutes.").setEphemeral(true).queue();
                    return null;
                }
                case (403): {
                    event.reply("API error - message to admin.").setEphemeral(true).queue();
                    return null;
                }
                case (404): {
                    event.reply("Player not found.").setEphemeral(true).queue();
                    return null;
                }
                case (405): {
                    event.reply("API-service error - message to admin.").setEphemeral(true).queue();
                    return null;
                }
                case (429): {
                    event.reply("Rate limit reached. Try again in few seconds.").setEphemeral(true).queue();
                    return null;
                }
                case (500): {
                    event.reply("Internal error - message to admin.").setEphemeral(true).queue();
                    return null;
                }
                //Successful response
                case (200): {
                    //Take String from response
                    assert response.body() != null;
                    String responseBody = response.body().string();

                    System.out.println(response.code());
                    System.out.println(responseBody);

                    // Take data from JSON
                    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    return mapper.readValue(responseBody, ApiResponse.class);
                }
                default: {
                    event.reply("Unknown error - message to admin.").setEphemeral(true).queue();
                    return null;
                }
            }
        } catch (IOException e) {
            event.reply("IOException detected - message to admin.").setEphemeral(true).queue();
            return null;
        }
    }
}