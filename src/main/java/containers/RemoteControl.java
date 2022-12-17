package containers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RemoteControl {
    private final Map<String, Supplier<Object>> commands = new HashMap<>();

    public RemoteControl addCommand(String name, Supplier<Object> command) {
        commands.put(name, command);

        return this;
    }

    public Object press(String command) {
        return commands.get(command).get();
    }
}
