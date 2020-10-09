package command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * реализует архивирование файлов
 *
 * @author Valentin
 * @version 1.00
 * @since 08/10/2020
 */
public class Packing implements Command {
    private static final String FS = File.separator;

    /**
     * реализует операцию архивирования файлов
     *
     * @param pathsList - первый элемент содержит имя архива,
     *                  а остальные - пути к файлам для архитвирования
     */
    @Override
    public void execute(List<String> pathsList) {
        String zipName = pathsList.get(0).concat(".zip");
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipName));
            List<File> files = pathsList
                    .subList(1, pathsList.size())
                    .stream()
                    .map(File::new)
                    .collect(Collectors.toList());
            for (File file : files) {
                if (file.isDirectory()) {
                    createZipDir(zipOutputStream, file.listFiles(), file.getName() + FS, zipName);
                } else {
                    createZipDir(zipOutputStream, new File[]{file}, "", zipName);
                }
            }
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String info() {
        return "Команда для архивирования имеет следующую структуру\n"
                + "java -jar путь-к-исполняемому-jar-файлу packing имя-архива путь-к-1-ому-файлу-или-папке путь-к-следущему-файлу-или-архиву\n"
                + "Например(для Windows):\n"
                + "java -jar D:\\Archiver-1.0.jar packing archiver-name D:\\first D:\\next.text\n";
    }

    @Override
    public String commandName() {
        return "packing";
    }

    /**
     * рекурсивная функция для архивирования файлов,
     * вызывает сама себя для каждого каталога(директории)
     * в массиве переданных файло
     *
     * @param zipOutputStream - поток, в который передаются данные для архивирования
     * @param files           - массив файлов для архивирования
     * @param path            - путь относительно корневой директории,
     *                        в который происходит архивация файлов для
     *                        текущего вызова функции
     * @param zipName         - имя файла(архива) куда происходит запись
     * @throws IOException - ошибки чтения-записи в файл
     */
    private static void createZipDir(ZipOutputStream zipOutputStream,
                                     File[] files,
                                     String path,
                                     String zipName) throws IOException {
        for (File file : files) {
            if (file.isDirectory()) {
                createZipDir(zipOutputStream, file.listFiles(), path + file.getName() + FS, zipName);
            } else if (!file.getName().equals(zipName)) {
                ZipEntry zipEntry = new ZipEntry(path + file.getName());
                try {
                    zipOutputStream.putNextEntry(zipEntry);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int size;
                while ((size = fileInputStream.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, size);
                }
            }
        }
    }
}
