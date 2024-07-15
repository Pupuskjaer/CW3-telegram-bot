package pro.sky.telegrambot.model;

import javax.persistence.SqlResultSetMappings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Commands {
    private Map<String, String> commands = Stream.of(new String[][] {
            { "/start", "Этот бот предназначен для уведомлений" },
            { "/add_notification", "чтобы добавить уведомление, необходимо " +
                    "его в следующем формате" },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public String getCommand(String neededCommand){
        return commands.get(neededCommand);
    }

    public List<String> getAllCommands() {
        return new ArrayList<String>(commands.values());
    }

}
