import bot.Bot;
import lombok.SneakyThrows;
import org.random.api.RandomOrgClient;

public class Launcher {
    @SneakyThrows
    public static void main(String[] args) {
        var api = args[0];
        var random = RandomOrgClient.getRandomOrgClient(api);
        var token = args[1];
        new Bot(token, random);
        System.out.println("Started!");
    }
}
