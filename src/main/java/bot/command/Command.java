package bot.command;

import bot.Bot;
import com.pengrad.telegrambot.request.SendMessage;
import org.random.api.exception.RandomOrgRANDOMORGError;

import java.io.IOException;
import java.util.Arrays;

public abstract class Command {
    Bot bot = Bot.getBot();
    Long chatId = bot.getMessage().chat().id();

    public Command() {
        try {
            main();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {
            bot.execute(new SendMessage(chatId, "Error: Invalid Arguments"));
        } catch (RandomOrgRANDOMORGError e) {
            bot.execute(new SendMessage(chatId, e.getMessage()));
        } catch (Exception e) {
            bot.execute(new SendMessage(chatId, "Error: Some error occurred"));
        }
    }

    abstract void main() throws IOException;

    void send(Object[] objects) {
        var s = new StringBuilder();
        Arrays.stream(objects)
                .forEach(o -> s.append(o).append('\n'));

        bot.execute(new SendMessage(chatId, s.toString()));
    }
}
