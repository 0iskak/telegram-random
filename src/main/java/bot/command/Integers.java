package bot.command;

import java.io.IOException;
import java.util.Arrays;

public class Integers extends Command {
    @Override
    void main() throws IOException {
        var args = Arrays.stream(bot.getArgs())
                .skip(1)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        var ints = bot.getRandom().generateIntegers(args[0], args[1], args[2]);

        send(Arrays.stream(ints).boxed().toArray());
    }
}
