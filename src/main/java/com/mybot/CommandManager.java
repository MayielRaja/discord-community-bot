package com.mybot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import java.awt.Color;

public class CommandManager extends ListenerAdapter {
    // Declare BOTH services you are using
    private final WeatherService weatherService = new WeatherService();
    private final DictionaryService dictionaryService = new DictionaryService();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("ping")) {
            event.reply("pong!").setEphemeral(true).queue();
        } else if (command.equals("say")) {
            OptionMapping option = event.getOption("message");
            if (option != null) {
                String message = option.getAsString();
                event.reply(message).queue();
            }
        } else if (command.equals("weather")) {
            event.deferReply().queue();
            OptionMapping option = event.getOption("city");
            if (option == null) {
                event.getHook().sendMessage("You must provide a city name.").queue();
                return;
            }
            String city = option.getAsString();
            String weatherInfo = weatherService.getWeather(city);
            event.getHook().sendMessage(weatherInfo).queue();
        } else if (command.equals("define")) {
            event.deferReply().queue();
            OptionMapping option = event.getOption("word");
            String word = option.getAsString();

            WordEntry entry = dictionaryService.getDefinition(word);

            if (entry == null) {
                event.getHook().sendMessage("Sorry, I couldn't find a definition for '" + word + "'.").queue();
                return;
            }

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(entry.getWord() + " " + entry.getPhonetic());
            embed.setColor(Color.GREEN);

            StringBuilder description = new StringBuilder();
            for (Meaning meaning : entry.getMeanings()) {
                description.append("**").append(meaning.getPartOfSpeech()).append("**\n");
                for (int i = 0; i < meaning.getDefinitions().size(); i++) {
                    description.append((i + 1) + ". ").append(meaning.getDefinitions().get(i).getDefinition()).append("\n");
                }
                description.append("\n");
            }

            embed.setDescription(description.toString());
            event.getHook().sendMessageEmbeds(embed.build()).queue();
        }
    }
}
