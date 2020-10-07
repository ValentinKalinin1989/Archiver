package main;

import command.Command;
import command.Executor;
import command.Packing;
import command.Unpacking;
import properties.PropertiesReader;

import java.util.HashMap;
import java.util.Map;

/**
 * основной класс, реализующий работу приложения
 */
public class Archiver {
    public static void main(String[] args) {

        PropertiesReader propertiesReader = new PropertiesReader();
        if (!propertiesReader.setPropertyFromArgs(args)) {
            System.out.println(propertiesReader.getErrorMessage());
        } else {
            Executor executor = new Executor(initCommands());
            executor.execute(propertiesReader.getCommand(), propertiesReader.getPathsList());
        }
    }

    /**
     * связыват команды с реализующими классами
     *
     * @return - карта связи имен команд с реализующими классами
     */
    public static Map<String, Command> initCommands() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("packing", new Packing());
        commands.put("unpacking", new Unpacking());
        return commands;
    }
}
