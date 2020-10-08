package command;

import java.io.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * реализует разархивирование файлов
 *
 * @author Valentin
 * @version 1.00
 * @since 08/10/2020
 */
public class Unpacking implements Command {
    /**
     * реализует операцию разархивирования файлов
     *
     * @param pathsList - пути к файлам для разархивирования
     */
    @Override
    public void execute(List<String> pathsList) {
        pathsList.forEach(new UnpackConsumer());
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String commandName() {
        return "unpacking";
    }

    /**
     * выполняет разархивирование для файла, к которому был получен путь
     */
    static class UnpackConsumer implements Consumer<String> {
        private final File destDir = new File(System.getProperty("user.dir"));

        @Override
        public void accept(String fileZip) {
            try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileZip))) {
                ZipEntry zipEntry;
                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    File newFile = new File(destDir, zipEntry.getName());
                    if(zipEntry.isDirectory()) {
                        newFile.mkdirs();
                        continue;
                    }
                    newFile.getParentFile().mkdirs();
                    FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zipInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.close();
                }
                zipInputStream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
