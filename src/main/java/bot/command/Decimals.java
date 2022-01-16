package bot.command;

import java.io.IOException;
import java.util.Arrays;

public class Decimals extends Command {
    @Override
    void main() throws IOException {
        var args = Arrays.stream(bot.getArgs())
                .skip(1)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        var decimals = bot.getRandom().generateDecimalFractions(args[0], args[1]);

        send(Arrays.stream(decimals).boxed().toArray());
    }
}
