package es.jaime.proyectofinal.cli;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class FlightsApplicationCli {
    private final Scanner scanner;
    private final CommandRegistry commandRegistry;

    public FlightsApplicationCli(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        this.commandRegistry = new CommandRegistry();
    }

    public void registerCommand(String commandName, CommandRunner commandRunner){
        this.commandRegistry.register(commandName, commandRunner);
    }

    public void startListeningForCommands(){
        while(true){
            try {
                String input = scanner.nextLine();

                onNewInput(input);
            }catch (Exception e ){
                System.err.println(e.getMessage());
            }
        }
    }

    public void onNewInput(String input){
        String commandName = getCommandNameFromInput(input);
        String[] args = getArgsFromInput(input);

        if(commandExists(commandName)){
            this.commandRegistry
                    .findByCommandName(commandName)
                    .execute(args);
        }
    }

    private boolean commandExists(String commandName){
        return this.commandRegistry.existsCommand(commandName);
    }

    private String[] getArgsFromInput(String input){
        String[] inputSplitBySpaces = input.split(" ");

        return inputSplitBySpaces.length > 1 ?
                Arrays.copyOfRange(inputSplitBySpaces, 1, inputSplitBySpaces.length) :
                new String[0];
    }

    private String getCommandNameFromInput(String input){
        return input.split(" ")[0];
    }
}
