package tools;

/**
 * утилитный интерфейс для работы с файлами
 */
public interface FileTools {
    /**
     * удаляет расширения файла из пути к файлу
     *
     * @param filepath - путь к файлу
     * @return - путь к файлу без расширения
     */
    static String getBaseName(String filepath) {
        int index = filepath.lastIndexOf('.');
        if (index == -1) {
            return filepath;
        } else {
            return filepath.substring(0, index);
        }
    }
}
