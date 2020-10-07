package command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * выполняет метод execute для классов реализующих интерфейс Command,
 * выбирает класс в зависимости от выбранной команды
 */
public class Executor {

    /**
     * карта, связщывающая имена команд с классами
     */
    private Map<String, Command> commands = new HashMap<>();

    public Executor(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * выполнение метода execute для класса,
     * связанного с переданной командой
     *
     * @param command - команда, для выбора класса
     * @param paths   - пути к файлам, над которыми надо провести операции
     */
    public void execute(String command, List<String> paths) {
        commands.get(command).execute(paths);
    }
}
