package bot.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Strings extends Command {
    @Override
    void main() throws IOException {
        var args = Arrays.stream(bot.getArgs())
                .skip(1)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        var set = new StringBuilder();

        if (args[2] == 1)
            IntStream.range(0, 10)
                    .forEach(set::append);

        if (args[3] == 1)
            IntStream.range('a', 'z' + 1)
                    .mapToObj(i -> (char) i)
                    .forEach(set::append);

        if (args[4] == 1)
            IntStream.range('A', 'Z' + 1)
                    .mapToObj(i -> (char) i)
                    .forEach(set::append);

        var strings = bot.getRandom().generateStrings(args[0], args[1], set.toString());

        send(strings);
    }
}
