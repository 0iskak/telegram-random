package bot.command;

import java.io.IOException;
import java.util.Arrays;

public class BLOBs extends Command {
    @Override
    void main() throws IOException {
        var args = Arrays.stream(bot.getArgs())
                .skip(1)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        var blobs = bot.getRandom().generateBlobs(args[0], args[1]);

        send(blobs);
    }
}
