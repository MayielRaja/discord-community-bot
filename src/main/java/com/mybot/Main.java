package com.mybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Load configuration from the properties file
        ConfigLoader config = new ConfigLoader();
        String token = config.getProperty("DISCORD_TOKEN");
        String serverId = config.getProperty("SERVER_ID");

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(new CommandManager());

        JDA jda = builder.build().awaitReady();

        System.out.println("Bot is online and ready!");

        Guild guild = jda.getGuildById(serverId);

        if (guild != null) {
            guild.upsertCommand("ping", "Calculates the bot's latency.").queue();
            guild.upsertCommand("say", "Makes the bot say something.").addOption(OptionType.STRING, "message", "The message you want the bot to say", true).queue();
            guild.upsertCommand("weather", "Gets the current weather for a city.").addOption(OptionType.STRING, "city", "The city you want the weather for", true).queue();
            guild.upsertCommand("define", "Looks up the definition of a word.")
                    .addOption(OptionType.STRING, "word", "The word to define", true)
                    .queue();
        }
    }
}
