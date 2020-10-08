package command;

import java.util.List;

/**
 * интерфейс для посторения исполняемых команд
 *
 * @author Valentin
 * @version 1.00
 * @since 08/10/2020
 */
public interface Command {
    /**
     * выполнение задачи, которую реализующий класс
     *
     * @param pathsList - список файлов, на которомы
     *                  нужно произвести операцию
     */
    void execute(List<String> pathsList);

    /**
     * описание функций команды execute
     *
     * @return - описание
     */
    String info();

    /**
     * имя команды, по которой произойдет
     * выполнение команды execute
     *
     * @return - имя команды
     */
    String commandName();
}
