package pro.sky.telegrambot.model;

import javax.persistence.SqlResultSetMappings;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Commands {
    private Map<String, String> commands = Stream.of(new String[][] {
            { "/start", "Превет.Этот бот предназначен для уведомлений" },
            { "/add_notification", "чтобы добавить уведомление, необходимо " +
                    "его в следующем формате" },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public String getCommand(String neededCommand){
        return commands.get(neededCommand);
    }

    public List<String> getAllCommands() {
        return new ArrayList<String>(commands.keySet());
    }

    public boolean checkIsCommand(String string) {
        return commands.containsKey(string);
    }

    public String getValue(String string) {
        return commands.get(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commands commands1 = (Commands) o;
        return Objects.equals(commands, commands1.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(commands);
    }

    @Override
    public String toString() {
        return "Commands{" +
                "commands=" + commands +
                '}';
    }
}
