package properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * чтение параметров передаваемых через String[] args
 */
public class PropertiesReader {

    /**
     * имя команды
     */
    private String command;
    /**
     * список путей к файлам
     */
    private List<String> pathsList;
    /**
     * информация об ошибке при чтении параметров
     */
    private String errorMessage = "Информация об ошибках отсутствует";

    /**
     * получает параметры из String[] args
     *
     * @param args - String[] args
     * @return true - если параметры прочитаны умпешно, иначе - false
     */
    public boolean setPropertyFromArgs(String[] args) {
        if (args.length < 1) {
            errorMessage = "Входные параметры отсутствуют.";
            return false;
        } else {
            if (args[0] == null) {
                errorMessage = "Не задана команда.";
                return false;
            } else {
                this.command = args[0];
            }
            this.pathsList = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
            return true;
        }
    }

    /**
     * вывод сообщения об ошибке
     *
     * @return описание ошибки
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * получение имени команды
     *
     * @return - команда
     */
    public String getCommand() {
        return command;
    }

    /**
     * получение списка путей к файлам
     *
     * @return - список путей
     */
    public List<String> getPathsList() {
        return pathsList;
    }

}
