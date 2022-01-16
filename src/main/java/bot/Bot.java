package bot;

import bot.command.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.Getter;
import org.random.api.RandomOrgClient;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class Bot extends TelegramBot {
    @Getter
    private static Bot bot;

    private final RandomOrgClient random;
    private final Map<String, Runnable> commands = new LinkedHashMap<>();

    private Message message;
    private String[] args;

    public Bot(String token, RandomOrgClient random) {
        super(token);
        this.random = random;
        bot = this;

        setUpdatesListener(updates -> {
            updates.stream()
                    .map(Update::message)
                    .filter(Objects::nonNull)
                    .filter(this::notOld)
                    .forEach(this::onMessage);

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });


        commands.put("int", Integers::new);
        commands.put("uuid", UUIDs::new);
        commands.put("blob", BLOBs::new);
        commands.put("decimal", Decimals::new);
        commands.put("string", Strings::new);
    }

    private boolean notOld(Message message) {
        var time = Instant.now().minusSeconds(message.date()).getEpochSecond();
        return time < 5;
    }

    private void onMessage(Message message) {
        this.message = message;
        args = Arrays.stream(message.text().split("\s+"))
                .toArray(String[]::new);
        try {
            commands.get(args[0].substring(1)).run();
        } catch (NullPointerException ignored) {
        }
    }
}
