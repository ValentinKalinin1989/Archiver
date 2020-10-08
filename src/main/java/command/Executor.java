package command;

import java.util.List;
import java.util.Map;

/**
 * выполняет метод execute для классов реализующих интерфейс Command,
 * выбирает класс в зависимости от выбранной команды
 *
 * @author Valentin
 * @version 1.00
 * @since 08/10/2020
 */
public class Executor {

    /**
     * карта, связщывающая имена команд с классами
     */
    private final Map<String, Command> commands;

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
