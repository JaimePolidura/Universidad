package AlgoritmiaI.proyectofinal.cli;

import java.util.HashMap;
import java.util.Map;

public final class CommandRegistry {
    private final Map<String, CommandRunner> commands;

    public CommandRegistry(){
        this.commands = new HashMap<>();
    }

    public void register(String commandName, CommandRunner runner){
        this.commands.put(commandName, runner);
    }

    public boolean existsCommand(String commandName){
        return this.commands.containsKey(commandName);
    }

    public CommandRunner findByCommandName(String commandName){
        return this.commands.get(commandName);
    }
}
