package bot.command;

import java.io.IOException;
import java.util.Arrays;

public class UUIDs extends Command {
    @Override
    void main() throws IOException {
        var args = Arrays.stream(bot.getArgs())
                .skip(1)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        var uuids = bot.getRandom().generateUUIDs(args[0]);

        send(uuids);
    }
}
